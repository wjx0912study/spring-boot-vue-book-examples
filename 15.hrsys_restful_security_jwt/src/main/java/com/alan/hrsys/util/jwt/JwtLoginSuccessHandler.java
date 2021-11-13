package com.alan.hrsys.util.jwt;

import com.alan.hrsys.util.ServerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功处理
 */
@Component("jwtLoginSuccessHandler")
public class JwtLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    //Jackson提供的JSON转换类ObjectMapper,可以用Spring自动注入
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse, Authentication authentication) throws
            IOException, ServletException {
        //登录成功后,生成Token
        String token = JwtUtils.generateToken(authentication);
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        //将token信息写入ServerResponse
        ServerResponse sr = new ServerResponse();
        sr.setCode(1);
        sr.setAuthorization(token);
        //转换成JSON
        String s = objectMapper.writeValueAsString(sr);
        //响应给浏览器
        httpServletResponse.getWriter().write(s);
    }
}