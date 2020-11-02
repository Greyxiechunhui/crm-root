package com.crm.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crm.cn.entity.*;
import com.crm.cn.http.AxiosResult;
import com.crm.cn.http.PageResult;
import com.crm.cn.mapper.*;
import com.crm.cn.mapper.SysUserMapper;
import com.crm.cn.service.*;
import com.crm.cn.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 春辉真球帅
 * @since 2020-10-17
 */
@Service
@Transactional
//@Log4j
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper SysUserMapper;
    @Autowired
    private ISysUserRoleService iSysUserRoleService;

    @Autowired
    private ISysRoleMenuService iSysRoleMenuService;

    @Autowired
    private ISysMenuService iSysMenuService;


    public List<SysUser> findAll() {
        return SysUserMapper.selectList(null);
    }


    public PageResult pageList(IPage<SysUser> page) {
        IPage<SysUser> SysUserIPage = SysUserMapper.selectPage(page, null);
        List<SysUser> records = SysUserIPage.getRecords();
        long total = SysUserIPage.getTotal();

        return PageResult.instance(records, total);
    }

    public SysUser findById(Serializable id) {
        return SysUserMapper.selectById(id);
    }

    public void add(SysUser entity) {
        SysUserMapper.insert(entity);
        //添加用户的角色
        String[] as = entity.getRoleIds().split("A");

        Arrays.asList(as).forEach(item -> {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(entity.getUserId());
            sysUserRole.setRoleId(Long.parseLong(item));
            iSysUserRoleService.add(sysUserRole);
        });


    }

    public void update(SysUser entity) {
        SysUserMapper.updateById(entity);
        //先删除完再添加
        iSysUserRoleService.deleteRoleByUserId(entity.getUserId());

        //添加用户的角色
        String[] as = entity.getRoleIds().split("A");
        Arrays.asList(as).forEach(item -> {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(entity.getUserId());
            sysUserRole.setRoleId(Long.parseLong(item));
            iSysUserRoleService.add(sysUserRole);
        });
    }

    public void deleteById(Serializable id) {
        SysUserMapper.deleteById(id);
    }

    @Override
    public SysUser getUserByUserName(String userName) {

        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUser::getUserName, userName);
        SysUser sysUser = SysUserMapper.selectOne(queryWrapper);
        return sysUser;
    }

    @Override
    public List<SysMenu> findUserAllMenu(Long userId) {
        List<SysMenu> list = new ArrayList<>();
        List<SysRole> allRoles = iSysUserRoleService.findRoleByUserId(userId);

        allRoles.forEach(sysRole -> {
            List<SysRoleMenu> sysRoleMenuByRoleId = iSysRoleMenuService.findSysRoleMenuByRoleId(sysRole.getRoleId());
            sysRoleMenuByRoleId.forEach(sysRoleMenu -> {
                SysMenu byId = iSysMenuService.findById(sysRoleMenu.getMenuId());
                if (byId != null) {
                    list.add(byId);
                }
            });

        });

        return list;
    }

    @Override
    public List<SysMenu> findUserRouter(Long userId) {
        List<SysMenu> allMenu = findUserAllMenu(userId);
        List<SysMenu> menus = allMenu.stream().filter(sysMenu -> !sysMenu.getMenuType().equalsIgnoreCase("F")).collect(Collectors.toList());
        List<SysMenu> rootMenu = menus.stream().filter(sysMenu -> sysMenu.getParentId().longValue() == 0).collect(Collectors.toList());
        rootMenu.forEach(sysMenu -> {
            findChildren(sysMenu, menus);
        });


        return rootMenu;
    }

    public void findChildren(SysMenu sysMenu, List<SysMenu> menus) {

        List<SysMenu> collect = menus.stream().filter(sysMenu1 -> sysMenu1.getParentId().longValue() == sysMenu.getMenuId().longValue()).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(collect)) {
            sysMenu.setChildren(collect);
        }

        collect.forEach(sysMenu1 -> {
            findChildren(sysMenu1, menus);
        });
    }

    @Override
    public List<SysMenu> findUserBtnPerm(Long userId) {
        List<SysMenu> allMenu = findUserAllMenu(userId);
        List<SysMenu> menus = allMenu.stream().filter(sysMenu -> sysMenu.getMenuType().equalsIgnoreCase("F")).collect(Collectors.toList());
        return menus;
    }


}
