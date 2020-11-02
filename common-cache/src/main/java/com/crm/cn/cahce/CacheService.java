package com.crm.cn.cahce;

import com.crm.cn.entity.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CacheService {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private JSONUtils jsonUtils;

    /**
     * 缓存对象
     *
     * @param key
     * @param obj
     */

    public void cacheObj(String key, Object obj) {
        stringRedisTemplate.opsForValue().set(key, jsonUtils.obj2Str(obj));
    }


    /**
     * 缓存对象带有效时间的
     *
     * @param key
     * @param obj
     */

    public void cacheObj(String key, Object obj, long expireTime) {
        stringRedisTemplate.opsForValue().set(key, jsonUtils.obj2Str(obj), expireTime, TimeUnit.MILLISECONDS);
    }

    public <T> T getCacheObj(String key, Class<T> clazz) {
        String s = stringRedisTemplate.opsForValue().get(key);
        T t = jsonUtils.str2Obj(s, clazz);
        return t;
    }

    /**
     * 缓存登录对象
     */
    public void cacheLoginUser(String key, LoginUser loginUser) {
        this.cacheObj(key, loginUser);
    }

    /**
     * 缓存登录对象时间的
     */
    public void cacheLoginUser(String key, LoginUser loginUser, long expireTime) {
        this.cacheObj(key, loginUser, expireTime);
    }

    public LoginUser getCacheLoginUser(String key) {
     return  this.getCacheObj(key,LoginUser.class);
    }

    /**
     * 删除缓存
     */
    public void removeCache(String key){
        stringRedisTemplate.delete(key);
    }
}
