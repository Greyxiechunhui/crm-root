package com.crm.cn.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crm.cn.entity.SysRole;
import com.crm.cn.http.PageResult;

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
public interface ISysRoleService {
    /**
     * 查询所有
     * @return
     */
    List<SysRole> findAll();
    /**
     *分页查询
     */
    PageResult pageList(IPage<SysRole> page);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    SysRole findById(Serializable id);

    /**
     * 添加功能
     * @param  SysRole 实体类对象
     */
    void add(SysRole SysRole);
    /**
     * 修改功能
     * @param SysRole
     */
    void update(SysRole SysRole);

    /**
     * 通过id删除
     * @param id
     */
    void deleteById(Serializable id);
}
