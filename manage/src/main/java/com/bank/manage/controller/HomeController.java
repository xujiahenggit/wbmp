package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.auth.service.PermissionService;
import com.bank.core.utils.NetUtil;
import com.bank.manage.service.DevicePlayService;
import com.bank.manage.service.HomeService;
import com.bank.role.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 获取首页信息
 *
 * @author
 * @date 2020-04-13
 */
@Api(tags = "首页信息-菜单权限-按钮权限")
@RestController
@RequestMapping("/home")
public class HomeController extends BaseController {

    @Resource
    private HomeService homeService;

    private final int day = 5;

    @GetMapping("/queryNumber")
    @ApiOperation(value = "获取首页面板数据")
    public Object queryNumber() {
        Map<String, Object> data = this.homeService.getHomePanleData(this.day);
        return data;
    }

    /*@Value("${MATERIAL.HTTP_PATH}")
    private String basePath;*/

    @Resource
    NetUtil netUtil;

    @GetMapping("/queryNewMaterial/{orgId}")
    @ApiOperation(value = "获取首页最新编辑")
    public Object queryNewMaterial(@PathVariable String orgId) {
        List<Map<String, Object>> data = this.homeService.getNewMaterial(this.day, orgId).stream().map(map -> {
            map.put("MATERIAL_PATH", this.netUtil.getUrlSuffix("") + map.get("MATERIAL_PATH"));
            return map;
        }).collect(Collectors.toList());
        return data;
    }

    @Resource
    private DevicePlayService devicePlayService;

    @GetMapping("/queryPlayLatestTwo")
    @ApiOperation(value = "新增设备信息")
    public List<Map<String, Object>> queryPlayLatestTwo() {
        return this.devicePlayService.queryPlayLatestTwo();
    }

    @Resource
    RoleService roleService;

    @Resource
    PermissionService permissionService;

    @ApiOperation(value = "根据用户ID获取菜单树,PC")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path")
    @GetMapping("/menus/{id}")
    public Object getPermissionMenusTreeById(@PathVariable String id) {
        return this.permissionService.getUserMenu(id);
    }

    @ApiOperation(value = "根据用户ID获取菜单列表：app")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path")
    @GetMapping("/appmenus/{id}")
    public Object getPermissionMenus(@PathVariable String id) {
        return this.permissionService.getAppMenus(request.getContextPath(),id);
    }

    @Resource
    HttpServletRequest request;


    @ApiOperation(value = "根据菜单编号获取手机端待办列表：app")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, paramType = "path")
    @GetMapping("/appdealmenus/{userId}")
    public Object getAppMenuList(@PathVariable String userId){
        return permissionService.getAppDealtListMenu(request.getContextPath(),userId);
    }

    @ApiOperation(value = "根据用户ID获取按钮权限")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, paramType = "path")
    @GetMapping("/buttonPermission/{userId}")
    public String[] getButtonPermission(@PathVariable String userId) {
        return this.permissionService.getButtonPermission(userId);
    }
}
