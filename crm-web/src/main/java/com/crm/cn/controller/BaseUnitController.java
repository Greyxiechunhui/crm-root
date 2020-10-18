package com.crm.cn.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crm.cn.entity.BaseUnit;
import com.crm.cn.http.AxiosResult;
import com.crm.cn.http.PageResult;
import com.crm.cn.service.IBaseUnitService;
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
@RequestMapping("unit")
public class BaseUnitController {
    @Autowired
    private IBaseUnitService IBaseUnitService;

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping
    public AxiosResult findAll() {
        return AxiosResult.success(IBaseUnitService.findAll());
    }

    /**
     * 分页查询
     */
    @GetMapping("page")
    public AxiosResult pageList(@RequestParam(defaultValue = "1") int currentPage,
                                @RequestParam(defaultValue = "2") int pageSize) {
        Page<BaseUnit> page = new Page<BaseUnit>(currentPage, pageSize);
        IPage<BaseUnit> page1 = IBaseUnitService.pageList(page);
        return AxiosResult.success(PageResult.instance(page1.getRecords(), page1.getTotal()));
    }

    @PostMapping
    public AxiosResult add(@RequestBody BaseUnit BaseUnit) {

        IBaseUnitService.add(BaseUnit);
        return AxiosResult.success();
    }
    @PutMapping
    public AxiosResult update(@RequestBody BaseUnit BaseUnit){
        IBaseUnitService.update(BaseUnit);
        return AxiosResult.success();
    }
    @GetMapping("{id}")
    public AxiosResult findById(@PathVariable Serializable id){

        return AxiosResult.success(IBaseUnitService.findById(id));
    }
    @DeleteMapping("{id}")
    public AxiosResult deleteById(@PathVariable Serializable id){
        IBaseUnitService.deleteById(id);
        return AxiosResult.success();
    }
}
