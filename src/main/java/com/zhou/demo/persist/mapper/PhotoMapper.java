package com.zhou.demo.persist.mapper;

import com.zhou.demo.persist.po.Photo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName PhotoMapper
 * @Author
 * @Date 2023/3/29 16:05
 * @Version
 * @Description
 */

@Mapper
public interface PhotoMapper {

    public int addPhoto(Photo photo);
}
