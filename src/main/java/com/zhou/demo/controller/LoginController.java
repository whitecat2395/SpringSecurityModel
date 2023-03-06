package com.zhou.demo.controller;

import com.zhou.demo.controller.request.UserRequest;
import com.zhou.demo.domain.CommonResult;
import com.zhou.demo.persist.po.User;
import com.zhou.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public CommonResult<Map<String, String>> login(@RequestBody UserRequest user){
        return loginService.loginUser(user);

    }
    @GetMapping("/user/logout")
    public CommonResult logOut(){
        return loginService.logout();
    }
}
