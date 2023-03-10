package com.zhou.demo.service;

import com.zhou.demo.controller.request.SearchParams;
import com.zhou.demo.domain.LoginUser;
import com.zhou.demo.persist.mapper.MenuMapper;
import com.zhou.demo.persist.po.Goods;
import com.zhou.demo.persist.po.GoodsStatuePo;
import com.zhou.demo.persist.po.Menu;
import com.zhou.demo.persist.po.MenuStatuePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName MenuService
 * @Author
 * @Date 2023/3/10 14:27
 * @Version
 * @Description
 */

@Service
public class MenuService {

    @Autowired
    private MenuMapper mapper;
    public Map queryAllMenu(SearchParams searchParams) {
        //判断page大小和起始位置
        String keyword=searchParams.getKeyword();
        if(keyword != "") {
            searchParams.setKeyword("%"+keyword+"%");
        }
        Integer pageNum = searchParams.getPagenum();
        Integer pageSize = searchParams.getPagesize();
        searchParams.setPageindex(pageNum==0 ? 0:(pageNum-1)*pageSize);
        List<Menu> goodsList = mapper.queryAllMenus(searchParams);
        searchParams.setTotal(mapper.queryMenuCount(searchParams));
        HashMap<String, Object> map = new HashMap<>();
        if(keyword!=null){
            searchParams.setKeyword(keyword);
        }
        //装填参数
        map.put("goodsList",goodsList);
        map.put("SearchParams",searchParams);
        return map;
    }

    public int addMenu(Menu menu) {
        //填充Role
        //从handler中获取当前用户的信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser= (LoginUser)authentication.getPrincipal();
        Integer userId = loginUser.getUser().getId();
        //0为使用状态
        menu.setCreateTime(new Date());
        menu.setDelFlag(false);
        menu.setStatus("0");
        Integer flag = mapper.addMenu(menu);
        if(flag==0){
            return flag;
        }
        return flag;
    }

    public int updateStatue(MenuStatuePo menuStatuePo) {
        return mapper.updateStatue(menuStatuePo);
    }

    public int deleteMenu(Integer id) {
        return mapper.deleteMenu(id);
    }

    public int editMenu(Menu menu) {
        Date date = new Date();
        menu.setUpdateTime(date);
        return mapper.updateMenu(menu);
    }
}
