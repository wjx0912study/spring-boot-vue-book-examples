package com.alan.hrsys.util.jwt;

import com.alan.hrsys.util.ServerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 *登录失败处理
 */
@Component("jwtLoginFailureHandler")
public class JwtLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,HttpServletResponse response, AuthenticationException exception) throws IOException,ServletException {
        //设置返回类型
        response.setContentType("application/json;charset=UTF-8");
        //将错误信息写入ServerResponse
        ServerResponse sr=new ServerResponse();
        sr.setCode(2);
        //返回JSON
        String s = objectMapper.writeValueAsString(sr);
        response.getWriter().write(s);
    }
}