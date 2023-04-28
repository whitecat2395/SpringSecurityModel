package com.zhou.demo.controller;

import cn.hutool.core.lang.hash.Hash;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.zhou.demo.domain.CommonResult;
import com.zhou.demo.persist.po.CategoryTreeVo;
import com.zhou.demo.service.CategoryService;
import com.zhou.demo.utils.CategoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public CommonResult<Map> getAllSupplier(){
        // 先获取所有的数据表数据
        List<CategoryTreeVo> allCateGoryTreeVoList = categoryService.selectCategoryList();

        List<CategoryTreeVo> cateGoryTreeVOTreeList = CategoryUtil.toTree(allCateGoryTreeVoList);

        if (CollectionUtils.isNotEmpty(cateGoryTreeVOTreeList)) {
            HashMap<Object, Object> map = new HashMap<>();
            map.put("categoryItem",cateGoryTreeVOTreeList);
            return new CommonResult<>(200,"查询成功",map);
        }else {
            return new CommonResult(200,"查询失败");
        }
    }
}
