package com.zhou.demo.service;

import com.zhou.demo.persist.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl {

    @Autowired
    private RoleMapper mapper;

    //添加用户和角色关联关系
    public int addRoleByUserName(Integer userid ,Integer roleid) {
        return mapper.connectUserAndRole(userid,roleid);
    }
}
