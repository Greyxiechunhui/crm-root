package com.crm.cn.service.impl;

import com.crm.cn.entity.SysLoginLog;
import com.crm.cn.mapper.SysLoginLogMapper;
import com.crm.cn.service.ISysLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysLoginLogServiceImpl implements ISysLoginService {

    @Autowired
    private SysLoginLogMapper sysLoginLogMapper;
    @Override
    public void add(SysLoginLog sysLoginLog) {
        sysLoginLogMapper.insert(sysLoginLog);
    }
}
