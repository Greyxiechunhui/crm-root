package com.crm.cn.controller;


import com.crm.cn.entity.SysMenu;
import com.crm.cn.http.AxiosResult;
import com.crm.cn.service.ISysMenuService;
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
@RequestMapping("menu")
public class SysMenuController {
    @Autowired
    private ISysMenuService ISysMenuService;

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping("menuTree")
    public AxiosResult findAll() {
        return AxiosResult.success(ISysMenuService.getAllMenuTree());
    }


    @PostMapping
    public AxiosResult add(@RequestBody SysMenu SysMenu) {

        ISysMenuService.add(SysMenu);
        return AxiosResult.success();
    }
    @PutMapping
    public AxiosResult update(@RequestBody SysMenu SysMenu){
        ISysMenuService.update(SysMenu);
        return AxiosResult.success();
    }
    @GetMapping("{id}")
    public AxiosResult findById(@PathVariable Serializable id){
        return AxiosResult.success(ISysMenuService.findById(id));
    }
    @DeleteMapping("{id}")
    public AxiosResult deleteById(@PathVariable Serializable id){
        ISysMenuService.deleteById(id);
        return AxiosResult.success();
    }
}
