package com.crm.cn.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crm.cn.entity.BaseSupplier;
import com.crm.cn.mapper.BaseSupplierMapper;
import com.crm.cn.service.IBaseSupplierService;
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
public class BaseSupplierServiceImpl implements IBaseSupplierService {

    @Autowired
    private BaseSupplierMapper baseSupplierMapper;
    public List<BaseSupplier> findAll() {
        return baseSupplierMapper.selectList(null);
    }

    public IPage<BaseSupplier> pageList(IPage<BaseSupplier> page) {
        IPage<BaseSupplier> page1 = baseSupplierMapper.selectPage(page, null);
        return page1;
    }

    public BaseSupplier findById(Serializable id) {
        return baseSupplierMapper.selectById(id);
    }

    public void add(BaseSupplier BaseSupplier) {
        baseSupplierMapper.insert(BaseSupplier);
    }

    public void update(BaseSupplier BaseSupplier) {
        baseSupplierMapper.updateById(BaseSupplier);
    }

    public void deleteById(Serializable id) {
        baseSupplierMapper.deleteById(id);
    }
}
