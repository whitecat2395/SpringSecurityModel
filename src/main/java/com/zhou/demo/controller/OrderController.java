package com.zhou.demo.controller;

import com.zhou.demo.controller.request.OrderRequest;
import com.zhou.demo.controller.request.SearchParams;
import com.zhou.demo.domain.CommonResult;
import com.zhou.demo.persist.po.Goods;
import com.zhou.demo.persist.po.Order;
import com.zhou.demo.persist.po.OrderDetail;
import com.zhou.demo.persist.po.StatuePo;
import com.zhou.demo.service.GoodsService;
import com.zhou.demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName OrderController
 * @Author zz
 * @Date 2023/3/22 15:44
 * @Version 1.0
 * @Description 订单管理
 */

@Slf4j
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private GoodsService goodsService;

    @PreAuthorize("hasAuthority('system:order:list')")
    @GetMapping("/order/queryOrderList")
    public CommonResult<Map> queryorderList(@RequestParam("keyword") String keyword,
                                                 @RequestParam("pagenum") Integer pagenum,
                                                 @RequestParam("pagesize") Integer pagesize,
                                                 @RequestParam("total") Integer total){
        SearchParams searchParams = new SearchParams();
        searchParams.setKeyword(keyword);
        searchParams.setPagenum(pagenum);
        searchParams.setPagesize(pagesize);
        Map map= orderService.queryAllOrder(searchParams);
        return new CommonResult<Map>(200,"查询成功",map);
    }
    @PreAuthorize("hasAuthority('system:order:list')")
    @PostMapping("/order/EditOrder")
    public CommonResult EditOrder(@RequestBody OrderRequest orderRequest){
        Order order = new Order();
        BeanUtils.copyProperties(orderRequest,order);
        int flag = orderService.editOrder(order);
        if(flag==0){
            return new CommonResult(200,"更新失败");
        }
        return new CommonResult(200,"更新成功");
    }

    @PostMapping("/order/addOrder")
    public CommonResult addRole(@RequestBody OrderRequest orderRequest){
        Order order = new Order();
        BeanUtils.copyProperties(orderRequest,order);
        int flag = orderService.addOrder(order);
        if(flag==0){
            return new CommonResult(200,"创建失败");
        }
        return new CommonResult(200,"创建成功");
    }

    @PreAuthorize("hasAuthority('system:order:list')")
    @PutMapping("/order/{id}/statue/{status}")
    public CommonResult updateStatue(@PathVariable String id,
                                     @PathVariable String status){
        StatuePo orderStatuePo = new StatuePo();
        orderStatuePo.setId(Integer.parseInt(id));
        orderStatuePo.setStatus(status);
        int flag = orderService.updateStatue(orderStatuePo);
        if(flag==0){
            return new CommonResult(200,"修改失败");
        }
        return new CommonResult(200,"修改成功");
    }

    @PreAuthorize("hasAuthority('system:order:list')")
    @DeleteMapping("/order/delOrder/{id}")
    public CommonResult delUser(@PathVariable String  id){
        int flag =orderService.deleteOrder(id);
        if(flag==0){
            return new CommonResult(200,"删除失败");
        }
        return new CommonResult(200,"删除成功");
    }

    @GetMapping("/order/createOrder/{id}")
    public CommonResult createOrder(@PathVariable Integer id){
        Goods goods = goodsService.selectGoodsById(id);
        log.info("购买的商品是：",goods.getGoodsName());
        HashMap<String, String> map = new HashMap<>();
        if(ObjectUtils.isEmpty(goods)){
            return new CommonResult(200,"购买失败，商品已下架");
        }
        String  orderId = orderService.createOrder(goods);
        map.put("orderId",orderId);
        if("".equals(orderId)){
            return new CommonResult(200,"下单失败，请重试");
        }
        return new CommonResult(200,"下单成功",map);
    }

    @GetMapping("/order/selectOrderDetail/{orderId}")
    public CommonResult selectOrderDetail(@PathVariable String  orderId){
        OrderDetail orderDetail=orderService.selectOrderDetailByOrderId(orderId);
        HashMap<String,Object> map = new HashMap<>();
        if(ObjectUtils.isEmpty(orderDetail)){
            return new CommonResult(200,"获取订单详情失败");
        }
        map.put("orderDetail",orderDetail);
        return new CommonResult(200,"获取订单详情成功",map);
    }

    @GetMapping("/order/selectOrderDetail/noPay/{orderId}")
    public CommonResult selectOrderDetailNoPay(@PathVariable String  orderId){
        OrderDetail orderDetail=orderService.selectOrderDetailNoPay(orderId);
        HashMap<String,Object> map = new HashMap<>();
        if(ObjectUtils.isEmpty(orderDetail)){
            return new CommonResult(200,"获取订单详情失败");
        }
        map.put("orderDetail",orderDetail);
        return new CommonResult(200,"获取订单详情成功",map);
    }

}
