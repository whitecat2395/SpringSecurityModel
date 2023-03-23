package com.zhou.demo.service;

import com.zhou.demo.controller.request.SearchParams;
import com.zhou.demo.domain.LoginUser;
import com.zhou.demo.persist.mapper.OrderMapper;
import com.zhou.demo.persist.po.Order;
import com.zhou.demo.persist.po.StatuePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName OrderService
 * @Author
 * @Date 2023/3/22 15:52
 * @Version
 * @Description
 */

@Service
public class OrderService {

    @Autowired
    private OrderMapper mapper;

    public Map<String,Object> queryAllOrder(SearchParams searchParams) {
        //判断page大小和起始位置
        String keyword=searchParams.getKeyword();
        if(keyword != "") {
            searchParams.setKeyword("%"+keyword+"%");
        }
        Integer pageNum = searchParams.getPagenum();
        Integer pageSize = searchParams.getPagesize();
        searchParams.setPageindex(pageNum==0 ? 0:(pageNum-1)*pageSize);
        List<Order> orderList = mapper.queryAllOrder(searchParams);
        searchParams.setTotal(mapper.queryOrderCount(searchParams));
        HashMap<String, Object> map = new HashMap<>();
        if(keyword!=null){
            searchParams.setKeyword(keyword);
        }
        //装填参数
        map.put("orderList",orderList);
        map.put("SearchParams",searchParams);
        return map;
    }

    public int addOrder(Order order) {
        //填充Role
        //从handler中获取当前用户的信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser= (LoginUser)authentication.getPrincipal();
        Integer userId = loginUser.getUser().getId();
        //0为使用状态
        order.setUserId(userId);
        order.setCreateTime(new Date());
        order.setDelFlag(false);
        order.setStatus("0");
        Integer flag = mapper.addOrder(order);
        if(flag==0){
            return flag;
        }
        StatuePo orderStatuePo = new StatuePo();
        Integer orderId = mapper.queryOrderByKey(orderStatuePo);
        //创建用户和商品关联关系
        Integer flag1 =mapper.connectUserAndOrder(orderId,userId);
        return flag1;
    }

    public int editOrder(Order order) {
        Date date = new Date();
        order.setUpdateTime(date);
        return mapper.updateOrder(order);
    }

    public int updateStatue(StatuePo StatuePo) {
        return mapper.updateStatue(StatuePo);
    }

    public int deleteOrder(Integer id) {
        return mapper.deleteOrder(id,null);
    }

    public String  querySataue(Integer id) {
        return mapper.queryOrderStatue(id,null);
    }

    public Map queryAllOrderBySelUser(SearchParams searchParams) {
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
        List<Order> orderList = mapper.queryAllOrder(searchParams);
        searchParams.setTotal(mapper.queryOrderCount(searchParams));
        HashMap<String, Object> map = new HashMap<>();
        if(keyword!=null){
            searchParams.setKeyword(keyword);
        }
        //装填参数
        map.put("orderList",orderList);
        map.put("SearchParams",searchParams);
        return map;
    }

    public String querySataueBySelUser(Integer id) {
        //从handler中获取当前用户的信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser= (LoginUser)authentication.getPrincipal();
        Integer userId = loginUser.getUser().getId();
        return mapper.queryOrderStatue(id,userId);
    }

    public int editOrderBySelUser(Order order) {
        //从handler中获取当前用户的信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser= (LoginUser)authentication.getPrincipal();
        Integer userId = loginUser.getUser().getId();
        Date date = new Date();
        order.setUpdateTime(date);
        return mapper.updateOrder(order);
    }

    public int addOrderByUser(Order order) {
        //填充Role
        //从handler中获取当前用户的信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser= (LoginUser)authentication.getPrincipal();
        Integer userId = loginUser.getUser().getId();
        //0为使用状态
        order.setUserId(userId);
        order.setCreateTime(new Date());
        order.setDelFlag(false);
        order.setStatus("0");
        Integer flag = mapper.addOrder(order);
        if(flag==0){
            return flag;
        }
        StatuePo orderStatuePo = new StatuePo();
        orderStatuePo.setUserId(userId);
        Integer orderId = mapper.queryOrderByKey(orderStatuePo);
        //创建用户和订单关联关系
        Integer flag1 =mapper.connectUserAndOrder(orderId,userId);
        return flag1;

    }

    public int updateStatueByUser(StatuePo orderStatuePo) {
        //从handler中获取当前用户的信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser= (LoginUser)authentication.getPrincipal();
        Integer userId = loginUser.getUser().getId();
        orderStatuePo.setUserId(userId);
        return mapper.updateStatue(orderStatuePo);
    }

    public int deleteOrderBySelUser(Integer id) {
        //从handler中获取当前用户的信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser= (LoginUser)authentication.getPrincipal();
        Integer userId = loginUser.getUser().getId();
        return mapper.deleteOrder(id,userId);
    }
}
