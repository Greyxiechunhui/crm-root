package com.crm.cn.login;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.crm.cn.cahce.CacheService;
import com.crm.cn.entity.LoginUser;
import com.crm.cn.entity.SysUser;
import com.crm.cn.exception.LoginException;
import com.crm.cn.http.AxiosStatus;
import com.crm.cn.useragent.ServletUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Component
public class TokenService {

    //secret 春辉真球帅
    private String secret = "6ed220c641d5e6696d22cc25d667c10b";

    private long exipireTime = 1000 * 60 * 60 * 24 * 5;

    @Autowired
    private CacheService cacheService;

    /**
     * 封装LoginUser信息
     *
     * @param
     * @return
     */
    public String createTokenAndLoginUser(SysUser sysUser) {
        LoginUser loginUser = new LoginUser();
        loginUser.setSysUser(sysUser);
        loginUser.setUuid(UUID.randomUUID().toString());
        //登录时间
        loginUser.setLoginTime(System.currentTimeMillis());
        setLoginUserAgent(loginUser);
        //设置登录时间
        setExpireTime(loginUser);
        cacheLoginUser(loginUser);
        return createToken(loginUser.getUuid());

    }

    public void setLoginUserAgent(LoginUser loginUser) {
        loginUser.setIpAddr(ServletUtils.getIpAddr(ServletUtils.getRequest()));
        loginUser.setLoginLocation(ServletUtils.getLoginLocation(ServletUtils.getRequest()));
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getUserAgentString(ServletUtils.getRequest()));
        loginUser.setOs(userAgent.getOperatingSystem().getName());
        loginUser.setBrowserName(userAgent.getBrowser().getName());

    }

    /**
     * 设置登录时间 有效时间
     */

    public void setExpireTime(LoginUser loginUser) {

        //过期时间
        loginUser.setPreviousTime(System.currentTimeMillis());
        //设置有效时间
        loginUser.setExpireTime(System.currentTimeMillis() + exipireTime);

    }

    /**
     * 缓存LoginUser
     *
     * @param loginUser
     */
    public void cacheLoginUser(LoginUser loginUser) {
        cacheService.cacheLoginUser(loginUser.getUuid(), loginUser, exipireTime);
    }


    /**
     * 创建token值
     *
     * @param
     * @return
     */
    public String createToken(String uuid) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        String token = JWT.create()
                .withIssuer("南方联盟")
                .withClaim("uuid", uuid)
                .sign(algorithm);

        return token;
    }

    /**
     * 解析Token
     *
     * @param token
     * @return
     */

    public DecodedJWT vertifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("南方联盟")
                .build();

        DecodedJWT jwt = verifier.verify(token);
        return jwt;
    }

    /**
     * 获取loginUser
     * 有效时间到11点
     * <p>
     * 请求  当前时间 和  有效时间比较  这个比较  如果当前时间  和  有效时间 差距半个小时
     * 刷新Token
     * <p>
     * 当前时间 和登录时间  比较 低于5分钟  失效 每一次操作 都要刷新一次登录时间
     *
     * @param httpServletRequest
     * @return
     */

    public LoginUser getLoginUser(HttpServletRequest httpServletRequest) {
        String token = getToken(httpServletRequest);
        String tokenUUId = getTokenUUId(token);
        LoginUser cacheLoginUser = cacheService.getCacheLoginUser(tokenUUId);
        if(cacheLoginUser==null){
            throw new LoginException(AxiosStatus.TOLEM_VALID_FAILURE);
        }
        return cacheLoginUser;
    }


    /**
     * 获得Token的值
     */
    public String getTokenUUId(String tokenStr) {
        DecodedJWT decodedJWT = vertifyToken(tokenStr);
        return decodedJWT.getClaim("uuid").asString();
    }

    /**
     * 获得Token
     */

    public String getToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        return authorization.split(" ")[1];
    }

    /**
     * 如果redis的有效时间和当前时间差距30分钟以内 就刷新Redis的时间
     */
    public void refreshLoginUserToken(HttpServletRequest httpServletRequest) {
        LoginUser loginUser = getLoginUser(httpServletRequest);

        if (loginUser.getExpireTime() - System.currentTimeMillis() < 1000 * 60 * 30) {
            this.setExpireTime(loginUser);
            this.cacheLoginUser(loginUser);
        }

    }


    /**
     * 验证Token是否有效
     *
     * @param request
     * @return
     */
    public boolean tokenAuthorization(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(authorization) && authorization.startsWith("Bearer")) {
            String token = authorization.split(" ")[1];
            DecodedJWT decodedJWT = vertifyToken(token);
            if (decodedJWT == null) {
                //token解析错误
                return false;
            } else {

            }

        } else {
            return false;
        }

        refreshLoginUserToken(request);
        return true;
    }

    /**
     * 退出功能
     *
     * @param request
     */
    public void removeLoginUser(HttpServletRequest request) {
        LoginUser loginUser = this.getLoginUser(request);
        cacheService.removeCache(loginUser.getUuid());
    }

}


