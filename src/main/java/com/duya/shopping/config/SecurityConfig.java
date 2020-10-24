package com.duya.shopping.config;

import com.alibaba.fastjson.JSON;
import com.duya.shopping.dao.UserDao;
import com.duya.shopping.pojo.UserEntity;
import com.duya.shopping.utils.ServiceResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDao userDao;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll() // 这句比较重要，放过 option 请求

                // 无权访问是返回 json 格式数据。
                .and().httpBasic().authenticationEntryPoint(new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                 AuthenticationException e) throws IOException, ServletException {
                ServiceResult rm = ServiceResult.error(new ServiceResult.DefaultMessage<>(401, "need login"));
                httpServletResponse.getWriter().write(JSON.toJSONString(rm));
            }
        })

                // 登录响应
                .and().formLogin().failureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp,
                                                AuthenticationException e) throws IOException {
                resp.setContentType("application/json;charset=utf-8");
                ServiceResult rm = null;
                if (e instanceof BadCredentialsException || e instanceof UsernameNotFoundException) {
                    rm = ServiceResult.error(new ServiceResult.DefaultMessage<>(401, "账户名或者密码输入错误!"));
                } else if (e instanceof LockedException) {
                    rm = ServiceResult.error(new ServiceResult.DefaultMessage<>(401, "账户被锁定，请联系管理员!"));
                } else if (e instanceof CredentialsExpiredException) {
                    rm = ServiceResult.error(new ServiceResult.DefaultMessage<>(401, "密码过期，请联系管理员!"));
                } else if (e instanceof AccountExpiredException) {
                    rm = ServiceResult.error(new ServiceResult.DefaultMessage<>(401, "账户过期，请联系管理员!"));
                } else if (e instanceof DisabledException) {
                    rm = ServiceResult.error(new ServiceResult.DefaultMessage<>(401, "账户被禁用，请联系管理员!"));
                } else {
                    rm = ServiceResult.error(new ServiceResult.DefaultMessage<>(401, "登录失败!"));
                }
                resp.setStatus(401);
                PrintWriter out = resp.getWriter();
                out.write(JSON.toJSONString(rm));
                out.flush();
                out.close();
            }
        }).successHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth)
                    throws IOException {
                resp.setContentType("application/json;charset=utf-8");
                Object curUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                ServiceResult rm = ServiceResult.success(curUser);
                ObjectMapper om = new ObjectMapper();
                PrintWriter out = resp.getWriter();
                out.write(om.writeValueAsString(rm));
                out.flush();
                out.close();
            }
        }).permitAll()

                // 退出登录
                .and().logout().logoutSuccessHandler(new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication)
                    throws IOException, ServletException {
                resp.setContentType("application/json;charset=utf-8");
                ServiceResult rm = ServiceResult.success("注销成功!");
                PrintWriter out = resp.getWriter();
                out.write(JSON.toJSONString(rm));
                out.flush();
                out.close();
            }
        }).permitAll()

                // 这里还是要关闭crsf因为crsf还有token之类的安全防护
                // 这就是这种方式不安全的原因吧
                .and().csrf().disable();
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 这里做用户登录判断用户名密码
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
                UserEntity user = UserDao.findByUsername(username); // 从你的数据库中取出用户
                if (user == null) {
                    throw new UsernameNotFoundException("用户名不存在");
                }
                return user;
            }
        }).passwordEncoder(new BCryptPasswordEncoder()); // 使用 BCryptPasswordEncoder 加盐加密
    }
}
