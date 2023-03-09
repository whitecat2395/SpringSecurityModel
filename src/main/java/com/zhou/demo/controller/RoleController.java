package com.zhou.demo.controller;

import com.zhou.demo.controller.request.RoleRequest;
import com.zhou.demo.controller.request.SearchParams;
import com.zhou.demo.domain.CommonResult;
import com.zhou.demo.persist.po.Role;
import com.zhou.demo.persist.po.RoleStatuePo;
import com.zhou.demo.persist.po.UserStatuePo;
import com.zhou.demo.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName RoleController
 * @Author zz
 * @Date 2023/3/9 10:26
 * @Version
 * @Description
 */

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;



    @GetMapping("/role/queryRoleList")
    public CommonResult<Map>  roleList(@RequestParam("keyword") String keyword,
                                 @RequestParam("pagenum") Integer pagenum,
                                 @RequestParam("pagesize") Integer pagesize,
                                 @RequestParam("total") Integer total){
        SearchParams searchParams = new SearchParams();
        searchParams.setKeyword(keyword);
        searchParams.setPagenum(pagenum);
        searchParams.setPagesize(pagesize);
        Map<String,Object> map = roleService.queryAllRole(searchParams);
        return new CommonResult<Map> (200,"查询成功",map);
    }

    @PostMapping("/role/addRole")
    public CommonResult addRole(@RequestBody RoleRequest roleRequest){
        Role role = new Role();
        BeanUtils.copyProperties(roleRequest,role);
        int flag =roleService.addRole(role);
        if(flag==0){
            return new CommonResult(200,"创建失败");
        }
        return new CommonResult(200,"创建成功");
    }
    @PostMapping("/role/RoleEdit")
    public CommonResult EditRole(@RequestBody Role  role){
        int flag = roleService.editRole(role);
        if(flag==0) {
            return new CommonResult(200,"修改失败");
        }
        return new CommonResult(200,"修改成功");
    }

    @PreAuthorize("hasAuthority('system:role:list')")
    @PutMapping("/role/{id}/statue/{statue}")
    public CommonResult updateStatue(@PathVariable String id,
                                     @PathVariable String statue){
        RoleStatuePo userStatuePo = new RoleStatuePo(Integer.parseInt(id),statue);
        int flag =roleService.updateStatue(userStatuePo);
        if(flag==0){
            return new CommonResult(200,"修改失败");
        }
        return new CommonResult(200,"修改成功");
    }

    @PreAuthorize("hasAuthority('system:role:list')")
    @DeleteMapping("/role/delRole/{id}")
    public CommonResult delUser(@PathVariable Integer  id){
        int flag =roleService.deleteRole(id);
        if(flag==0){
            return new CommonResult(200,"删除失败");
        }
        return new CommonResult(200,"删除成功");
    }
}
