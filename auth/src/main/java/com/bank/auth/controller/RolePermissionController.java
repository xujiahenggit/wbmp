package com.bank.auth.controller;

import com.bank.auth.dto.PermissionDTO;
import com.bank.auth.service.PermissionService;
import com.bank.auth.util.MenuTree;
import com.bank.core.entity.BizException;
import com.bank.core.entity.Menu;
import com.bank.role.dto.RolePermissionDTO;
import com.bank.role.service.RolePermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author zhangfuqiang
 * @Date 2020/04/02
 */
@Api(tags = "角色权限操作接口")
@RestController
@RequestMapping("/rolePermission")
public class RolePermissionController {

    @Autowired
    private RolePermissionService  rolePermissionService;


    @ApiOperation(value = "插入角色权限")
    @ApiImplicitParam(name = "permissionDTO", value = "角色权限请求类", required = true, paramType = "body", dataType="RolePermissionDTO")
    @PostMapping
    public Integer insert(@RequestBody RolePermissionDTO permissionDTO) {
        int roleId = permissionDTO.getRoleId();
        String permissions = permissionDTO.getPermissionIds();

        if (roleId <=0) {
            throw new BizException("角色Id不能为空！");
        }

        if(permissions ==null || "".equals(permissions)){
            throw new BizException("角色权限不能为空！");
        }
        return  rolePermissionService.insert(permissions,roleId);
    }

    @Resource
    PermissionService permissionService;


    @ApiOperation(value = "根据角色id，获取角色权限信息")
    @ApiImplicitParam(name = "roleId", value = "角色", required = true, paramType = "path")
    @GetMapping("/{roleId}")
    public TreeSet<Menu> getById(@PathVariable Integer roleId) {
        if (null == roleId) {
            throw new BizException("角色Id不能为空！");
        }
        Set<PermissionDTO> rolePermissions = permissionService.listByRoleId(roleId);
        HashMap<Integer, Object> map = new HashMap<>();
        for (PermissionDTO rolePermission : rolePermissions) {
            map.put(rolePermission.getPermissionId(),rolePermission);
        }
        //获取所有权限
        Set<PermissionDTO> allPermissions = permissionService.listAllPermissions();

        TreeSet<Menu> menus = MenuTree.initMenuTree(allPermissions,map,4);


        return menus;
    }

}
