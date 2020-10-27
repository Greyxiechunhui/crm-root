package com.crm.cn.exception;

import com.crm.cn.http.AxiosResult;
import com.crm.cn.http.AxiosStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class CrmExceptionHandler {

    /*
    * 处理表单验证的异常
    * */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public AxiosResult validException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        Map<String,String> map = new HashMap<String, String>();
        if(bindingResult.hasFieldErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(fieldError->{
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            });
        }

        return AxiosResult.error(AxiosStatus.VALID_FAILURE,map);
    }
}
