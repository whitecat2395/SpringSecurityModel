package com.zhou.demo.controller;

import com.zhou.demo.controller.request.MenuRequest;
import com.zhou.demo.controller.request.SearchParams;
import com.zhou.demo.domain.CommonResult;
import com.zhou.demo.persist.po.Menu;
import com.zhou.demo.persist.po.MenuStatuePo;
import com.zhou.demo.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName MenuController
 * @Author zz
 * @Date 2023/3/10 14:26
 * @Version
 * @Description
 */

@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PreAuthorize("hasAuthority('system:menu:list')")
    @GetMapping("/menu/querymenuList")
    public CommonResult<Map> querymenuList(@RequestParam("keyword") String keyword,
                                      @RequestParam("pagenum") Integer pagenum,
                                      @RequestParam("pagesize") Integer pagesize,
                                      @RequestParam("total") Integer total){
        SearchParams searchParams = new SearchParams();
        searchParams.setKeyword(keyword);
        searchParams.setPagenum(pagenum);
        searchParams.setPagesize(pagesize);
        Map map= menuService.queryAllMenu(searchParams);
        return new CommonResult<Map>(200,"查询成功",map);
    }
    @PreAuthorize("hasAuthority('system:menu:list')")
    @PostMapping("/menu/menuEdit")
    public CommonResult EditMenu(@RequestBody MenuRequest MenuRequest){
        Menu menu = new Menu();
        BeanUtils.copyProperties(MenuRequest,menu);
        int flag = menuService.editMenu(menu);
        if(flag==0){
            return new CommonResult(200,"更新失败");
        }
        return new CommonResult(200,"更新成功");
}

    @PostMapping("/goods/addGoods")
    public CommonResult addRole(@RequestBody MenuRequest MenuRequest){
        Menu menu = new Menu();
        BeanUtils.copyProperties(MenuRequest,menu);
        int flag = menuService.addMenu(menu);
        if(flag==0){
            return new CommonResult(200,"创建失败");
        }
        return new CommonResult(200,"创建成功");
    }


    @PreAuthorize("hasAuthority('system:menu:list')")
    @PutMapping("/menu/{id}/statue/{status}")
    public CommonResult updateStatue(@PathVariable String id,
                                     @PathVariable String status){
        MenuStatuePo menuStatuePo = new MenuStatuePo();
        menuStatuePo.setId(Integer.parseInt(id));
        menuStatuePo.setStatus(status);
        int flag = menuService.updateStatue(menuStatuePo);
        if(flag==0){
            return new CommonResult(200,"修改失败");
        }
        return new CommonResult(200,"修改成功");
    }

    @PreAuthorize("hasAuthority('system:menu:list')")
    @DeleteMapping("/menu/delMenu/{id}")
    public CommonResult delUser(@PathVariable Integer  id){
        int flag =menuService.deleteMenu(id);
        if(flag==0){
            return new CommonResult(200,"删除失败");
        }
        return new CommonResult(200,"删除成功");
    }
}
