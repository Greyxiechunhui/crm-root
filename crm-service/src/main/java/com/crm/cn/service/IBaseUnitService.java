package com.crm.cn.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crm.cn.entity.BaseGood;
import com.crm.cn.entity.BaseUnit;
import com.crm.cn.entity.BaseUnit;

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
public interface IBaseUnitService {
    /**
     * 查询所有
     * @return
     */
    List<BaseUnit> findAll();
    /**
     *分页查询
     */
    IPage<BaseUnit> pageList(IPage<BaseUnit> page);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    BaseUnit findById(Serializable id);

    /**
     * 添加功能
     * @param  BaseUnit 实体类对象
     */
    void add(BaseUnit BaseUnit);
    /**
     * 修改功能
     * @param BaseUnit
     */
    void update(BaseUnit BaseUnit);

    /**
     * 通过id删除
     * @param id
     */
    void deleteById(Serializable id);
}
