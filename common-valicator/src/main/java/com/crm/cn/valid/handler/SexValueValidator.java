package com.crm.cn.valid.handler;

import com.crm.cn.valid.anntation.SexValues;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class SexValueValidator  implements ConstraintValidator<SexValues,String> {

    List<String> list;
    /*
    * 初始化
    * */
    @Override
    public void initialize(SexValues constraintAnnotation) {
        //表示注解上指定的值
        String[] values = constraintAnnotation.values();
        list = Arrays.asList(values);

    }
    /*
    * 验证
    * */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        //value  表示你给我传的值
//        System.out.println(value);
        return list.contains(value);
    }
}
