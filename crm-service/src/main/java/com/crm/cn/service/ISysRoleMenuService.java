package com.crm.cn.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crm.cn.entity.SysRoleMenu;

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
public interface ISysRoleMenuService {
    /**
     * 查询所有
     * @return
     */
    List<SysRoleMenu> findAll();

    /**
     * 通过id查询
     * @param id
     * @return
     */
    SysRoleMenu findById(Serializable id);

    /**
     * 添加功能
     * @param  SysRoleMenu 实体类对象
     */
    void add(SysRoleMenu SysRoleMenu);
    /**
     * 修改功能
     * @param SysRoleMenu
     */
    void update(SysRoleMenu SysRoleMenu);

    /**
     * 通过id删除
     * @param id
     */
    void deleteById(Serializable id);

    List<SysRoleMenu> findSysRoleMenuByRoleId(Serializable roleId);


    void deleteByRoleId(Serializable roleId);
}
