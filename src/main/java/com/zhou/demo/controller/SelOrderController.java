package com.zhou.demo.controller;

/**
 * @ClassName SelOrderController
 * @Author zz
 * @Date 2023/3/29 13:28
 * @Version 1.0
 * @Description 订单模块
 */

import com.zhou.demo.controller.request.OrderRequest;
import com.zhou.demo.controller.request.SearchParams;
import com.zhou.demo.domain.CommonResult;
import com.zhou.demo.persist.po.Order;
import com.zhou.demo.persist.po.StatuePo;
import com.zhou.demo.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName selroleController
 * @Author zz
 * @Date 2023/3/27 15:09
 * @Version 1.0
 * @Description
 */
@RestController
public class SelOrderController implements InitializingBean {


    @Autowired
    private OrderService orderService;

    @PreAuthorize("hasAuthority('system:order:list')")
    @GetMapping("/selorder/queryOrderList")
    public CommonResult<Map> userList(@RequestParam("keyword") String keyword,
                                      @RequestParam("pagenum") Integer pagenum,
                                      @RequestParam("pagesize") Integer pagesize,
                                      @RequestParam("total") Integer total) {
        SearchParams searchParams = new SearchParams();
        searchParams.setKeyword(keyword);
        searchParams.setPagenum(pagenum);
        searchParams.setPagesize(pagesize);
        Map map = orderService.queryAllOrderBySelUser(searchParams);
        return new CommonResult<Map>(200, "查询成功", map);
    }

    @PreAuthorize("hasAuthority('system:order:list')")
    @PostMapping("/selorder/editOrder")
    public CommonResult editOrder(@RequestBody OrderRequest orderRequest) {
        Order order = new Order();
        BeanUtils.copyProperties(orderRequest, order);
        String statue = orderService.querySataueBySelUser(order.getId());
        if ("1".equals(statue)) {
            return new CommonResult(200, "商品已售出,无法修改");
        }
        int flag = orderService.editOrderBySelUser(order);
        if (flag == 0) {
            return new CommonResult(200, "更新失败");
        }
        return new CommonResult(200, "更新成功");
    }

    @PostMapping("/selorder/addOrder")
    public CommonResult addRole(@RequestBody OrderRequest orderRequest) {
        Order order = new Order();
        BeanUtils.copyProperties(orderRequest, order);
        int flag = orderService.addOrderBySelUser(order);
        if (flag == 0) {
            return new CommonResult(200, "创建失败");
        }
        return new CommonResult(200, "创建成功");
    }
    @PreAuthorize("hasAuthority('system:order:list')")
    @PutMapping("/selorder/{id}/statue/{statue}")
    public CommonResult updateStatue(@PathVariable String id,
                                     @PathVariable String status) {
        StatuePo orderStatuePo = new StatuePo();
        orderStatuePo.setId(Integer.parseInt(id));
        orderStatuePo.setStatus(status);
        int flag = orderService.updateStatueBySelUser(orderStatuePo);
        if (flag == 0) {
            return new CommonResult(200, "订单修改失败");
        }
        return new CommonResult(200, "订单修改成功");
    }

    @PreAuthorize("hasAuthority('system:order:list')")
    @DeleteMapping("/selorder/delOrder/{id}")
    public CommonResult delUser(@PathVariable String  id) {
        int flag = orderService.deleteOrderBySelUser(id);
        if (flag == 0) {
            return new CommonResult(200, "删除失败");
        }
        return new CommonResult(200, "删除成功");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("当前是初始化方法：SelOrderController实例化了");
    }
}

