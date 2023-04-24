package com.zhou.demo.controller;

import com.zhou.demo.controller.request.SearchParams;
import com.zhou.demo.controller.response.TableData;
import com.zhou.demo.domain.CommonResult;
import com.zhou.demo.persist.po.Goods;
import com.zhou.demo.service.GoodsService;
import com.zhou.demo.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TabelDataController
 * @Author zz
 * @Date 2023/3/29 13:50
 * @Discription 商城前台商品数据模块
 * @Version
 * @Description
 */

@RestController
public class TabelDataController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private PhotoService photoService;

    @PreAuthorize("hasAuthority('system:goods:list')")
    @GetMapping("/tableData/queryGoodsList")
    public CommonResult<Map> goodsList(@RequestParam("keyword") String keyword,
                                      @RequestParam("pagenum") Integer pagenum,
                                      @RequestParam("pagesize") Integer pagesize,
                                      @RequestParam("total") Integer total){
        SearchParams searchParams = new SearchParams();
        searchParams.setKeyword(keyword);
        searchParams.setPagenum(pagenum);
        searchParams.setPagesize(pagesize);
        Map map= goodsService.queryGoodsByCategoryId(searchParams);
        return new CommonResult<Map>(200,"查询成功",map);
    }

    @PreAuthorize("hasAuthority('system:goods:list')")
    @GetMapping("/tableData/queryGoodsDetail/{goodsId}")
    public CommonResult<Map> userList(@PathVariable Integer goodsId ){
        HashMap<String , Object> map = new HashMap<>();
        Goods goods =goodsService.selectGoodsById(goodsId);
        List<String> urls=photoService.selectPhotoUrlByGoodsId(goodsId);
        TableData tableData = new TableData();
        tableData.setId(goodsId.toString());
        tableData.setTitle(goods.getGoodsName());
        tableData.setDetail(goods.getGoodsDetail());
        tableData.setPrice(goods.getPrice().toString());
        tableData.setUrls(urls);
        map.put("tableData",tableData);
        return new CommonResult<Map>(200,"查询成功",map);
    }
}
