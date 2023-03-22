package com.zhou.demo.persist.mapper;

import com.zhou.demo.controller.request.SearchParams;
import com.zhou.demo.persist.po.Goods;
import com.zhou.demo.persist.po.GoodsStatuePo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName GoodsMapper
 * @Author
 * @Date 2023/3/9 15:18
 * @Version
 * @Description
 */

@Mapper
public interface GoodsMapper {

    List<Goods> queryAllGoods(SearchParams searchParams);

    Integer addGoods(Goods goods);

    Integer queryGoodsCount(SearchParams searchParams);

    Integer updateStatue(GoodsStatuePo goodsStatuePo);

    Integer deleteGoods(Integer id,Integer userId);

    Integer updateGoods(Goods goods);

    Integer queryGoodsByKey(GoodsStatuePo goodsStatuePo);

    Integer connectUserAndGoods(Integer goodsId, Integer userId);

    String  queryGoodsStatue(Integer id,Integer userId);
}
