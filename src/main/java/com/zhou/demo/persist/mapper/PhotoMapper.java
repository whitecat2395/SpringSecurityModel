package com.zhou.demo.persist.mapper;

import com.zhou.demo.persist.po.Photo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName PhotoMapper
 * @Author
 * @Date 2023/3/29 16:05
 * @Version
 * @Description
 */

@Mapper
public interface PhotoMapper {

    int addPhoto(Photo photo);

    List<String> selectUrlByGoodsId(Integer goodsId);

}
