package com.zhou.demo.persist.mapper;

import com.zhou.demo.controller.request.SearchParams;
import com.zhou.demo.persist.po.Order;
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

    Integer updateStatue(StatuePo StatuePo);

    Integer deleteOrder(Integer id, Integer userId);

    Integer updateOrder(Order Order);

    Integer queryOrderByKey(StatuePo StatuePo);

    Integer connectUserAndOrder(Integer orderId, Integer userId);

    String  queryOrderStatue(Integer id,Integer userId);
}
