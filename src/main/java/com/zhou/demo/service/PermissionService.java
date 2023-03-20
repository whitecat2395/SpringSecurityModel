package com.zhou.demo.service;

import com.zhou.demo.controller.request.SearchParams;
import com.zhou.demo.domain.LoginUser;
import com.zhou.demo.persist.mapper.PermissionMapper;
import com.zhou.demo.persist.po.Permission;
import com.zhou.demo.persist.po.StatuePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PermissionService
 * @Author
 * @Date 2023/3/17 16:06
 * @Version
 * @Description
 */

@Service
public class PermissionService {
    @Autowired
    private PermissionMapper mapper;

    public Map queryAllPermission(SearchParams searchParams) {
        //判断page大小和起始位置
        String keyword=searchParams.getKeyword();
        if(keyword != "") {
            searchParams.setKeyword("%"+keyword+"%");
        }
        Integer pageNum = searchParams.getPagenum();
        Integer pageSize = searchParams.getPagesize();
        searchParams.setPageindex(pageNum==0 ? 0:(pageNum-1)*pageSize);
        List<Permission> PermissionList = mapper.queryAllPermissions(searchParams);
        searchParams.setTotal(mapper.queryPermissionCount(searchParams));
        HashMap<String, Object> map = new HashMap<>();
        if(keyword!=null){
            searchParams.setKeyword(keyword);
        }
        //装填参数
        map.put("PermissionList",PermissionList);
        map.put("SearchParams",searchParams);
        return map;
    }

    public int addPermission(Permission permission) {
        //填充Role
        //从handler中获取当前用户的信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser= (LoginUser)authentication.getPrincipal();
        Integer userId = loginUser.getUser().getId();
        //0为使用状态
        permission.setCreateTime(new Date());
        permission.setDelFlag(false);
        permission.setStatus("0");
        Integer flag = mapper.addPermission(permission);
        if(flag==0){
            return flag;
        }
        return flag;
    }

    public int updateStatue(StatuePo permissionStatuePo) {
        return mapper.updateStatue(permissionStatuePo);
    }

    public int deletePermission(Integer id) {
        return mapper.deletePermission(id);
    }

    public int editPermission(Permission permission) {
        Date date = new Date();
        permission.setUpdateTime(date);
        permission.setUpdateBy(0001);
        return mapper.updatePermission(permission);
    }
}
