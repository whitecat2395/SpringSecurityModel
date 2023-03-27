package com.zhou.demo.controller;

import com.zhou.demo.controller.request.GoodsRequest;
import com.zhou.demo.controller.request.SearchParams;
import com.zhou.demo.domain.CommonResult;
import com.zhou.demo.persist.po.Goods;
import com.zhou.demo.persist.po.GoodsStatuePo;
import com.zhou.demo.service.GoodsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName selgoodsController
 * @Author zz
 * @Date 2023/3/21 16:28
 * @Version 1.0
 * @Description 卖家商品展示
 */
@RestController
public class SelgoodsController {

    @Autowired
    private GoodsService goodsService;

    @PreAuthorize("hasAuthority('system:goods:list')")
    @GetMapping("/selgoods/queryGoodsList")
    public CommonResult<Map> userList(@RequestParam("keyword") String keyword,
                                      @RequestParam("pagenum") Integer pagenum,
                                      @RequestParam("pagesize") Integer pagesize,
                                      @RequestParam("total") Integer total) {
        SearchParams searchParams = new SearchParams();
        searchParams.setKeyword(keyword);
        searchParams.setPagenum(pagenum);
        searchParams.setPagesize(pagesize);
        Map map = goodsService.queryAllGoodsBySelUser(searchParams);
        return new CommonResult<Map>(200, "查询成功", map);
    }

    @PreAuthorize("hasAuthority('system:goods:list')")
    @PostMapping("/selgoods/goodsEdit")
    public CommonResult Editgoods(@RequestBody GoodsRequest goodsRequest) {
        Goods goods = new Goods();
        BeanUtils.copyProperties(goodsRequest, goods);
        String statue = goodsService.querySataueBySelUser(goods.getId());
        if ("1".equals(statue)) {
            return new CommonResult(200, "商品已售出,无法修改");
        }
        int flag = goodsService.editGoodsBySelUser(goods);
        if (flag == 0) {
            return new CommonResult(200, "更新失败");
        }
        return new CommonResult(200, "更新成功");
    }

    @PostMapping("/selgoods/addGoods")
    public CommonResult addRole(@RequestBody GoodsRequest goodsRequest) {
        Goods goods = new Goods();
        BeanUtils.copyProperties(goodsRequest, goods);
        int flag = goodsService.addGoodsBySelUser(goods);
        if (flag == 0) {
            return new CommonResult(200, "创建失败");
        }
        return new CommonResult(200, "创建成功");
    }

    @PreAuthorize("hasAuthority('system:goods:list')")
    @PutMapping("/selgoods/{id}/statue/{statue}")
    public CommonResult updateStatue(@PathVariable String id,
                                     @PathVariable String statue) {
        GoodsStatuePo goodsStatuePo = new GoodsStatuePo();
        goodsStatuePo.setId(Integer.parseInt(id));
        goodsStatuePo.setStatue(statue);
        int flag = goodsService.updateStatueBySelUser(goodsStatuePo);
        if (flag == 0) {
            return new CommonResult(200, "修改失败");
        }
        return new CommonResult(200, "修改成功");
    }

    @PreAuthorize("hasAuthority('system:goods:list')")
    @DeleteMapping("/selgoods/delGoods/{id}")
    public CommonResult delUser(@PathVariable Integer id) {
        int flag = goodsService.deleteGoodsBySelUser(id);
        if (flag == 0) {
            return new CommonResult(200, "删除失败");
        }
        return new CommonResult(200, "删除成功");
    }
}
