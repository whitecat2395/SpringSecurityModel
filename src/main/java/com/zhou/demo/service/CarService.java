package com.zhou.demo.service;

import com.zhou.demo.domain.LoginUser;
import com.zhou.demo.persist.mapper.CarMapper;
import com.zhou.demo.persist.po.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName CarService
 * @Author
 * @Date 2023/4/10 16:47
 * @Version
 * @Description
 */

@Component
public class CarService {

    @Autowired
    private CarMapper carMapper;

    public int addGoods(Integer id,Integer userId) {
        return carMapper.addGoods(id,userId);
    }

    public List<Goods> selectCarByUserId(Integer userId) {
        return carMapper.selectCarByUserId(userId);
    }

    public int selectCarCountByUserId(Integer userId) {
        return carMapper.selectCarCountByUserId(userId);
    }
}
