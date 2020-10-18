package com.crm.cn.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crm.cn.entity.BaseUnit;
import com.crm.cn.mapper.BaseUnitMapper;
import com.crm.cn.service.IBaseUnitService;
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
public class BaseUnitServiceImpl implements IBaseUnitService {

    @Autowired
    private BaseUnitMapper baseUnitMapper;

    public List<BaseUnit> findAll() {
        return baseUnitMapper.selectList(null);
    }

    public IPage<BaseUnit> pageList(IPage<BaseUnit> page) {
        IPage<BaseUnit> page1 = baseUnitMapper.selectPage(page,null);
        return page1;
    }

    public BaseUnit findById(Serializable id) {
        return baseUnitMapper.selectById(id);
    }

    public void add(BaseUnit BaseUnit) {
        baseUnitMapper.insert(BaseUnit);
    }

    public void update(BaseUnit BaseUnit) {
        baseUnitMapper.updateById(BaseUnit);
    }

    public void deleteById(Serializable id) {
        baseUnitMapper.deleteById(id);
    }
}
