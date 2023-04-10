package com.zhou.demo.persist.mapper;

import com.zhou.demo.persist.po.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName CarMapper
 * @Author
 * @Date 2023/4/10 16:50
 * @Version
 * @Description
 */

@Mapper
public interface CarMapper {

    int addGoods(Integer id, Integer userId);

    List<Goods> selectCarByUserId(Integer userId);

    int selectCarCountByUserId(Integer userId);
}
