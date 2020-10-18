package com.crm.cn.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crm.cn.entity.BaseSupplier;
import com.crm.cn.http.AxiosResult;
import com.crm.cn.http.PageResult;
import com.crm.cn.service.IBaseSupplierService;
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
@RequestMapping("supplier")
public class BaseSupplierController {
    @Autowired
    private IBaseSupplierService IBaseSupplierService;

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping
    public AxiosResult findAll() {
        return AxiosResult.success(IBaseSupplierService.findAll());
    }

    /**
     * 分页查询
     */
    @GetMapping("page")
    public AxiosResult pageList(@RequestParam(defaultValue = "1") int currentPage,
                                @RequestParam(defaultValue = "2") int pageSize) {
        Page<BaseSupplier> page = new Page<BaseSupplier>(currentPage, pageSize);
        IPage<BaseSupplier> page1 = IBaseSupplierService.pageList(page);
        return AxiosResult.success(PageResult.instance(page1.getRecords(), page1.getTotal()));
    }

    @PostMapping
    public AxiosResult add(@RequestBody BaseSupplier BaseSupplier) {

        IBaseSupplierService.add(BaseSupplier);
        return AxiosResult.success();
    }
    @PutMapping
    public AxiosResult update(@RequestBody BaseSupplier BaseSupplier){
        IBaseSupplierService.update(BaseSupplier);
        return AxiosResult.success();
    }
    @GetMapping("{id}")
    public AxiosResult findById(@PathVariable Serializable id){
        return AxiosResult.success(IBaseSupplierService.findById(id));
    }
    @DeleteMapping("{id}")
    public AxiosResult deleteById(@PathVariable Serializable id){
        IBaseSupplierService.deleteById(id);
        return AxiosResult.success();
    }
}
