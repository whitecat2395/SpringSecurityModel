package com.zhou.demo.service;

import com.zhou.demo.controller.request.UserRequest;
import com.zhou.demo.domain.CommonResult;
import com.zhou.demo.domain.LoginUser;
import com.zhou.demo.persist.po.User;
import com.zhou.demo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisTemplate redisTemplate;

    public CommonResult<Map<String, String>> loginUser(UserRequest user) {

        //AuthenticationManager autheticate进行用户认证
        UsernamePasswordAuthenticationToken uptoken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassWord());
        Authentication authenticate = authenticationManager.authenticate(uptoken);
        //如果认证没通过，给出提示
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("登录失败");
//            return new CommonResult(200,"登录失败");
        }

        //如果认证通过，使用userid生成一个jwt、
        LoginUser loginUser =(LoginUser) authenticate.getPrincipal();
        String userid = loginUser.getUser().getId().toString();
        System.out.println("userid: "+userid);
        String jwt = JwtUtils.createToken(userid);
        System.out.println("token:"+jwt);
        Map<String, String> map = new HashMap<>();
        map.put("token",jwt);
        //把完整的用户信息存入redis中 userid作为key。
        redisTemplate.opsForValue().set("login:"+userid,loginUser);
        return new CommonResult<>(200,"登录成功",map);
    }

    public CommonResult logout() {
        //1.先去SecurityContextHolder认证中获取用户id
        // 由于在同一个过滤器链中，所以退出之前还是会经过之前的jwtAuthenticationTokenFilter
        // 中的SecurityContextHolder,所以，可以在SecurityContextHolder中获取到userid
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser= (LoginUser)authentication.getPrincipal();
        Integer userid = loginUser.getUser().getId();

        //2.在redis中删除这个用户的token。
        Boolean flag = redisTemplate.delete("login:" + userid);
        System.out.println("退出结果："+flag);
        if(flag==false){
            return new CommonResult(200,"退出失败");
        }
        Map<String, String> map = new HashMap<>();
        return new CommonResult(200, "退出成功");
    }
}