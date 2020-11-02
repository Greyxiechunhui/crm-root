package com.crm.cn.async;


import com.crm.cn.email.EmailService;
import com.crm.cn.entity.SysLoginLog;
import com.crm.cn.service.ISysLoginService;
import com.crm.cn.spring.SpringUtils;
import com.crm.cn.useragent.ServletUtils;
import eu.bitwalker.useragentutils.UserAgent;

import java.time.LocalDateTime;

/**
 * 异步任务生产工厂
 */
public class AsyncFactory {


    public static Runnable executeEmail(final String to, final String message) {
        Runnable runnable = new Runnable() {
            public void run() {
                SpringUtils.getBean(EmailService.class).sendMail(to, message);
            }
        };
        return runnable;
    }


    /**
     * 记录登录
     */
    public static Runnable executeLoginLog(String status,String msg,String userName) {

        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getUserAgentString(ServletUtils.getRequest()));

        String sysName = userAgent.getOperatingSystem().getName();
        String browserName = userAgent.getBrowser().getName();
        String ipAddr = ServletUtils.getIpAddr(ServletUtils.getRequest());
        String loginLocation = ServletUtils.getLoginLocation(ServletUtils.getRequest());
        ISysLoginService iSysLoginService = SpringUtils.getBean(ISysLoginService.class);

        Runnable runnable = () ->{
            SysLoginLog loginLog = new SysLoginLog();
            loginLog.setLoginLocation(loginLocation);
            loginLog.setBrowser(browserName);
            loginLog.setOs(sysName);
            loginLog.setIpaddr(ipAddr);
            loginLog.setLoginTime(LocalDateTime.now());
            loginLog.setStatus(status);
            loginLog.setMsg(msg);
            loginLog.setUserName(userName);
            iSysLoginService.add(loginLog);
        };

        return runnable;

    }
}
