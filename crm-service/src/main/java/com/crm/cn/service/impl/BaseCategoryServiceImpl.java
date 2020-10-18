package com.crm.cn.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crm.cn.entity.BaseCategory;
import com.crm.cn.mapper.BaseCategoryMapper;
import com.crm.cn.service.IBaseCategoryService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 春辉真球帅
 * @since 2020-10-17
 */
@Service
@Transactional
@Log4j
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

    public IPage<BaseCategory> pageList(IPage<BaseCategory> page) {
        IPage<BaseCategory> page1 = baseCategoryMapper.selectPage(page, null);
        return page1;
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
}
