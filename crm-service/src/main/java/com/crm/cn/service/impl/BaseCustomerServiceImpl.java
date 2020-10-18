package com.crm.cn.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crm.cn.entity.BaseCustomer;
import com.crm.cn.mapper.BaseCustomerMapper;
import com.crm.cn.service.IBaseCustomerService;
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
public class BaseCustomerServiceImpl implements IBaseCustomerService {

    @Autowired
    private BaseCustomerMapper baseCustomerMapper;
    public List<BaseCustomer> findAll() {
        return baseCustomerMapper.selectList(null);
    }

    public IPage<BaseCustomer> pageList(IPage<BaseCustomer> page) {
        IPage<BaseCustomer> page1 = baseCustomerMapper.selectPage(page,null);
        return page1;
    }

    public BaseCustomer findById(Serializable id) {
        return baseCustomerMapper.selectById(id);
    }

    public void add(BaseCustomer BaseCustomer) {
        baseCustomerMapper.insert(BaseCustomer);
    }

    public void update(BaseCustomer BaseCustomer) {
        baseCustomerMapper.updateById(BaseCustomer);
    }

    public void deleteById(Serializable id) {
        baseCustomerMapper.deleteById(id);
    }
}
