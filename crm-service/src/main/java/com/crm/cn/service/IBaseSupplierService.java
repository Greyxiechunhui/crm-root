package com.crm.cn.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crm.cn.entity.BaseGood;
import com.crm.cn.entity.BaseSupplier;
import com.crm.cn.entity.BaseSupplier;

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
public interface IBaseSupplierService {
    /**
     * 查询所有
     * @return
     */
    List<BaseSupplier> findAll();
    /**
     *分页查询
     */
    IPage<BaseSupplier> pageList(IPage<BaseSupplier> page);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    BaseSupplier findById(Serializable id);

    /**
     * 添加功能
     * @param  BaseSupplier 实体类对象
     */
    void add(BaseSupplier BaseSupplier);
    /**
     * 修改功能
     * @param BaseSupplier
     */
    void update(BaseSupplier BaseSupplier);

    /**
     * 通过id删除
     * @param id
     */
    void deleteById(Serializable id);
}
