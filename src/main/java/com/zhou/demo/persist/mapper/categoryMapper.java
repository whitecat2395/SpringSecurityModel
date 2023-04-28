package com.zhou.demo.persist.mapper;

import com.zhou.demo.persist.po.CategoryTreeVo;

import java.util.List;

/**
 * @ClassName categoryMapper
 * @Author
 * @Date 2023/4/27 17:25
 * @Version
 * @Description
 */

public interface categoryMapper {

    List<CategoryTreeVo> selectlist();

}
