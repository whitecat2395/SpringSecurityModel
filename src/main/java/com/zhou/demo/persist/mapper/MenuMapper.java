package com.zhou.demo.persist.mapper;

import com.zhou.demo.controller.request.SearchParams;
import com.zhou.demo.persist.po.Goods;
import com.zhou.demo.persist.po.Menu;
import com.zhou.demo.persist.po.MenuStatuePo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<String> queryPermsByUserId(Integer id);

    List<Menu> queryAllMenus(SearchParams searchParams);

    Integer queryMenuCount(SearchParams searchParams);

    int updateStatue(MenuStatuePo menuStatuePo);

    Integer addMenu(Menu menu);

    int deleteMenu(Integer id);

    int updateMenu(Menu menu);

}
