package com.crm.cn.login;

import com.crm.cn.entity.LoginUser;
import com.crm.cn.entity.SysMenu;
import com.crm.cn.entity.SysUser;
import com.crm.cn.http.AxiosResult;
import com.crm.cn.mapper.SysLoginLogMapper;
import com.crm.cn.useragent.ServletUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private SysLoginLogMapper sysLoginLogMapper;

    @Autowired
    private TokenService tokenService;

    @PostMapping("login")
    public AxiosResult login(@RequestBody Map<String, String> map) {
        String userName = map.get("userName");
        String password = map.get("password");


        return loginService.doLogin(userName, password);
    }

    /**
     * 获取用户的信息 和用户的权限 和用户的动态菜单
     */

    @GetMapping("userInfo")
    public AxiosResult getUserInfo() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser sysUser =  loginUser.getSysUser();
        Long userId =  sysUser.getUserId();

        List<SysMenu> userRouter = loginService.findUserRouter(userId);
        List<SysMenu> perm = loginService.findUserBtnPerm(userId);
        Map<String,Object> map = new HashMap<>();
        map.put("user",sysUser);
        map.put("router",userRouter);
        map.put("perm",perm);
        loginUser.setPerms(perm);
        tokenService.cacheLoginUser(loginUser);
        return AxiosResult.success(map);
    }
}
