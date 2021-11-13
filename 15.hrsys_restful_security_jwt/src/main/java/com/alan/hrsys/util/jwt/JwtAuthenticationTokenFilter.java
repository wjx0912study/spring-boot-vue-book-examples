package com.alan.hrsys.util.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//过滤器，获取Token
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //获取Token
        String token= request.getHeader("Authorization");
        //Token不为空则交给JwtUtils去验证Token
        if (token!= null) {
            JwtUtils.tokenParser(token);
        }
        filterChain.doFilter(request, response);
    }
}