package com.zhou.demo.controller;

import com.zhou.demo.controller.request.PermissionRequest;
import com.zhou.demo.controller.request.SearchParams;
import com.zhou.demo.domain.CommonResult;
import com.zhou.demo.persist.po.Permission;
import com.zhou.demo.persist.po.StatuePo;
import com.zhou.demo.service.PermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName PermissionController
 * @Author zz
 * @Date 2023/3/17 16:02
 * @Version 1.0
 * @Description 权限管理
 */

@RestController
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PreAuthorize("hasAuthority('system:permission:list')")
    @GetMapping("/permission/queryPermissionList")
    public CommonResult<Map> queryPermissionList(@RequestParam("keyword") String keyword,
                                           @RequestParam("pagenum") Integer pagenum,
                                           @RequestParam("pagesize") Integer pagesize,
                                           @RequestParam("total") Integer total){
        SearchParams searchParams = new SearchParams();
        searchParams.setKeyword(keyword);
        searchParams.setPagenum(pagenum);
        searchParams.setPagesize(pagesize);
        Map map= permissionService.queryAllPermission(searchParams);
        return new CommonResult<Map>(200,"查询成功",map);
    }
    @PreAuthorize("hasAuthority('system:permission:list')")
    @PostMapping("/permission/EditPermission")
    public CommonResult EditPermission(@RequestBody PermissionRequest PermissionRequest){
        Permission permission = new Permission();
        BeanUtils.copyProperties(PermissionRequest,permission);
        int flag = permissionService.editPermission(permission);
        if(flag==0){
            return new CommonResult(200,"更新失败");
        }
        return new CommonResult(200,"更新成功");
    }

    @PostMapping("/permission/addPermission")
    public CommonResult addRole(@RequestBody PermissionRequest PermissionRequest){
        Permission Permission = new Permission();
        BeanUtils.copyProperties(PermissionRequest,Permission);
        int flag = permissionService.addPermission(Permission);
        if(flag==0){
            return new CommonResult(200,"创建失败");
        }
        return new CommonResult(200,"创建成功");
    }


    @PreAuthorize("hasAuthority('system:permission:list')")
    @PutMapping("/permission/{id}/statue/{status}")
    public CommonResult updateStatue(@PathVariable String id,
                                     @PathVariable String status){
        StatuePo permissionStatuePo = new StatuePo();
        permissionStatuePo.setId(Integer.parseInt(id));
        permissionStatuePo.setStatus(status);
        int flag = permissionService.updateStatue(permissionStatuePo);
        if(flag==0){
            return new CommonResult(200,"修改失败");
        }
        return new CommonResult(200,"修改成功");
    }

    @PreAuthorize("hasAuthority('system:permission:list')")
    @DeleteMapping("/permission/delPermission/{id}")
    public CommonResult delUser(@PathVariable Integer  id){
        int flag =permissionService.deletePermission(id);
        if(flag==0){
            return new CommonResult(200,"删除失败");
        }
        return new CommonResult(200,"删除成功");
    }
}
