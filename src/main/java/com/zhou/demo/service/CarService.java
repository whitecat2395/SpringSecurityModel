package com.zhou.demo.service;

import com.zhou.demo.controller.request.SearchParams;
import com.zhou.demo.domain.LoginUser;
import com.zhou.demo.persist.mapper.CarMapper;
import com.zhou.demo.persist.po.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map showGoods(SearchParams searchParams) {
        //从handler中获取当前用户的信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser= (LoginUser)authentication.getPrincipal();
        Integer userId = loginUser.getUser().getId();
        //判断page大小和起始位置
        String keyword=searchParams.getKeyword();
        if(keyword != "") {
            searchParams.setKeyword("%"+keyword+"%");
        }
        Integer pageNum = searchParams.getPagenum();
        Integer pageSize = searchParams.getPagesize();
        searchParams.setPageindex(pageNum==0 ? 0:(pageNum-1)*pageSize);
        searchParams.setUserId(userId);
        List<Goods> goodsList = carMapper.selectCarGoodsByUserId(searchParams);
        int count = carMapper.selectCarCountByUserId(userId);
        searchParams.setTotal(carMapper.selectCarCountByUserId(userId));
        if(keyword!=null){
            searchParams.setKeyword(keyword);
        }
        Map map =new HashMap();
        map.put("goodsList",goodsList);
        map.put("SearchParams",searchParams);
        return map;
    }

}
