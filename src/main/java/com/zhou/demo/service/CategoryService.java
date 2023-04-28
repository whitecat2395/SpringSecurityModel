package com.zhou.demo.service;

import com.zhou.demo.persist.mapper.categoryMapper;
import com.zhou.demo.persist.po.CategoryTreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CategoryService
 * @Author
 * @Date 2023/4/27 17:23
 * @Version
 * @Description
 */

@Service
public class CategoryService {

    @Autowired
    private categoryMapper mapper;

    public List<CategoryTreeVo> selectCategoryList() {
        return mapper.selectlist();
    }
}
