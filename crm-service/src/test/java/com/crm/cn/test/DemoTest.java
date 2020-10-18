package com.crm.cn.test;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crm.cn.entity.BaseCategory;
import com.crm.cn.mapper.BaseCategoryMapper;
import com.crm.cn.service.IBaseCategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext-mapper.xml","classpath:applicationContext-service.xml"})
public class DemoTest {
//    @Autowired
//    private AdminMapper adminMapper;
//    @Test
//    public void doTest(){
////        System.out.println(adminMapper.selectList(null));
////        Admin admin = adminMapper.selectById(1);
////        admin.setId(10);
////        admin.setPhone("");
////        admin.setEmail("");
////        adminMapper.insert(admin);
//        QueryWrapper<Admin> wrapper = new QueryWrapper<Admin>();
//        wrapper.eq("id",10);
//        adminMapper.delete(wrapper);
//    }
//    @Autowired
//    private BaseCategoryMapper baseCategoryMapper;
//    @Test
//    public void doTest(){
////        System.out.println(baseCategoryMapper.selectList(null));
//        Category category = new Category();
//        category.setName("bbbbbb");
//        category.setPid(3);
//        category.setAddress("睡大觉");
//        baseCategoryMapper.insert(category);
//
//   }
    @Autowired
    private IBaseCategoryService iBaseCategoryService;
    @Test
    public void doTest(){
        IPage<BaseCategory> page = new Page<BaseCategory>(1,3);

        IPage<BaseCategory> baseCategoryIPage = iBaseCategoryService.pageList(page);

        System.out.println(page == baseCategoryIPage);
    }

}
