package com.crm.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crm.cn.entity.SysMenu;
import com.crm.cn.entity.SysRole;
import com.crm.cn.entity.SysRoleMenu;
import com.crm.cn.http.PageResult;
import com.crm.cn.mapper.SysRoleMapper;
import com.crm.cn.service.ISysMenuService;
import com.crm.cn.service.ISysRoleMenuService;
import com.crm.cn.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class SysRoleServiceImpl implements ISysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private ISysRoleMenuService iSysRoleMenuService;

    @Autowired
    private ISysMenuService iSysMenuService;

    @Override
    public List<SysRole> findAll() {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
//        queryWrapper.orderByAsc("role_sort");
        queryWrapper.lambda().orderByAsc(SysRole::getRoleSort);
        List<SysRole> sysRoles = sysRoleMapper.selectList(queryWrapper);
        sysRoleMapper.selectList(null);
        return sysRoles;
    }

    @Override
    public PageResult pageList(IPage<SysRole> page) {
        return null;
    }

    @Override
    public SysRole findById(Serializable id) {

        List<Long> menuIds = new ArrayList<>();
        List<SysMenu> menus = new ArrayList<>();
        SysRole sysRole = sysRoleMapper.selectById(id);
        List<SysRoleMenu> sysRoleMenuByRoleId = iSysRoleMenuService.findSysRoleMenuByRoleId(sysRole.getRoleId());
        sysRoleMenuByRoleId.forEach(sysRoleMenu -> {
            SysMenu byId = iSysMenuService.findById(sysRoleMenu.getMenuId());
            menus.add(byId);
        });


        //菜单和按钮
        List<SysMenu> m = menus.stream().filter(sysMenu -> !sysMenu.getMenuType().equalsIgnoreCase("M")).collect(Collectors.toList());

        m.forEach(sysMenu -> {
            if(!hasChildren(sysMenu,m)){
                menuIds.add(sysMenu.getMenuId());
            }
        });

        sysRole.setMenuIds(menuIds);

        return sysRole;
    }


    @Override
    public void add(SysRole entity) {
        sysRoleMapper.insert(entity);
        List<Long> menuIds = entity.getMenuIds();
        if (!CollectionUtils.isEmpty(menuIds)) {
            menuIds.forEach(item -> {
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setMenuId(item);
                sysRoleMenu.setRoleId(entity.getRoleId());
                iSysRoleMenuService.add(sysRoleMenu);
            });
        }
    }

    public boolean hasChildren(SysMenu sysMenu, List<SysMenu> list) {

        return list.stream().anyMatch(sysMenu1 -> sysMenu1.getParentId().longValue() == sysMenu.getMenuId().longValue());

    }

    @Override
    public void update(SysRole entity) {
        sysRoleMapper.updateById(entity);
        iSysRoleMenuService.deleteByRoleId(entity.getRoleId());

        List<Long> menuIds = entity.getMenuIds();
        menuIds.forEach(item -> {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setMenuId(item);
            sysRoleMenu.setRoleId(entity.getRoleId());
            iSysRoleMenuService.add(sysRoleMenu);
        });

    }

    @Override
    public void deleteById(Serializable id) {
        sysRoleMapper.deleteById(id);
    }
}
