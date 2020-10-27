package com.crm.cn.async;


import com.crm.cn.email.EmailService;
import com.crm.cn.spring.SpringUtils;

/**
 * 异步任务生产工厂
 */
public class AsyncFactory {



    public static Runnable executeEmail(final String to, final String message){
        Runnable runnable = new Runnable() {
            public void run() {
                SpringUtils.getBean(EmailService.class).sendMail(to, message);
            }
        };
        return runnable;
    }
}
