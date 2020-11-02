package com.crm.cn.exception;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.crm.cn.http.AxiosResult;
import com.crm.cn.http.AxiosStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JWTVerificationException.class)
    public AxiosResult myExceptionHandler(JWTVerificationException e){
        return AxiosResult.success(AxiosStatus.TOLEM_VALID_FAILURE);
    }

    @ExceptionHandler(LoginException.class)
    public AxiosResult myExceptionHandler(LoginException e){
        return AxiosResult.success(e.getAxiosStatus());
    }

    @ExceptionHandler(Throwable.class)
    public AxiosResult myExceptionHandler(Throwable e){
        return AxiosResult.success(AxiosStatus.WEB_ERROR);
    }
}
