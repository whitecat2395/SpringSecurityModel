package com.zhou.demo.service;

import com.zhou.demo.controller.request.SearchParams;
import com.zhou.demo.domain.LoginUser;
import com.zhou.demo.persist.mapper.GoodsMapper;
import com.zhou.demo.persist.mapper.RoleMapper;
import com.zhou.demo.persist.po.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.*;

/**
 * @ClassName GoodsService
 * @Author
 * @Date 2023/3/9 15:10
 * @Version
 * @Description
 */

@Service
public class GoodsService {

    @Autowired
    private GoodsMapper mapper;

    public Map<String,Object> queryAllGoods(SearchParams searchParams) {
        //判断page大小和起始位置
        String keyword=searchParams.getKeyword();
        if(keyword != "") {
            searchParams.setKeyword("%"+keyword+"%");
        }
        Integer pageNum = searchParams.getPagenum();
        Integer pageSize = searchParams.getPagesize();
        searchParams.setPageindex(pageNum==0 ? 0:(pageNum-1)*pageSize);
        List<Goods> goodsList = mapper.queryAllGoods(searchParams);
        searchParams.setTotal(mapper.queryGoodsCount(searchParams));
        HashMap<String, Object> map = new HashMap<>();
        if(keyword!=null){
            searchParams.setKeyword(keyword);
        }
        //装填参数
        map.put("goodsList",goodsList);
        map.put("SearchParams",searchParams);
        return map;
    }

    public int addGoods(Goods goods) {
        //填充Role
        //从handler中获取当前用户的信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser= (LoginUser)authentication.getPrincipal();
        Integer userId = loginUser.getUser().getId();
        //0为使用状态
        goods.setUserId(userId);
        String key= UUID.randomUUID().toString();
        goods.setUukey(key);
        goods.setCreateTime(new Date());
        goods.setDelFlag(false);
        goods.setSellStatus("0");
        Integer flag = mapper.addGoods(goods);
        if(flag==0){
            return flag;
        }
        GoodsStatuePo goodsStatuePo = new GoodsStatuePo();
        goodsStatuePo.setUuKey(key);
        goodsStatuePo.setUserId(userId);
        Integer GoodsId = mapper.queryGoodsByKey(goodsStatuePo);
        //创建用户和商品关联关系
        Integer flag1 =mapper.connectUserAndGoods(GoodsId,userId);
        return flag1;
    }

    public int editGoods(Goods goods) {
        Date date = new Date();
        goods.setUpdateTime(date);
        return mapper.updateGoods(goods);
    }

    public int updateStatue(GoodsStatuePo goodsStatuePo) {
        return mapper.updateStatue(goodsStatuePo);
    }

    public int deleteGoods(Integer id) {
        return mapper.deleteGoods(id,null);
    }

    public String  querySataue(Integer id) {
        return mapper.queryGoodsStatue(id,null);
    }

    public Map queryAllGoodsBySelUser(SearchParams searchParams) {
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
        List<Goods> goodsList = mapper.queryAllGoods(searchParams);
        searchParams.setTotal(mapper.queryGoodsCount(searchParams));
        HashMap<String, Object> map = new HashMap<>();
        if(keyword!=null){
            searchParams.setKeyword(keyword);
        }
        //装填参数
        map.put("goodsList",goodsList);
        map.put("SearchParams",searchParams);
        return map;
    }

    public String querySataueBySelUser(Integer id) {
        //从handler中获取当前用户的信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser= (LoginUser)authentication.getPrincipal();
        Integer userId = loginUser.getUser().getId();
        return mapper.queryGoodsStatue(id,userId);
    }

    public int editGoodsBySelUser(Goods goods) {
        //从handler中获取当前用户的信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser= (LoginUser)authentication.getPrincipal();
        Integer userId = loginUser.getUser().getId();
        Date date = new Date();
        goods.setUpdateTime(date);
        return mapper.updateGoods(goods);
    }

    public int addGoodsBySelUser(Goods goods) {
        //填充Role
        //从handler中获取当前用户的信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser= (LoginUser)authentication.getPrincipal();
        Integer userId = loginUser.getUser().getId();
        //0为使用状态
        goods.setUserId(userId);
        String key= UUID.randomUUID().toString();
        goods.setUukey(key);
        goods.setCreateTime(new Date());
        goods.setDelFlag(false);
        goods.setSellStatus("0");
        Integer flag = mapper.addGoods(goods);
        if(flag==0){
            return flag;
        }
        GoodsStatuePo goodsStatuePo = new GoodsStatuePo();
        goodsStatuePo.setUuKey(key);
        goodsStatuePo.setUserId(userId);
        Integer GoodsId = mapper.queryGoodsByKey(goodsStatuePo);
        //创建用户和商品关联关系
        Integer flag1 =mapper.connectUserAndGoods(GoodsId,userId);
        return flag1;

    }

    public int updateStatueBySelUser(GoodsStatuePo goodsStatuePo) {
        //从handler中获取当前用户的信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser= (LoginUser)authentication.getPrincipal();
        Integer userId = loginUser.getUser().getId();
        goodsStatuePo.setUserId(userId);
        return mapper.updateStatue(goodsStatuePo);
    }

    public int deleteGoodsBySelUser(Integer id) {
        //从handler中获取当前用户的信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser= (LoginUser)authentication.getPrincipal();
        Integer userId = loginUser.getUser().getId();
        return mapper.deleteGoods(id,userId);
    }

    public Map queryGoodsByCategoryId(SearchParams searchParams) {
        //判断page大小和起始位置
        String keyword=searchParams.getKeyword();
        if(keyword != "") {
            searchParams.setKeyword("%"+keyword+"%");
        }
        Integer pageNum = searchParams.getPagenum();
        Integer pageSize = searchParams.getPagesize();
        searchParams.setPageindex(pageNum==0 ? 0:(pageNum-1)*pageSize);
        List<Goods> tableData = mapper.queryAllGoods(searchParams);
        searchParams.setTotal(mapper.queryGoodsCount(searchParams));
        HashMap<String, Object> map = new HashMap<>();
        if(keyword!=null){
            searchParams.setKeyword(keyword);
        }
        //装填参数
        map.put("tableData",tableData);
        map.put("SearchParams",searchParams);
        return map;
    }

    public Goods selectGoodsById(Integer goodsId) {
        return mapper.selectGoodsById(goodsId);
    }

}
