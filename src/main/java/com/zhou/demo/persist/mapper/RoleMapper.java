package com.zhou.demo.persist.mapper;

import com.zhou.demo.controller.request.SearchParams;
import com.zhou.demo.persist.po.Role;
import com.zhou.demo.persist.po.RoleStatuePo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {

    int connectUserAndRole(Integer userid, Integer roleid);

    List<Role> queryAllRole(SearchParams searchParams);

    int addRole(Role role);

    Integer queryRoleCount(SearchParams searchParams);

    int updateStatue(RoleStatuePo roleStatuePo);

    int deleteRole(Integer id);

    int updateRole(Role role);
}
