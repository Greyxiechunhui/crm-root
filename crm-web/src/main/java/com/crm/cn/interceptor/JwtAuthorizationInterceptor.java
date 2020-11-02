package com.crm.cn.interceptor;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.crm.cn.login.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtAuthorizationInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        boolean b = tokenService.tokenAuthorization(request);
        System.out.println(b);

        if(!b){
            throw new JWTVerificationException("token认证失败");
        }



        return true;
    }
}
