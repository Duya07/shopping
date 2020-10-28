package com.duya.shopping.config;

import com.alibaba.fastjson.JSON;
import com.duya.shopping.filter.CustomAuthenticationFilter;
import com.duya.shopping.utils.CodeMessage;
import com.duya.shopping.utils.ServiceResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /*
    /manage/** 需要admin权限 其他都不需要权限
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/manage/**").hasRole("admin")
                .anyRequest().permitAll()

                .and()
                .formLogin().loginProcessingUrl("/login")
                .permitAll()
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessHandler((req, resp, authentication) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write(JSON.toJSONString(ServiceResult.error(
                            new ServiceResult.DefaultMessage<>("00000000", "登出成功"))));
                    out.flush();
                    out.close();
                })
                .permitAll()
                .and()
                .csrf().disable().exceptionHandling()
                .authenticationEntryPoint((req, resp, authException) -> {
                            resp.setContentType("application/json;charset=utf-8");
                            PrintWriter out = resp.getWriter();
                            out.write(JSON.toJSONString(ServiceResult.error(
                                    new ServiceResult.DefaultMessage<>("10000005", "尚未登陆，请先登录"))));
                            out.flush();
                            out.close();
                        }
                );

        http.addFilter(customAuthenticationFilter());

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("admin").roles("admin");
    }


    /*
    json 格式  {"username": "admin", "password": "admin"}
     */
    @Bean
    CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth) throws IOException, ServletException {
                Object principal = auth.getPrincipal();
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.write(JSON.toJSONString(ServiceResult.success(principal)));
                out.flush();
                out.close();
            }
        });

        /*
        从 10000000 开始是错误码
         */
        filter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                if (e instanceof LockedException) {
                    out.write(JSON.toJSONString(ServiceResult.error(
                            new ServiceResult.DefaultMessage<>("10000000", "账户被锁定，请联系管理员!"))));
                } else if (e instanceof CredentialsExpiredException) {
                    out.write(JSON.toJSONString(ServiceResult.error(
                            new ServiceResult.DefaultMessage<>("10000001", "密码过期，请联系管理员!"))));
                } else if (e instanceof AccountExpiredException) {
                    out.write(JSON.toJSONString(ServiceResult.error(
                            new ServiceResult.DefaultMessage<>("10000002", "账户过期，请联系管理员!"))));
                } else if (e instanceof DisabledException) {
                    out.write(JSON.toJSONString(ServiceResult.error(
                            new ServiceResult.DefaultMessage<>("10000003", "账户被禁用，请联系管理员!"))));
                } else if (e instanceof BadCredentialsException) {
                    out.write(JSON.toJSONString(ServiceResult.error(
                            new ServiceResult.DefaultMessage<>("10000004", "用户名或者密码输入错误，请重新输入!"))));
                }
                out.flush();
                out.close();
            }
        });
        //.logoutSuccessHandler((req, resp, authentication) -> {
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }
}