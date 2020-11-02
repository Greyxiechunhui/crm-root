package com.crm.cn.annatation;

import com.crm.cn.cahce.JSONUtils;
import com.crm.cn.entity.LoginUser;
import com.crm.cn.entity.SysMenu;
import com.crm.cn.http.AxiosResult;
import com.crm.cn.http.AxiosStatus;
import com.crm.cn.login.TokenService;
import com.crm.cn.useragent.ServletUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

@Aspect
@Component
public class PermAspect {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private JSONUtils jsonUtils;

    @Pointcut("@annotation(com.crm.cn.annatation.HasPerm)")
    public void myPointCut(){

    }
    /*
    * 请求路径 请求参数 谁请求的  请求的相应内容 哪个类  哪个方法
    *
    * */


    @Before(value = "myPointCut()")
    public void beforePerm(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature  = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        HasPerm declaredAnnotation = method.getDeclaredAnnotation(HasPerm.class);
        if(declaredAnnotation!=null){
            System.out.println(declaredAnnotation.perm());
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            List<SysMenu> perms = loginUser.getPerms();

            boolean b = perms.stream().anyMatch(sysMenu -> sysMenu.getPerms().equalsIgnoreCase(declaredAnnotation.perm()));
            if(!b){
                //
                ServletUtils.returnJsonStr(jsonUtils.obj2Str(AxiosResult.success(AxiosStatus.TOLEM_VALID_FAILURE)));
            }
//            System.out.println(declaredAnnotation.perm());
//            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
//
//            List<SysMenu> perms = loginUser.getPerms();
//
//            boolean b = perms.stream().anyMatch(sysMenu -> sysMenu.getPerms().equalsIgnoreCase(declaredAnnotation.perm()));
//
//            if(!b){
//                //建议使用原生的方式
//
//                ServletUtils.returnJsonStr(jsonUtils.obj2Str(AxiosResult.success(AxiosStatus.TOLEM_VALID_FAILURE)));
//            }
        }

    }
}
