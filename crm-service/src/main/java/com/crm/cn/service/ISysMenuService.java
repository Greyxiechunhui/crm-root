package com.crm.cn.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crm.cn.entity.SysMenu;
import com.crm.cn.http.PageResult;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 春辉真球帅
 * @since 2020-10-17
 */
public interface ISysMenuService {
    /**
     * 查询所有
     *
     * @return
     */
    List<SysMenu> findAll();

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    SysMenu findById(Serializable id);

    /**
     * 添加功能
     *
     * @param SysMenu 实体类对象
     */
    void add(SysMenu SysMenu);

    /**
     * 修改功能
     *
     * @param SysMenu
     */
    void update(SysMenu SysMenu);

    /**
     * 通过id删除
     *
     * @param id
     */
    void deleteById(Serializable id);

    List<SysMenu> getAllMenuTree();
}
