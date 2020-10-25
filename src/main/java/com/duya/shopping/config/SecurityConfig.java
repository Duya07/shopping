//package com.duya.shopping.config;
//
//import com.duya.shopping.filter.MyAuthenticationFilter;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsUtils;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("admin").password("admin").authorities("MANAGE").build());
//        return manager;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .addFilter(myAuthen2aticationFilter())
//                .formLogin()
//                .loginProcessingUrl("/dologin")
//                .permitAll()
//                .and()
//                .csrf().disable();
//
////                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll() // 这句比较重要，放过 option 请求
////                .antMatchers("/","/index","/login").permitAll() // "/" 的请求可以通过
////                .antMatchers("/manage").hasAnyAuthority("MANAGE")
////                .anyRequest().authenticated() // 除了"/" 其他的请求必须认证通过
////                .and()
////                .formLogin() // 允许表单登录
////                .loginPage("/login")
////                .loginProcessingUrl("/api/login")
////                .successForwardUrl("/manage")
////                .permitAll();
//    }
//
//    @Bean
//    MyAuthenticationFilter myAuthenticationFilter() throws Exception {
//        MyAuthenticationFilter filter = new MyAuthenticationFilter();
//        filter.setAuthenticationManager(super.authenticationManagerBean());
//        filter.setFilterProcessesUrl("/jsonLogin");
//        filter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
//            @Override
//            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
//                response.setContentType("application/json;charset=utf-8");
//                PrintWriter out = response.getWriter();
//                Map<String, Object> map = new HashMap<>();
//                map.put("status", 200);
//                map.put("msg", auth.getPrincipal());
//                out.write(new ObjectMapper().writeValueAsString(map));
//                out.flush();
//                out.close();
//            }
//        });
//        return filter;
//    }
//}
////
////    @Autowired
////    UserDao userDao;
////
////    @Autowired
////    private PasswordEncoder passwordEncoder;
////
////    @Bean
////    public PasswordEncoder passwordEncoder(){
////        return new BCryptPasswordEncoder();
////    }
////
////    @Bean
////    public UserDetailsService userDetailsService(){
////        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
////        manager.createUser(User.withUsername("admin").password("admin").authorities("MANAGE").build());
////        return manager;
////    }
////
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.authorizeRequests()
//////                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll() // 这句比较重要，放过 option 请求
////                .antMatchers("/","/index","/login").permitAll() // "/" 的请求可以通过
////                .antMatchers("/manage").hasAnyAuthority("MANAGE")
////                .anyRequest().authenticated() // 除了"/" 其他的请求必须认证通过
////                .and()
////                .formLogin() // 允许表单登录
//////                .loginPage("/login")
//////                .loginProcessingUrl("/api/login")
//////                .successForwardUrl("/manage")
////                .permitAll()
////
//////                // 无权访问是返回 json 格式数据。
//////                .and().httpBasic().authenticationEntryPoint(new AuthenticationEntryPoint() {
//////            @Override
//////            public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
//////                                 AuthenticationException e) throws IOException, ServletException {
//////                ServiceResult rm = ServiceResult.error(new ServiceResult.DefaultMessage<>(401, "need login"));
//////                httpServletResponse.getWriter().write(JSON.toJSONString(rm));
//////            }
//////        })
//////
//////                // 登录响应
//////                .and().formLogin().failureHandler(new AuthenticationFailureHandler() {
//////            @Override
//////            public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp,
//////                                                AuthenticationException e) throws IOException {
//////                resp.setContentType("application/json;charset=utf-8");
////////                ServiceResult rm = null;
////////                if (e instanceof BadCredentialsException || e instanceof UsernameNotFoundException) {
////////                    rm = ServiceResult.error(new ServiceResult.DefaultMessage<>(401, "账户名或者密码输入错误!"));
////////                } else if (e instanceof LockedException) {
////////                    rm = ServiceResult.error(new ServiceResult.DefaultMessage<>(401, "账户被锁定，请联系管理员!"));
////////                } else if (e instanceof CredentialsExpiredException) {
////////                    rm = ServiceResult.error(new ServiceResult.DefaultMessage<>(401, "密码过期，请联系管理员!"));
////////                } else if (e instanceof AccountExpiredException) {
////////                    rm = ServiceResult.error(new ServiceResult.DefaultMessage<>(401, "账户过期，请联系管理员!"));
////////                } else if (e instanceof DisabledException) {
////////                    rm = ServiceResult.error(new ServiceResult.DefaultMessage<>(401, "账户被禁用，请联系管理员!"));
////////                } else {
////////                    rm = ServiceResult.error(new ServiceResult.DefaultMessage<>(401, "登录失败!"));
////////                }
//////                resp.setStatus(401);
//////                PrintWriter out = resp.getWriter();
////////                out.write(JSON.toJSONString(rm));
//////                out.flush();
//////                out.close();
//////            }
//////        })
////        .successHandler(new AuthenticationSuccessHandler() {
////            @Override
////            public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth)
////                    throws IOException {
////                resp.setContentType("application/json;charset=utf-8");
////                Object curUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
////                ServiceResult rm = ServiceResult.success(curUser);
////                resp.getWriter().write(JSON.toJSONString(rm));
////            }
////        }).permitAll()
//////
//////                // 退出登录
//////                .and().logout().logoutSuccessHandler(new LogoutSuccessHandler() {
//////            @Override
//////            public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication)
//////                    throws IOException, ServletException {
//////                resp.setContentType("application/json;charset=utf-8");
//////                ServiceResult rm = ServiceResult.success("注销成功!");
//////                PrintWriter out = resp.getWriter();
//////                out.write(JSON.toJSONString(rm));
//////                out.flush();
//////                out.close();
//////            }
//////        }).permitAll()
////
////                // 这里还是要关闭crsf因为crsf还有token之类的安全防护
////                // 这就是这种方式不安全的原因吧
////                .and().csrf().disable();
////    }
////
////
////
//////    @Override
//////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//////        // 这里做用户登录判断用户名密码
//////        auth.userDetailsService(new UserDetailsService() {
//////            @Override
//////            public User loadUserByUsername(String username) throws UsernameNotFoundException {
//////                System.out.println("进入");
//////                UserEntity user = userDao.findByUsername(username); // 从你的数据库中取出用户
//////                if (user == null) {
//////                    throw new UsernameNotFoundException("用户名不存在");
//////                }
//////                return new User(user.getUsername(),passwordEncoder.encode(user.getPassword()),
//////                        AuthorityUtils.commaSeparatedStringToAuthorityList("LOGIN"));
//////            }
//////        });
//////    }
////
////
////}
