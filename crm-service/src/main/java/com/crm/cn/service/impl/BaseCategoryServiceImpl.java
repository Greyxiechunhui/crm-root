package com.crm.cn.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crm.cn.entity.BaseCategory;
import com.crm.cn.http.PageResult;
import com.crm.cn.mapper.BaseCategoryMapper;
import com.crm.cn.service.IBaseCategoryService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 春辉真球帅
 * @since 2020-10-17
 */
@Service
@Transactional
//@Log4j
public class BaseCategoryServiceImpl implements IBaseCategoryService {

//    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(BaseCategoryServiceImpl.class);

    @Autowired
    private BaseCategoryMapper baseCategoryMapper;

//    public List<BaseCategory> findAll() {
//        IPage<BaseCategory> page = new Page<BaseCategory>(1,1);
//        IPage<BaseCategory> baseCategoryIPage = baseCategoryMapper.selectPage(page, null);
//        System.out.println(baseCategoryIPage.getTotal());
//        List<BaseCategory> records = baseCategoryIPage.getRecords();
//        log.error("程序报错了");
//        log.info("程序打印了info");
//        return records;
//    }

    public List<BaseCategory> findAll() {
        return baseCategoryMapper.selectList(null);
    }


    public PageResult pageList(IPage<BaseCategory> page) {
        IPage<BaseCategory> baseCategoryIPage = baseCategoryMapper.selectPage(page, null);


        List<BaseCategory> records = baseCategoryIPage.getRecords();
        long total = baseCategoryIPage.getTotal();

        records.forEach(item -> {
            Integer pId = item.getPId();
            if (pId.equals(0)) {
                item.setPName("一级分类");
            } else {
                item.setPName(this.findById(pId).getName());
            }
        });
        return PageResult.instance(records,total);
    }

    public BaseCategory findById(Serializable id) {
        return baseCategoryMapper.selectById(id);
    }

    public void add(BaseCategory BaseCategory) {
        baseCategoryMapper.insert(BaseCategory);
    }

    public void update(BaseCategory BaseCategory) {
        baseCategoryMapper.updateById(BaseCategory);
    }

    public void deleteById(Serializable id) {
        baseCategoryMapper.deleteById(id);
    }

    @Override
    public List<BaseCategory> getCategoryTree() {
        //所有的分类
        List<BaseCategory> all = this.findAll();

        //获得一级分类
        List<BaseCategory> collect = all.stream().filter(baseCategory -> baseCategory.getPId() == 0).collect(Collectors.toList());

        collect.forEach(item -> {
            getCategoryChild(item, all);
        });

        return collect;
    }

    /*
     * 找分类的孩子
     *
     * */
    public void getCategoryChild(BaseCategory category, List<BaseCategory> all) {
        List<BaseCategory> collect = all.stream().filter(category1 -> category1.getPId() == category.getId()).collect(Collectors.toList());

        if(!CollectionUtils.isEmpty(collect)){
            category.setChildren(collect);
        }

        collect.forEach(category2 -> {
            getCategoryChild(category2, all);
        });
    }
}
