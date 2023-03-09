package com.zhou.demo.controller;

import com.zhou.demo.controller.request.RoleRequest;
import com.zhou.demo.controller.request.SearchParams;
import com.zhou.demo.controller.request.UserRequest;
import com.zhou.demo.controller.response.UserResponse;
import com.zhou.demo.domain.CommonResult;
import com.zhou.demo.persist.po.Role;
import com.zhou.demo.persist.po.User;
import com.zhou.demo.persist.po.UserStatuePo;
import com.zhou.demo.service.GoodsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

/**
 * @ClassName GoodsController
 * @Author zz
 * @Date 2023/3/9 15:05
 * @Version 1.0
 * @Description 商品管理接口
 */

@RestController
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @PreAuthorize("hasAuthority('system:goods:list')")
    @GetMapping("/goods/querygoodsList")
    public CommonResult<Map> userList(@RequestParam("keyword") String keyword,
                                      @RequestParam("pagenum") Integer pagenum,
                                      @RequestParam("pagesize") Integer pagesize,
                                      @RequestParam("total") Integer total){
        SearchParams searchParams = new SearchParams();
        searchParams.setKeyword(keyword);
        searchParams.setPagenum(pagenum);
        searchParams.setPagesize(pagesize);
        Map map= goodsService.queryAllGoods(searchParams);
        return new CommonResult<Map>(200,"查询成功",map);
    }
    @PreAuthorize("hasAuthority('system:goods:list')")
    @PostMapping("/goods/goodsEdit")
    public CommonResult Editgoods(@RequestBody UserResponse userResponse){
        User user = new User();
        //工具类属性赋值
        BeanUtils.copyProperties(userResponse,user);

        int flag = goodsService.updateGoods(user);
        if(flag==0){
            return new CommonResult(200,"更新失败");
        }
        return new CommonResult(200,"更新成功");
    }

//    @GetMapping("/user/UserShow")
//    public CommonResult<User> UserShow(@RequestBody String id){
//        User user = goodsService.showUserById(Integer.parseInt(id));
//        if(Objects.isNull(user)){
//            return new CommonResult(200,"查询失败");
//        }
//        return new CommonResult(200,"查询成功",user);
//    }


    @PostMapping("/goods/addRole")
    public CommonResult addRole(@RequestBody RoleRequest roleRequest){
        Role role = new Role();
        BeanUtils.copyProperties(roleRequest,role);
        int flag =goodsService.addGoods(role);
        if(flag==0){
            return new CommonResult(200,"创建失败");
        }
        return new CommonResult(200,"创建成功");
    }


    @PreAuthorize("hasAuthority('system:goods:list')")
    @PutMapping("/goods/{id}/statue/{statue}")
    public CommonResult updateStatue(@PathVariable String id,
                                     @PathVariable String statue){
        UserStatuePo userStatuePo = new UserStatuePo(Integer.parseInt(id),statue);
        int flag =goodsService.updateStatue(userStatuePo);
        if(flag==0){
            return new CommonResult(200,"修改失败");
        }
        return new CommonResult(200,"修改成功");
    }

    @PreAuthorize("hasAuthority('system:goods:list')")
    @DeleteMapping("/goods/delUser/{id}")
    public CommonResult delUser(@PathVariable Integer  id){
        int flag =goodsService.deleteGoodsById(id);
        if(flag==0){
            return new CommonResult(200,"删除失败");
        }
        return new CommonResult(200,"删除成功");
    }
}
