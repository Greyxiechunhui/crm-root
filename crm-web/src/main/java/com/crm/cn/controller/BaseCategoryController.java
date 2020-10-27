package com.crm.cn.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crm.cn.entity.BaseCategory;
import com.crm.cn.http.AxiosResult;
import com.crm.cn.http.PageResult;
import com.crm.cn.service.IBaseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 春辉真球帅
 * @since 2020-10-17
 */
@RestController
@RequestMapping("category")
public class BaseCategoryController {
    @Autowired
    private IBaseCategoryService IBaseCategoryService;

    /**
     * 查询所有
     *
     * 商品 要用分类信息
     *
     * @return
     */
    @GetMapping
    public AxiosResult findAll() {
        return AxiosResult.success(IBaseCategoryService.findAll());
    }

    /**
     * 分页查询
     */
    @GetMapping("page")
    public AxiosResult pageList(@RequestParam(defaultValue = "1") int currentPage,
                                @RequestParam(defaultValue = "5") int pageSize) {
        Page<BaseCategory> page = new Page<BaseCategory>(currentPage, pageSize);
        return AxiosResult.success(IBaseCategoryService.pageList(page));
    }

    @PostMapping
    public AxiosResult add(@RequestBody BaseCategory BaseCategory) {

        IBaseCategoryService.add(BaseCategory);
        return AxiosResult.success();
    }
    @PutMapping
    public AxiosResult update(@RequestBody BaseCategory BaseCategory){
        IBaseCategoryService.update(BaseCategory);
        return AxiosResult.success();
    }
    @GetMapping("{id}")
    public AxiosResult findById(@PathVariable Serializable id){
        return AxiosResult.success(IBaseCategoryService.findById(id));
    }
    @DeleteMapping("{id}")
    public AxiosResult deleteById(@PathVariable Serializable id){
        IBaseCategoryService.deleteById(id);
        return AxiosResult.success();
    }


    @GetMapping("categoryTree")
    public AxiosResult getCategoryTree(){
        //
        List<BaseCategory> treeList = IBaseCategoryService.getCategoryTree();


        return AxiosResult.success(treeList);
    }

}
