package com.crm.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crm.cn.entity.SysRole;
import com.crm.cn.entity.SysUserRole;
import com.crm.cn.http.AxiosResult;
import com.crm.cn.mapper.SysUserRoleMapper;
import com.crm.cn.service.ISysRoleService;
import com.crm.cn.service.ISysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SysUserRoleServiceImpl implements ISysUserRoleService {
    @Autowired
    private SysUserRoleMapper SysUserRoleMapper;

    @Autowired
    private ISysRoleService iSysRoleService;

    @Override
    public List<SysUserRole> findAll() {
        List<SysUserRole> sysUserRoles = SysUserRoleMapper.selectList(null);
        return sysUserRoles;
    }

    @Override
    public SysUserRole findById(Serializable id) {
        return SysUserRoleMapper.selectById(id);
    }

    @Override
    public void add(SysUserRole entity) {
        SysUserRoleMapper.insert(entity);
        //添加用户的角色

    }

    @Override
    public void update(SysUserRole entity) {
        SysUserRoleMapper.updateById(entity);
    }

    @Override
    public void deleteById(Serializable id) {
        SysUserRoleMapper.deleteById(id);
    }

    @Override
    public List<SysRole> findRoleByUserId(Serializable userId) {
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUserRole::getUserId, userId);
        List<SysUserRole> list = SysUserRoleMapper.selectList(queryWrapper);

        List<SysRole> roles = new ArrayList<>();

        list.forEach(sysUserRole -> {
            SysRole byId = iSysRoleService.findById(sysUserRole.getRoleId());
            roles.add(byId);
        });


        return roles;
    }

    @Override
    public void deleteByUserIdAndRoleId(Serializable userId, Serializable roleId) {
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUserRole::getUserId, userId).eq(SysUserRole::getRoleId,roleId);
        SysUserRoleMapper.delete(queryWrapper);

    }

    @Override
    public void deleteRoleByUserId(Serializable userId) {
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUserRole::getUserId, userId);
        SysUserRoleMapper.delete(queryWrapper);
    }


}
