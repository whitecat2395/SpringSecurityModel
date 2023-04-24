package com.zhou.demo.persist.mapper;

import com.zhou.demo.controller.request.SearchParams;
import com.zhou.demo.persist.po.Order;
import com.zhou.demo.persist.po.OrderDetail;
import com.zhou.demo.persist.po.OrderStatuePo;
import com.zhou.demo.persist.po.StatuePo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName OrderMapper
 * @Author
 * @Date 2023/3/22 15:58
 * @Version
 * @Description
 */
@Mapper
public interface OrderMapper {

    List<Order> queryAllOrder(SearchParams searchParams);

    Integer addOrder(Order order);

    Integer queryOrderCount(SearchParams searchParams);

    Integer updateStatue(OrderStatuePo StatuePo);

    Integer deleteOrder(String id, Integer userId);

    Integer updateOrder(Order Order);


    Integer connectUserAndOrder(Integer orderId, Integer userId);

    String  queryOrderStatus(String id);

    Integer addOrderDetail(OrderDetail orderDetail);

    OrderDetail selectOrderDetail(OrderDetail orderDetail);


}
