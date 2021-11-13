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
  //  按照权限config
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
                .logout().permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/index").hasAnyAuthority("common")
                .antMatchers("/sysUser/**").hasAnyAuthority("sysUser")
                .antMatchers( "/sysRole/**").hasAnyAuthority("sysRole")
                .antMatchers( "/sysPermission/**").hasAnyAuthority("sysPermission")
                .antMatchers("/dep/**").hasAnyAuthority("department")
                .antMatchers("/emp/**").hasAnyAuthority("employee")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/roleError")
                .and().headers().frameOptions().sameOrigin()
                .and().csrf().disable();//不加，登录的时候会302一直在登录页面上
    }

    //按照角色划分
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin()
//                //自定义登录页面
//                .loginPage("/login")
//                .loginProcessingUrl("/doLogin")
//                .defaultSuccessUrl("/index")
//                .failureUrl("/loginError")
//                .permitAll()
//                .and()
//                .logout().permitAll()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/sysUser/**", "/sysRole/**", "/sysPermission/**").hasAnyRole("ADMIN")
//                .antMatchers("/dep/**").hasAnyRole("ADMIN", "MANAGER")
//                .antMatchers("/index").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
//                .antMatchers("/emp/**").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")
//                .anyRequest().authenticated()
//                .and()
//                .exceptionHandling().accessDeniedPage("/roleError")
//                .and().headers().frameOptions().sameOrigin()
//                .and().csrf().disable();//不加，登录的时候会302一直在登录页面上
//    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin()
//                //自定义登录页面
//                .loginPage("/login")
//                .loginProcessingUrl("/doLogin")
//                .defaultSuccessUrl("/index")
//                .failureUrl("/loginError")
//                .permitAll()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/dep/**").hasRole("ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .exceptionHandling().accessDeniedPage("/roleError")
//                .and().headers().frameOptions().sameOrigin()
//                .and().csrf().disable();//不加，登录的时候会302一直在登录页面上
//    }


    @Override
    public void configure(WebSecurity web) {
        // 设置拦截忽略文件夹，对静态资源放行
        web.ignoring().antMatchers("/bootstrap/**", "/js/**");
    }
}