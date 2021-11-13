package com.alan.hrsys.config;

import com.alan.hrsys.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private PasswordEncoder encoder = new BCryptPasswordEncoder();


    // 加入自定义的安全认证:数据库用户
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                //自定义登录页面
                .loginPage("/login")
                .loginProcessingUrl("/doLogin")
                .defaultSuccessUrl("/index")
                .failureUrl("/loginError")
                .permitAll()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/roleError")
                .and().headers().frameOptions().sameOrigin()
                .and().csrf().disable();//不加，登录的时候会302一直在登录页面上
    }


    @Override
    public void configure(WebSecurity web) {
        // 设置拦截忽略文件夹，对静态资源放行
        web.ignoring().antMatchers("/bootstrap/**", "/js/**");
    }

    //ajax返回消息
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin()
//                //自定义登录页面
//                .loginPage("/login")
//                .loginProcessingUrl("/doLogin")
//                .successHandler(new AuthenticationSuccessHandler() {
//                    @Override
//                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//                        httpServletRequest.getRequestDispatcher("/index").forward(httpServletRequest, httpServletResponse);
//                    }
//                })
//                .failureHandler(new AuthenticationFailureHandler() {
//                    @Override
//                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//                        httpServletResponse.setCharacterEncoding("UTF-8");
//                        PrintWriter out = httpServletResponse.getWriter();
//                        out.print("失败");
//                        out.close();
//                    }
//                })
//                .permitAll()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/index").hasRole("ddd")
//                .anyRequest().authenticated()
//                .and().headers().frameOptions().sameOrigin()
//                .and().csrf().disable();//不加，登录的时候会302一直在登录页面上
//    }


    //创建内存型用户
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().passwordEncoder(encoder)
//                .withUser("aaa").password("111").roles("ADMIN")
//                .and().withUser("bbb").password("111").roles("CUSTOMER");
//
//    }

    //定义加密策略，设置明文密码，即不使用加密技术
//    private PasswordEncoder encoder =
//            new PasswordEncoder() {
//                @Override
//                public String encode(CharSequence charSequence) {
//                    return charSequence.toString();
//                }
//
//                @Override
//                public boolean matches(CharSequence charSequence, String s) {
//                    return s.equals(charSequence.toString());
//                }
//            };
//定义加密策略，使用MD5加密
//    private PasswordEncoder encoder =
//            new PasswordEncoder() {
//                @Override
//                public String encode(CharSequence charSequence) {
//                    return MD5Util.encode(charSequence.toString());
//                }
//
//                @Override
//                public boolean matches(CharSequence charSequence, String s) {
//                    return s.equals(MD5Util.encode(charSequence.toString()));
//                }
//            };

}