package com.crm.cn.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.crm.cn.entity.BaseCategory;
import com.crm.cn.entity.BaseCategory;
import com.crm.cn.entity.BaseGood;
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
public interface IBaseCategoryService {
    /**
     * 查询所有
     * @return
     */
    List<BaseCategory> findAll();
    /**
     * 分页查询
     *
     */
    PageResult pageList(IPage<BaseCategory> page);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    BaseCategory findById(Serializable id);

    /**
     * 添加功能
     * @param  BaseCategory 实体类对象
     */
    void add(BaseCategory BaseCategory);
    /**
     * 修改功能
     * @param BaseCategory
     */
    void update(BaseCategory BaseCategory);

    /**
     * 通过id删除
     * @param id
     */
    void deleteById(Serializable id);

    /**
     * 获得分类tree
     * @return
     */

    List<BaseCategory> getCategoryTree();
}
