package com.crm.cn.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.crm.cn.entity.SysUser;
import com.crm.cn.entity.SysUserRole;
import com.crm.cn.http.AxiosResult;
import com.crm.cn.http.PageResult;
import com.crm.cn.mapper.SysUserMapper;
import com.crm.cn.mapper.SysUserMapper;
import com.crm.cn.service.ISysUserRoleService;
import com.crm.cn.service.ISysUserService;
import com.crm.cn.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Arrays;
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
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper SysUserMapper;
    @Autowired
    private ISysUserRoleService iSysUserRoleService;


    public List<SysUser> findAll() {
        return SysUserMapper.selectList(null);
    }


    public PageResult pageList(IPage<SysUser> page) {
        IPage<SysUser> SysUserIPage = SysUserMapper.selectPage(page, null);
        List<SysUser> records = SysUserIPage.getRecords();
        long total = SysUserIPage.getTotal();

        return PageResult.instance(records,total);
    }

    public SysUser findById(Serializable id) {
        return SysUserMapper.selectById(id);
    }

    public void add(SysUser entity) {
        SysUserMapper.insert(entity);
        //添加用户的角色
        String[] as = entity.getRoleIds().split("A");

        Arrays.asList(as).forEach(item->{
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(entity.getUserId());
            sysUserRole.setRoleId(Long.parseLong(item));
            iSysUserRoleService.add(sysUserRole);
        });



    }

    public void update(SysUser entity) {
        SysUserMapper.updateById(entity);
        //先删除完再添加
        iSysUserRoleService.deleteRoleByUserId(entity.getUserId());

        //添加用户的角色
        String[] as = entity.getRoleIds().split("A");
        Arrays.asList(as).forEach(item->{
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(entity.getUserId());
            sysUserRole.setRoleId(Long.parseLong(item));
            iSysUserRoleService.add(sysUserRole);
        });
    }

    public void deleteById(Serializable id) {
        SysUserMapper.deleteById(id);
    }


}
