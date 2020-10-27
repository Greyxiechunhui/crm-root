package com.crm.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crm.cn.entity.SysRoleMenu;
import com.crm.cn.mapper.SysRoleMenuMapper;
import com.crm.cn.service.ISysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

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
public class SysRoleMenuServiceImpl implements ISysRoleMenuService {

    @Autowired
    private SysRoleMenuMapper SysRoleMenuMapper;


    public List<SysRoleMenu> findAll() {
        return SysRoleMenuMapper.selectList(null);
    }




    public SysRoleMenu findById(Serializable id) {
        return SysRoleMenuMapper.selectById(id);
    }

    public void add(SysRoleMenu entity) {
        SysRoleMenuMapper.insert(entity);


    }

    public void update(SysRoleMenu entity) {
        SysRoleMenuMapper.updateById(entity);



    }

    public void deleteById(Serializable id) {
        SysRoleMenuMapper.deleteById(id);
    }

    @Override
    public List<SysRoleMenu> findSysRoleMenuByRoleId(Serializable roleId) {
        QueryWrapper<SysRoleMenu> queryWrapper =new QueryWrapper<>();
        queryWrapper.lambda().eq(SysRoleMenu::getRoleId,roleId);

        List<SysRoleMenu> sysRoleMenus = SysRoleMenuMapper.selectList(queryWrapper);
        return sysRoleMenus;
    }

    @Override
    public void deleteByRoleId(Serializable roleId) {
        QueryWrapper<SysRoleMenu> queryWrapper =new QueryWrapper<>();
        queryWrapper.lambda().eq(SysRoleMenu::getRoleId,roleId);
        SysRoleMenuMapper.delete(queryWrapper);
    }


}
