package com.bank.role.controller;

import com.bank.role.dos.UserRoleDO;
import com.bank.role.service.UserRoleService;
import com.bank.role.vo.UserRoleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/4/8 9:00
 */
@Api(tags = "用户角色设置")
@RestController
@RequestMapping("/userrole")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    @ApiOperation(value = "根据用户ID 设置角色")
    @PostMapping("/setrole")
    public Boolean setUserRole(@RequestBody UserRoleVo userRoleVo){
        return userRoleService.setUserRole(userRoleVo);
    }

    @ApiOperation(value = "根据用户ID 获取角色ID 列表")
    @PostMapping("/roles")
    public List<UserRoleDO> getUserRoleId(@RequestParam String userId){
        List<UserRoleDO> roleList=userRoleService.getUserRoleList(userId);
        return roleList;
    }
}
