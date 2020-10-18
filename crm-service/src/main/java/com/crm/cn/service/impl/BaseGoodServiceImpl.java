package com.crm.cn.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crm.cn.entity.BaseGood;
import com.crm.cn.mapper.BaseGoodMapper;
import com.crm.cn.service.IBaseGoodService;
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
public class BaseGoodServiceImpl implements IBaseGoodService {
    @Autowired
    private BaseGoodMapper baseGoodMapper;

    public List<BaseGood> findAll() {
        return baseGoodMapper.selectList(null);
    }

    public IPage<BaseGood> pageList(IPage<BaseGood> page) {
        IPage<BaseGood> page1 = baseGoodMapper.selectPage(page, null);

        return page1;
    }

    public BaseGood findById(Serializable id) {
        return baseGoodMapper.selectById(id);
    }

    public void add(BaseGood baseGood) {
        baseGoodMapper.insert(baseGood);
    }

    public void update(BaseGood baseGood) {
        baseGoodMapper.updateById(baseGood);
    }

    public void deleteById(Serializable id) {
        baseGoodMapper.deleteById(id);
    }
}
