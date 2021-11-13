package com.alan.hrsys.util.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;

/**
 * JWT工具类，提供生成Token和验证Token的两个方法
 */
public class JwtUtils {

    /**
     * 生成Token
     */
    public static String generateToken(Authentication authentication) {
        String token = Jwts.builder()
                //设置token的信息
                //将认证后的authentication写入token
                //authentication中包含登录用户的用户名、权限等信息
                .claim("authentication", authentication)
                //设置主题
                .setSubject("主题")
                //过期时间
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
                //加密方式及秘钥
                .signWith(SignatureAlgorithm.HS256, "alan")
                .compact();

        return token;
    }

    /**
     * 验证Token
     */
    public static void tokenParser(String token) {
        // 解析token.
        Claims claims = Jwts.parser()
                .setSigningKey("alan")
                .parseClaimsJws(token)
                .getBody();
        //获取过期时间
        Date claimsExpiration = claims.getExpiration();
        //判断是否过期
        Date now = new Date();
        if (now.getTime() > claimsExpiration.getTime()) {
            throw new AuthenticationServiceException("凭证已过期，请重新登录！");
        }
        //从claims中获取authentication，它是一个LinkedHashMap类型
        LinkedHashMap authenticationMap=  (LinkedHashMap<String, Object>) claims.get("authentication");
        //从authentication中获取用户名
        String username = (String)authenticationMap.get("name");
        //获取保存在token中的登录认证成功的权限(authentication)，并将其转换成List<GrantedAuthority>
        ArrayList<LinkedHashMap<String,String>> authenticationList = (ArrayList<LinkedHashMap<String,String>>) authenticationMap.get("authorities");
        //将ArrayList<LinkedHashMap<String,String>>转换成字符串数组
        String[] authenticationStr = new String[authenticationList.size()];
        for (int i = 0; i < authenticationList.size(); i++) {
            authenticationStr[i] = authenticationList.get(i).get("authority");
        }
        //将字符串数组权限转换成 List<GrantedAuthority>类型
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(authenticationStr);
        // 利用UsernamePasswordAuthenticationToken生成新的authentication
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
        // 放入到SecurityContextHolder，表示认证通过
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
