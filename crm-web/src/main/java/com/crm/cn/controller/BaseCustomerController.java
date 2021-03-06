package com.crm.cn.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crm.cn.entity.BaseCustomer;
import com.crm.cn.http.AxiosResult;
import com.crm.cn.http.PageResult;
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
@RequestMapping("customer")
public class BaseCustomerController {
    @Autowired
    private com.crm.cn.service.IBaseCustomerService IBaseCustomerService;

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping
    public AxiosResult findAll() {
        return AxiosResult.success(IBaseCustomerService.findAll());
    }

    /**
     * 分页查询
     */
    @GetMapping("page")
    public AxiosResult pageList(@RequestParam(defaultValue = "1") int currentPage,
                                @RequestParam(defaultValue = "2") int pageSize) {
        Page<BaseCustomer> page = new Page<BaseCustomer>(currentPage, pageSize);
        IPage<BaseCustomer> page1 = IBaseCustomerService.pageList(page);
        return AxiosResult.success(PageResult.instance(page1.getRecords(), page1.getTotal()));
    }

    @PostMapping
    public AxiosResult add(@RequestBody BaseCustomer BaseCustomer) {

        IBaseCustomerService.add(BaseCustomer);
        return AxiosResult.success();
    }
    @PutMapping
    public AxiosResult update(@RequestBody BaseCustomer BaseCustomer){
        IBaseCustomerService.update(BaseCustomer);
        return AxiosResult.success();
    }
    @GetMapping("{id}")
    public AxiosResult findById(@PathVariable Serializable id){
        return AxiosResult.success(IBaseCustomerService.findById(id));
    }
    @DeleteMapping("{id}")
    public AxiosResult deleteById(@PathVariable Serializable id){
        IBaseCustomerService.deleteById(id);
        return AxiosResult.success();
    }

}
