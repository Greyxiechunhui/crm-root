package com.crm.cn.login;
import com.crm.cn.async.AsyncFactory;
import com.crm.cn.async.AsyncManager;
import com.crm.cn.entity.SysMenu;
import com.crm.cn.entity.SysUser;
import com.crm.cn.http.AxiosResult;
import com.crm.cn.http.AxiosStatus;
import com.crm.cn.service.ISysLoginService;
import com.crm.cn.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.util.List;

@Component
public class LoginService {

    @Autowired
    private ISysUserService iSysUserService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ISysLoginService iSysLoginService;

    @Autowired
    private TokenService tokenService;

    /**
     * 登录操作
     *
     * @param userName
     * @param password
     * @return
     */
    public AxiosResult doLogin(String userName, String password) {
        if (StringUtils.isEmpty(userName)) {
            AsyncManager.getInstance().executeTask(AsyncFactory.executeLoginLog("1", AxiosStatus.USERNAME_NOT_EMPTY.getMessage(), userName));
            return AxiosResult.error(AxiosStatus.USERNAME_NOT_EMPTY);
        }

        if (StringUtils.isEmpty(password)) {

            AsyncManager.getInstance().executeTask(AsyncFactory.executeLoginLog("1", AxiosStatus.PASSWORD_NOT_EMPTY.getMessage(), userName));
            return AxiosResult.error(AxiosStatus.PASSWORD_NOT_EMPTY);
        }
        //通过用户名查询密码
        SysUser sysUser = iSysUserService.getUserByUserName(userName);
        if (sysUser == null) {
            //用户名不正确
            AsyncManager.getInstance().executeTask(AsyncFactory.executeLoginLog("1", AxiosStatus.USERNAME_NOT_SURE.getMessage(), userName));
            return AxiosResult.error(AxiosStatus.USERNAME_NOT_SURE);
        }

        boolean matches = bCryptPasswordEncoder.matches(password, sysUser.getPassword());
        if (!matches) {
            //密码不正确
            AsyncManager.getInstance().executeTask(AsyncFactory.executeLoginLog("1", AxiosStatus.PASSWORD_NOT_SURE.getMessage(), userName));
            return AxiosResult.error(AxiosStatus.PASSWORD_NOT_SURE);
        }


        AsyncManager.getInstance().executeTask(AsyncFactory.executeLoginLog("0", "登录成功", userName));

        String token = tokenService.createTokenAndLoginUser(sysUser);
        return AxiosResult.success(token);
    }

    /**
     * 树形菜单
     *
     * @param userId
     */
    public List<SysMenu> findUserRouter(Long userId) {

        List<SysMenu> userRouter = iSysUserService.findUserRouter(userId);
        return userRouter;
    }

    /**
     * 获取用户按钮权限
     * @param userId
     * @return
     */

    public List<SysMenu> findUserBtnPerm(Long userId) {

        List<SysMenu> userRouter = iSysUserService.findUserBtnPerm(userId);
        return userRouter;
    }
}
