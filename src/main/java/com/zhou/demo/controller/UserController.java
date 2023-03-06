package com.zhou.demo.controller;

import com.zhou.demo.controller.request.UserRequest;
import com.zhou.demo.domain.CommonResult;
import com.zhou.demo.persist.po.User;
import com.zhou.demo.service.RoleServiceImpl;
import com.zhou.demo.service.UserDetailsServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserDetailsServiceImpl userService;
    @Autowired
    RoleServiceImpl roleService;

    @PostMapping("/userlogin")
    public String userlogin(@RequestParam("username") String username,
                            @RequestParam("password") String password){
        userService.loadUserByUsername(username);
        return "";
    }
    @GetMapping("/user/userlist")
    public CommonResult<List> userlist(){
        List<User> users = userService.queryAllUser();
        return new CommonResult<List>(200,"查询成功",users);
    }
    @PostMapping("/user/addUser")
    public CommonResult adduser(@RequestBody UserRequest userRequest){
        User user = new User();
        //工具类属性赋值
        BeanUtils.copyProperties(userRequest,user);
        //userType=1，普通用户
        int flag = userService.addUser(user,"1");
        if(flag==0){
            return new CommonResult(200,"注册失败");
        }
        User user1 = userService.queryUserByName(user.getUserName());
        //为用户绑定角色
        int flag1 = roleService.addRoleByUserName(user1.getId(), 2);
        return new CommonResult(200,"注册成功");
    }
}
