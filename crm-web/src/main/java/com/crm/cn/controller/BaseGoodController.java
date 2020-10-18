package com.crm.cn.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crm.cn.entity.BaseGood;
import com.crm.cn.http.AxiosResult;
import com.crm.cn.http.PageResult;
import com.crm.cn.service.IBaseGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 春辉真球帅
 * @since 2020-10-17
 */
@RestController
@RequestMapping("good")
public class BaseGoodController {
    @Autowired
    private IBaseGoodService iBaseGoodService;

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping
    public AxiosResult findAll() {
        return AxiosResult.success(iBaseGoodService.findAll());
    }

    /**
     * 分页查询
     */
    @GetMapping("page")
    public AxiosResult pageList(@RequestParam(defaultValue = "1") int currentPage,
                                @RequestParam(defaultValue = "2") int pageSize) {
        Page<BaseGood> page = new Page<BaseGood>(currentPage, pageSize);
        IPage<BaseGood> page1 = iBaseGoodService.pageList(page);
        return AxiosResult.success(PageResult.instance(page1.getRecords(), page1.getTotal()));
    }

    @PostMapping
    public AxiosResult add(@RequestBody BaseGood baseGood) {

        iBaseGoodService.add(baseGood);
        return AxiosResult.success();
    }
    @PutMapping
    public AxiosResult update(@RequestBody BaseGood baseGood){
        iBaseGoodService.update(baseGood);
        return AxiosResult.success();
    }
    @GetMapping("{id}")
    public AxiosResult findById(@PathVariable Serializable id){
        return AxiosResult.success(iBaseGoodService.findById(id));
    }
    @DeleteMapping("{id}")
    public AxiosResult deleteById(@PathVariable Serializable id){
        iBaseGoodService.deleteById(id);
        return AxiosResult.success();
    }

}
