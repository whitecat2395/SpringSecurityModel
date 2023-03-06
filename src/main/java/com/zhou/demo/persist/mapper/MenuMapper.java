package com.zhou.demo.persist.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<String> queryPermsByUserId(Integer id);

}
