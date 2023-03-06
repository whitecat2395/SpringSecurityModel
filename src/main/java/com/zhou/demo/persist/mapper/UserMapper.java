package com.zhou.demo.persist.mapper;

import com.zhou.demo.persist.po.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {


    List<User> queryAllUser();

    User queryUserById(String id);

    int addUser(User user);

    User queryUserByName(String userName);

}
