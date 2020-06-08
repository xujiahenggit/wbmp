package com.bank.auth.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.auth.dos.PermissionDO;
import com.bank.auth.dto.PermissionDTO;
import com.bank.auth.service.PermissionService;
import com.bank.core.entity.BizException;
import com.bank.core.entity.PageQueryModel;
import com.baomidou.mybatisplus.core.metadata.IPage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * ClassName: PermissionController
 *
 * @author Yanwen D. Ding
 * <p>
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 * <p>
 * All Rights Reserved
 * <p>
 * http://www.yusys.com.cn
 * <p>
 * Create Time: 2020/03/31 17:53:48
 */
@Api(tags = "系统管理-权限管理接口")
@RestController
@RequestMapping("/permission")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @ApiOperation(value = "插入权限信息")
    @ApiImplicitParam(name = "permissionDTO", value = "权限信息", required = true, paramType = "body", dataType = "PermissionDTO")
    //@RequiresPermissions("permission:info:insert")
    @PostMapping
    public Integer insert(@RequestBody PermissionDTO permissionDTO) {
        return this.permissionService.insert(permissionDTO);
    }

    @ApiOperation(value = "更新权限信息")
    @ApiImplicitParam(name = "permissionDTO", value = "权限信息", required = true, paramType = "body", dataType = "PermissionDTO")
    //@RequiresPermissions("permission:info:update")
    @PutMapping
    public Integer update(@RequestBody PermissionDTO permissionDTO) {
        return this.permissionService.updateById(permissionDTO);
    }

    @ApiOperation(value = "删除权限信息")
    @ApiImplicitParam(name = "id", value = "权限唯一标识", required = true, paramType = "path")
    //@RequiresPermissions("permission:info:delete")
    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable Integer id) {
        if (id.intValue() == 1) {
            throw new BizException("权限根不允许删除");
        }
        return this.permissionService.deleteById(id);
    }

    @ApiOperation(value = "查询权限信息-分页", notes = "分页条件查询参数queryParam包含：字段{权限名称【permissionName】|权限标识码【permissionCode】|权限类型【permissionType】}")
    @ApiImplicitParam(name = "pageQueryModel", value = "权限信息分页查询", required = true, paramType = "body", dataType = "PageQueryModel")
    //@RequiresPermissions("permission:info:selectPage")
    @PostMapping("/selectPage")
    public IPage<PermissionDO> selectPage(@RequestBody PageQueryModel pageQueryModel) {
        return this.permissionService.selectPage(pageQueryModel);
    }

    @ApiOperation(value = "查询权限信息ByID")
    @ApiImplicitParam(name = "id", value = "权限唯一标识", required = true, paramType = "path")
    //@RequiresPermissions("permission:info:selectById")
    @GetMapping("/{id}")
    public PermissionDO selectById(@PathVariable Integer id) {
        return this.permissionService.selectById(id);
    }

    @ApiOperation(value = "查询权限根与一级菜单（功能模块）")
    //@RequiresPermissions("permission:info:selectParentList")
    @GetMapping("/selectParentList")
    public List<Map<String, Object>> selectParentList() {
        return this.permissionService.selectParentList();
    }

    @ApiOperation(value = "查询二级菜单")
    //@RequiresPermissions("permission:info:selectParentList")
    @GetMapping("/selectParentList2")
    public List<Map<String, Object>> selectParentList2() {
        return this.permissionService.selectParentList2();
    }

}
