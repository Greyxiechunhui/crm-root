package com.crm.cn.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crm.cn.entity.BaseGood;

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
public interface IBaseGoodService {
    /**
     * 查询所有
     * @return
     */
    List<BaseGood> findAll();
    /**
     * 分页查询
     *
     */
    IPage<BaseGood> pageList(IPage<BaseGood> page);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    BaseGood findById(Serializable id);

    /**
     * 添加功能
     * @param  baseGood 实体类对象
     */
    void add(BaseGood baseGood);
    /**
     * 修改功能
     * @param baseGood
     */
    void update(BaseGood baseGood);

    /**
     * 通过id删除
     * @param id
     */
    void deleteById(Serializable id);
}
