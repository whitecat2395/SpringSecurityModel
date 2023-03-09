package com.zhou.demo.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhou.demo.controller.request.SearchParams;
import com.zhou.demo.controller.response.UserResponse;
import com.zhou.demo.persist.mapper.RoleMapper;
import com.zhou.demo.persist.po.Role;
import com.zhou.demo.persist.po.RoleStatuePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleService {

    @Autowired
    private RoleMapper mapper;

    //添加用户和角色关联关系
    public int addRoleByUserName(Integer userid ,Integer roleid) {
        return mapper.connectUserAndRole(userid,roleid);
    }

    public Map<String,Object> queryAllRole(SearchParams searchParams) {
        //判断page大小和起始位置
        String keyword=searchParams.getKeyword();
        if(keyword != "") {
            searchParams.setKeyword("%"+keyword+"%");
        }
        Integer pageNum = searchParams.getPagenum();
        Integer pageSize = searchParams.getPagesize();
        searchParams.setPageindex(pageNum==0 ? 0:(pageNum-1)*pageSize);
        List<Role> roles = mapper.queryAllRole(searchParams);
        searchParams.setTotal(mapper.queryRoleCount(searchParams));
        HashMap<String, Object> map = new HashMap<>();
        if(keyword!=null){
            searchParams.setKeyword(keyword);
        }
        //装填参数
        map.put("roleList",roles);
        map.put("SearchParams",searchParams);
        return map;
    }

    public int addRole(Role role) {
        //填充Role
        //0为使用状态
        role.setStatue("0");
        role.setCreateBy(000);
        role.setCreateTime(new Date());
        role.setDelFlag(false);
        return mapper.addRole(role);
    }

    public int editRole(Role role) {
        return mapper.updateRole(role);
    }

    public int updateStatue(RoleStatuePo userStatuePo) {
        return mapper.updateStatue(userStatuePo);
    }

    public int deleteRole(Integer id) {
        return mapper.deleteRole(id);
    }
}
