package com.crm.cn.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crm.cn.async.AsyncFactory;
import com.crm.cn.async.AsyncManager;
import com.crm.cn.email.EmailService;
import com.crm.cn.entity.SysRole;
import com.crm.cn.entity.SysUser;
import com.crm.cn.http.AxiosResult;
import com.crm.cn.service.ISysUserRoleService;
import com.crm.cn.service.ISysUserService;
import com.crm.cn.valid.group.AddGroup;
import com.crm.cn.valid.group.UpdateGroup;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 春辉真球帅
 * @since 2020-10-17
 */
@RestController
@RequestMapping("user")
public class SysUserController {
    @Autowired
    private ISysUserService ISysUserService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Autowired
    private ISysUserRoleService sysUserRoleService;

    /**
     * 查询所有
     * <p>
     * 商品 要用分类信息
     *
     * @return
     */
    @GetMapping
    public AxiosResult findAll() {
        return AxiosResult.success(ISysUserService.findAll());
    }

    /**
     * 分页查询
     */
    @GetMapping("page")
    public AxiosResult pageList(@RequestParam(defaultValue = "1") int currentPage,
                                @RequestParam(defaultValue = "5") int pageSize) {
        Page<SysUser> page = new Page<SysUser>(currentPage, pageSize);
        return AxiosResult.success(ISysUserService.pageList(page));
    }

    /*
     * Hibernate Validator
     *
     * 彩虹表撞库
     * */
    @PostMapping
    public AxiosResult add(@Validated(AddGroup.class) @RequestBody SysUser SysUser) throws Exception {
        SysUser.setPassword(bCryptPasswordEncoder.encode("123456"));
        System.out.println(SysUser);
        String[] roleIds = SysUser.getRoleIds().split("A");

        ISysUserService.add(SysUser);
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        Template template = configuration.getTemplate("user-ftl.html", "utf-8");
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", SysUser.getUserId() + "");
        map.put("userName", SysUser.getUserName());
        map.put("email", SysUser.getEmail());
        map.put("phone", SysUser.getPhone());
        map.put("password", "123456");
        StringWriter writer = new StringWriter();
        template.process(map, writer);
//        new Thread(){
//            @Override
//            public void run(){
//                emailService.sendMail(SysUser.getEmail(),writer.toString());
//            }
//        }.start();

        //发送邮件
//        emailService.sendMail(SysUser.getEmail(),writer.toString());
        AsyncManager.getInstance().executeTask(AsyncFactory.executeEmail(SysUser.getEmail(), writer.toString()));
        return AxiosResult.success();
    }


    @PutMapping
    public AxiosResult update(@Validated(UpdateGroup.class) @RequestBody SysUser SysUser) {


        System.out.println(SysUser);
        ISysUserService.update(SysUser);
        return AxiosResult.success();
    }

    @GetMapping("{id}")
    public AxiosResult findById(@PathVariable Serializable id) {
        return AxiosResult.success(ISysUserService.findById(id));
    }

    @DeleteMapping("{id}")
    public AxiosResult deleteById(@PathVariable Serializable id) {
        ISysUserService.deleteById(id);
        return AxiosResult.success();
    }

    @GetMapping("{userId}/roles")
    public AxiosResult getRoleByUserId(@PathVariable Serializable userId) {
        List<SysRole> roleByUserId = sysUserRoleService.findRoleByUserId(userId);

        return AxiosResult.success(roleByUserId);
    }

    @DeleteMapping("{userId}/role/{roleId}")
    public AxiosResult getRoleByUserId(@PathVariable Serializable userId, @PathVariable Serializable roleId) {
        sysUserRoleService.deleteByUserIdAndRoleId(userId, roleId);
        return AxiosResult.success();
    }


}
