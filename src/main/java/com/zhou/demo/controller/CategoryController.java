package com.zhou.demo.controller;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.zhou.demo.persist.po.CategoryTreeVo;
import com.zhou.demo.service.CategoryService;
import com.zhou.demo.utils.CategoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName CateGoryController
 * @Author
 * @Date 2023/4/27 17:20
 * @Version
 * @Description
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping(value = "/getAllCategory")
    @ResponseBody
    public List<CategoryTreeVo> getAllSupplier(){
        // 先获取所有的数据表数据
        List<CategoryTreeVo> allMenuTreeVoList = categoryService.selectCategoryList();

        List<CategoryTreeVo> menuTreeVOTreeList = CategoryUtil.toTree(allMenuTreeVoList);

        if (CollectionUtils.isNotEmpty(menuTreeVOTreeList)) {
            return menuTreeVOTreeList;
        }else {
            return null;
        }
    }
}
