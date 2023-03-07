package com.zhou.demo.service;

import com.zhou.demo.controller.request.SearchParams;
import com.zhou.demo.controller.request.UserRequest;
import com.zhou.demo.domain.LoginUser;
import com.zhou.demo.persist.mapper.MenuMapper;
import com.zhou.demo.persist.mapper.UserMapper;
import com.zhou.demo.persist.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //查询用户信息
        User user = userMapper.queryUserByName(username);
        if(Objects.isNull(user)){
            //用户不存在抛异常 springsecurity中会捕获异常并处理。
            throw new RuntimeException("用户名或密码错误");
        }
        //用户存在封装成userDetails对象返回。实现Details接口。
        //TODO 查询对应的权限信息
        List<String> permissions = menuMapper.queryPermsByUserId(user.getId());
        System.out.println(permissions);
//        List<String> list =new ArrayList<>(Arrays.asList("test","admin"));
        return new LoginUser(user,permissions);
    }

    public Map<String,Object> queryAllUser(SearchParams searchParams) {
        //判断page大小和起始位置
        Integer pagenum = searchParams.getPagenum();
        Integer pagesize = searchParams.getPagesize();
        searchParams.setPageindex(pagenum==0 ?0:pagenum*pagesize);
        List<User> users = userMapper.queryAllUser(searchParams);
        searchParams.setTotal(userMapper.queryUserCount(searchParams));
        HashMap<String, Object> map = new HashMap<>();
        map.put("userList",users);
        map.put("SearchParams",searchParams);
        return map;
    }

    public User queryUserById(String id) {
        User user = userMapper.queryUserById(id);
        return user;
    }

    public int addUser(User user ,String userType) {
        user.setPassWord(passwordEncoder.encode(user.getPassWord()));
        Date date = new Date();
        //对象填充
        //设置为普通用户
        //绑定角色 -买家平台  买家角色id=2
        user.setRoleId(2);
        user.setUserType("1");
        user.setRoleId(1);
        user.setAvatar("**/**.img"); //配置默认图片
        user.setCreateTime(date);
        user.setCreateBy(2395);
        return userMapper.addUser(user);
    }

    public User queryUserByName(String userName) {
        return userMapper.queryUserByName(userName);
    }

    public User showUserById(Integer userid){
        return userMapper.showUserById(userid);
    }
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }
}
