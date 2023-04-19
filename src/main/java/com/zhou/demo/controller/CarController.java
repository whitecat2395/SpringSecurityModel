package com.zhou.demo.controller;

import com.zhou.demo.controller.request.SearchParams;
import com.zhou.demo.domain.CommonResult;
import com.zhou.demo.domain.LoginUser;
import com.zhou.demo.persist.po.Goods;
import com.zhou.demo.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CarController
 * @Author zz
 * @Date 2023/4/10 16:33
 * @Version 1.0
 * @Description
 */

@Slf4j
@RestController
public class CarController {
    @Autowired
    private CarService carService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/car/addgoods/{id}")
    public  CommonResult carAddGoods(@PathVariable Integer id){
        CommonResult<Map> commonResult = new CommonResult<Map>();
        //从handler中获取当前用户的信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser= (LoginUser)authentication.getPrincipal();
        Integer userId = loginUser.getUser().getId();
        int result = carService.addGoods(id,userId);
        if(result==1){
            log.info("加入购物车成功:{}",result);
        }
        //查询购物车count
        int count =  carService.selectCarCountByUserId(userId);
        //TODO
        HashMap<String,Integer> map = new HashMap<>();
        map.put("count",count);
        commonResult.setData(map);
        return commonResult;
    }
    @GetMapping("/car/queryGoodsList")
    public  CommonResult queryGoodsList(@RequestParam("keyword") String keyword,
                                        @RequestParam("pagenum") Integer pagenum,
                                        @RequestParam("pagesize") Integer pagesize,
                                        @RequestParam("total") Integer total){
        SearchParams searchParams = new SearchParams();
        searchParams.setKeyword(keyword);
        searchParams.setPagenum(pagenum);
        searchParams.setPagesize(pagesize);
        Map map = carService.showGoods(searchParams);
        return new CommonResult<Map>(200,"查询成功",map);
    }

}
