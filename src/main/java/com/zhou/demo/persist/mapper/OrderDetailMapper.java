package com.zhou.demo.persist.mapper;

import com.zhou.demo.controller.request.SearchParams;
import com.zhou.demo.persist.po.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName OrderDetailMapper
 * @Author
 * @Date 2023/3/27 16:55
 * @Version
 * @Description
 */

@Mapper
public interface OrderDetailMapper {

    List<OrderDetail> queryAllOrder(SearchParams searchParams);

    Integer queryOrderCount(SearchParams searchParams);

}
