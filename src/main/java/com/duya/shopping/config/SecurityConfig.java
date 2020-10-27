package com.duya.shopping.config;

import com.alibaba.fastjson.JSON;
import com.duya.shopping.filter.CustomAuthenticationFilter;
import com.duya.shopping.utils.CodeMessage;
import com.duya.shopping.utils.ServiceResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/manage").hasRole("admin")
                .anyRequest().permitAll()

                .and()
                .formLogin().loginProcessingUrl("/login")
//                .successHandler((req, resp, authentication) -> {
//                    Object principal = authentication.getPrincipal();
//                    resp.setContentType("application/json;charset=utf-8");
//                    PrintWriter out = resp.getWriter();
//                    out.write(JSON.toJSONString(ServiceResult.success(principal)));
//                    out.flush();
//                    out.close();
//                })
//                .failureHandler((req, resp, exception) -> {
//                    System.out.println(exception);
//                    resp.setContentType("application/json;charset=utf-8");
//                    PrintWriter out = resp.getWriter();
//                    out.write(JSON.toJSONString(ServiceResult.error(
//                            new ServiceResult.DefaultMessage<>("003", "账号或者密码错误"))));
//                    out.flush();
//                    out.close();
//                })
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable();

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
        filter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.write(JSON.toJSONString(ServiceResult.error(
                        new ServiceResult.DefaultMessage<>("003", "账号或者密码错误")
                )));
                out.flush();
                out.close();
            }

        });
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }
}