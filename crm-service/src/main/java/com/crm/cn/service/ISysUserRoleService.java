package com.crm.cn.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crm.cn.entity.SysRole;
import com.crm.cn.entity.SysRoleMenu;
import com.crm.cn.entity.SysUserRole;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 春辉真球帅
 * @since 2020-10-17
 */
public interface ISysUserRoleService {
    /**
     * 查询所有
     * @return
     */
    List<SysUserRole> findAll();

    /**
     * 通过id查询
     * @param id
     * @return
     */
    SysUserRole findById(Serializable id);

    /**
     * 添加功能
     * @param  SysUserRole 实体类对象
     */
    void add(SysUserRole SysUserRole);
    /**
     * 修改功能
     * @param SysUserRole
     */
    void update(SysUserRole SysUserRole);

    /**
     * 通过id删除
     * @param id
     */
    void deleteById(Serializable id);

    List<SysRole> findRoleByUserId(Serializable userId);

    void deleteByUserIdAndRoleId(Serializable userId,Serializable roleId);

    void deleteRoleByUserId(Serializable userId);


}
