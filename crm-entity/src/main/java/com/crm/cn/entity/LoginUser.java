package com.crm.cn.entity;

import lombok.Data;

import java.util.List;

@Data
public class LoginUser {

    private String uuid;

    private SysUser sysUser;

    private String os;

    private String ipAddr;

    private String browserName;

    private String loginLocation;

    private long loginTime;

    private long expireTime;

    private long previousTime;

    private List<SysMenu> perms;
}
