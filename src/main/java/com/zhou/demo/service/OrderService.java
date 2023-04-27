package com.zhou.demo.service;

import com.zhou.demo.controller.request.SearchParams;
import com.zhou.demo.domain.LoginUser;
import com.zhou.demo.persist.mapper.OrderDetailMapper;
import com.zhou.demo.persist.mapper.OrderMapper;
import com.zhou.demo.persist.po.*;
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
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderDetailMapper OrderDetailmapper;

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
//        Integer orderId = mapper.queryOrderByKey(orderStatuePo);
        //创建用户和商品关联关系
//        Integer flag1 =mapper.connectUserAndOrder(orderId,userId);
        return 0;
    }

    public int editOrder(Order order) {
        Date date = new Date();
        order.setUpdateTime(date);
        return mapper.updateOrder(order);
    }

    public int updateStatue(OrderStatuePo orderStatuePo) {
        orderStatuePo.setUpdateTime(new Date());
        return mapper.updateStatue(orderStatuePo);
    }

    public int deleteOrder(String  id) {
        return mapper.deleteOrder(id,null);
    }

    public String  querySatau(String  id) {
        return mapper.queryOrderStatus(id);
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
        List<OrderDetail> orderList = OrderDetailmapper.queryAllOrder(searchParams);
        searchParams.setTotal(OrderDetailmapper.queryOrderCount(searchParams));
        HashMap<String, Object> map = new HashMap<>();
        if(keyword!=null){
            searchParams.setKeyword(keyword);
        }
        //装填参数
        map.put("orderList",orderList);
        map.put("SearchParams",searchParams);
        return map;
    }

    public String querySatausBySelUser(String id) {
        //从handler中获取当前用户的信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser= (LoginUser)authentication.getPrincipal();
        Integer userId = loginUser.getUser().getId();
        return mapper.queryOrderStatus(id);
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

    public int addOrderBySelUser(Order order) {
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
//        Integer orderId = mapper.queryOrderByKey(orderStatuePo);
        //创建用户和订单关联关系
//        Integer flag1 =mapper.connectUserAndOrder(orderId,userId);
        return 0;

    }

    public int updateStatueBySelUser(OrderStatuePo orderStatuePo) {
        //从handler中获取当前用户的信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser= (LoginUser)authentication.getPrincipal();
        Integer userId = loginUser.getUser().getId();
        orderStatuePo.setUserId(userId);
        orderStatuePo.setStatus("0");
        return mapper.updateStatue(orderStatuePo);
    }

    public int deleteOrderBySelUser(String  id) {
        //从handler中获取当前用户的信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser= (LoginUser)authentication.getPrincipal();
        Integer userId = loginUser.getUser().getId();
        return mapper.deleteOrder(id,userId);
    }

    public String  createOrder(Goods goods) {
        //从handler中获取当前用户的信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser= (LoginUser)authentication.getPrincipal();
        Integer userId = loginUser.getUser().getId();

        Order order = new Order();
        String uuId = UUID.randomUUID().toString();
        order.setId(uuId);
        order.setUserId(userId);
        //未支付
        order.setStatus("0");
        order.setCreateTime(new Date());
        order.setDelFlag(false);
        Integer flag = mapper.addOrder(order);
        if(flag==0){
            return "";
        }
        //为订单绑定商品
        String result = bandingGoods(goods,order.getId());
        if("".equals(result)){
            return "";
        }
        return order.getId();
    }

    private String bandingGoods(Goods goods, String id) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setSellerId(goods.getUserId());
        orderDetail.setGoodsId(goods.getId());
        orderDetail.setGoodsImg(goods.getPicture());
        orderDetail.setGoodsName(goods.getGoodsName());
        orderDetail.setGoodsPrice(goods.getPrice());
        orderDetail.setBuyCount(1);
        orderDetail.setStatus("0");
        orderDetail.setCreateTime(new Date());
        orderDetail.setDelFlag(false);
        orderDetail.setOrderId(id);
        Integer flag = mapper.addOrderDetail(orderDetail);
        if(flag==0){
            return "";
        }
        return "绑定成功";
    }

    public OrderDetail selectOrderDetailByOrderId(String  orderId) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(orderId);
        return mapper.selectOrderDetail(orderDetail);
    }

    public OrderDetail selectOrderDetailNoPay(String orderId) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(orderId);
        orderDetail.setStatus("0");
        return mapper.selectOrderDetail(orderDetail);
    }

    public String selectorderStatusByOrderId(String orderId) {
        return mapper.queryOrderStatus(orderId);
    }

}
