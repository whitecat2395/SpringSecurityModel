package com.zhou.demo.persist.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {

    int connectUserAndRole(Integer userid, Integer roleid);

}
