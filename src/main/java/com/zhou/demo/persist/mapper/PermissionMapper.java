package com.zhou.demo.persist.mapper;

import com.zhou.demo.controller.request.SearchParams;
import com.zhou.demo.persist.po.Permission;
import com.zhou.demo.persist.po.StatuePo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName PermissionMapper
 * @Author zz
 * @Date 2023/3/10 15:06
 * @Version
 * @Description
 */

@Mapper
public interface PermissionMapper {

    List<Permission> queryAllPermissions(SearchParams searchParams);

    Integer queryPermissionCount(SearchParams searchParams);

    int updateStatue(StatuePo PermissiontatuePo);

    Integer addPermission(Permission permission);

    int deletePermission(Integer id);

    int updatePermission(Permission permission);

}
