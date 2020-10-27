package com.crm.cn.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crm.cn.entity.SysRole;
import com.crm.cn.http.AxiosResult;
import com.crm.cn.http.PageResult;
import com.crm.cn.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 春辉真球帅
 * @since 2020-10-17
 */
@RestController
@RequestMapping("role")
public class SysRoleController {
    @Autowired
    private ISysRoleService ISysRoleService;

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping
    public AxiosResult findAll() {
        return AxiosResult.success(ISysRoleService.findAll());
    }

    /**
     * 分页查询
     */
//    @GetMapping("page")
//    public AxiosResult pageList(@RequestParam(defaultValue = "1") int currentPage,
//                                @RequestParam(defaultValue = "2") int pageSize) {
//        Page<SysRole> page = new Page<SysRole>(currentPage, pageSize);
//        IPage<SysRole> page1 = ISysRoleService.pageList(page);
//        return AxiosResult.success(PageResult.instance(page1.getRecords(), page1.getTotal()));
//    }

    @PostMapping
    public AxiosResult add(@RequestBody SysRole SysRole) {

        System.out.println(SysRole);

        ISysRoleService.add(SysRole);
        return AxiosResult.success();
    }
    @PutMapping
    public AxiosResult update(@RequestBody SysRole SysRole){
        ISysRoleService.update(SysRole);
        return AxiosResult.success();
    }
    @GetMapping("{id}")
    public AxiosResult findById(@PathVariable Serializable id){
        return AxiosResult.success(ISysRoleService.findById(id));
    }
    @DeleteMapping("{id}")
    public AxiosResult deleteById(@PathVariable Serializable id){
        ISysRoleService.deleteById(id);
        return AxiosResult.success();
    }
}
