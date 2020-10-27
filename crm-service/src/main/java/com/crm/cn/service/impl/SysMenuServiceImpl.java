package com.crm.cn.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crm.cn.entity.SysMenu;
import com.crm.cn.entity.SysMenu;
import com.crm.cn.http.PageResult;
import com.crm.cn.mapper.SysMenuMapper;
import com.crm.cn.mapper.SysMenuMapper;
import com.crm.cn.service.ISysMenuService;
import com.crm.cn.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
public class SysMenuServiceImpl implements ISysMenuService {

    @Autowired
    private SysMenuMapper SysMenuMapper;

    public List<SysMenu> findAll() {
        return SysMenuMapper.selectList(null);
    }



    public SysMenu findById(Serializable id) {
        return SysMenuMapper.selectById(id);
    }

    public void add(SysMenu SysMenu) {
        SysMenuMapper.insert(SysMenu);
    }

    public void update(SysMenu SysMenu) {
        SysMenuMapper.updateById(SysMenu);
    }

    public void deleteById(Serializable id) {
        SysMenuMapper.deleteById(id);
    }

    @Override
    public List<SysMenu> getAllMenuTree() {
        List<SysMenu> all = this.findAll();
        List<SysMenu> collect = all.stream().filter(item -> item.getParentId().longValue()==0).collect(Collectors.toList());

        collect.forEach(item->{
            getMenuChild(item,all);
        });


        return collect;
    }
    public void getMenuChild(SysMenu sysMenu,List<SysMenu> all){
        List<SysMenu> collect = all.stream().filter(item -> item.getParentId().equals(sysMenu.getMenuId())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(collect)){
            sysMenu.setChildren(collect);
        }

        collect.forEach(item1->{
            getMenuChild(item1,all);
        });
    }

}
