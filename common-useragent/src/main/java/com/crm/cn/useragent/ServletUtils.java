package com.crm.cn.useragent;
import com.crm.cn.spring.SpringUtils;
import com.crm.cn.useragent.bean.LgoinLocationBean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class ServletUtils {
    /**
     * 获取ip地址
     * @param request
     * @return
     */

    public static String getIpAddr(HttpServletRequest request){
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if("0:0:0:0:0:0:0:1".equalsIgnoreCase(ip)){
            return "127.0.0.1";
        }
        return ip;
    }

    /**
     * 获取真实地址
     * @param request
     * @return
     */

    public static String getLoginLocation(HttpServletRequest request){
        String ipAddr = getIpAddr(request);
        RestTemplate bean = SpringUtils.getBean(RestTemplate.class);
        LgoinLocationBean lgoinLocationBean = bean.getForObject("http://apis.juhe.cn/ip/ipNew?ip=" + ipAddr + "&key=f1b0d472b1cd000fa239bc7cb0280d7e", LgoinLocationBean.class);

        if("200".equalsIgnoreCase(lgoinLocationBean.getResultcode())){
            Map<String,String> result = lgoinLocationBean.getResult();
            String country = result.get("Country");
            String province = result.get("Province");
            String city = result.get("City");
            return country+province+city;
        }

        return "未知地区,卧龙凤雏";

    }

    /**
     * 获取HttpServletRequset
     *
     *
     */
    public static HttpServletRequest getRequest(){
        return getServletRequestAttributes().getRequest();
    }

    public static HttpServletResponse getResponse(){
        return getServletRequestAttributes().getResponse();
    }

    public static ServletRequestAttributes getServletRequestAttributes(){
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    public static String getUserAgentString(HttpServletRequest request){
        return request.getHeader("User-Agent");
    }

    public static void returnJsonStr(String message){
        HttpServletResponse response =getResponse();
        response.setContentType("application/json;charset=utf-8");
        try {
            response.getWriter().write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
