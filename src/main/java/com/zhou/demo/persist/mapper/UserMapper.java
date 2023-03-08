package com.zhou.demo.persist.mapper;

import com.zhou.demo.controller.request.SearchParams;
import com.zhou.demo.controller.response.UserResponse;
import com.zhou.demo.persist.po.User;
import com.zhou.demo.persist.po.UserStatuePo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {


    List<UserResponse> queryAllUser(SearchParams searchParams);

    User queryUserById(String id);

    int addUser(User user);

    User queryUserByName(String userName);

    int updateUser(User user);

    User showUserById(Integer id);

    Integer queryUserCount(SearchParams searchParams);

    int deleteUser(Integer id);

    int updateStatue(UserStatuePo userStatuePo);

}
