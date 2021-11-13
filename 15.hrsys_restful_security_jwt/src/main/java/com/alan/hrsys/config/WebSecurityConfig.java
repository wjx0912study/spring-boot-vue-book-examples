package com.alan.hrsys.config;

import com.alan.hrsys.service.impl.UserDetailsServiceImpl;
import com.alan.hrsys.util.ServerResponse;
import com.alan.hrsys.util.jwt.JwtAuthenticationTokenFilter;
import com.alan.hrsys.util.jwt.JwtLoginFailureHandler;
import com.alan.hrsys.util.jwt.JwtLoginSuccessHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)  //  启用方法级别的权限认
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private JwtLoginSuccessHandler jwtLoginSuccessHandler;
    @Autowired
    private JwtLoginFailureHandler jwtLoginFailureHandler;

    //方法注解方式
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //JWT过滤器
        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
        //将JWT拦截器添加到UsernamePasswordAuthenticationFilter之前
        http. addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginProcessingUrl("/doLogin")
                .successHandler(jwtLoginSuccessHandler)
                .failureHandler(jwtLoginFailureHandler)
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedHandler(new AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
                printCode(httpServletResponse, 3);
            }
        })
                .and().headers().frameOptions().sameOrigin()
                .and().cors()
                .and().csrf().disable()
                //使用JWT，关闭默认的Session实现会话机制，这样会不产生Cookie
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(WebSecurity web) {
        // 设置拦截忽略文件夹，对静态资源放行
        web.ignoring().antMatchers("/bootstrap/**", "/js/**");
    }

    // 加入自定义的安全认证:数据库用户
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }

    private void printCode(HttpServletResponse httpServletResponse, Integer code) {
        try {
            httpServletResponse.setCharacterEncoding("UTF-8");
            PrintWriter out = httpServletResponse.getWriter();
            ServerResponse sr = new ServerResponse<>();
            sr.setCode(code);
            ObjectMapper mapper = new ObjectMapper();
            String str = mapper.writeValueAsString(sr);
            out.write(str);
            out.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}