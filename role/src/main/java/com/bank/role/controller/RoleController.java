package com.bank.role.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.core.entity.BizException;
import com.bank.role.dos.RoleDO;
import com.bank.role.dto.RoleDTO;
import com.bank.role.service.RoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @author wpp.arthur
 * @Date 2020/02/29
 */
@Api(tags = "角色操作接口")
@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @ApiOperation(value = "根据角色id，获取角色信息")
    @ApiImplicitParam(name = "id", value = "角色唯一标识", required = true, paramType = "path")
    @GetMapping("/{id}")
    public RoleDTO getById(@PathVariable Integer id) {
        if (null == id) {
            throw new BizException("角色Id不能为空！");
        }
        return this.roleService.getById(id);
    }

    @ApiOperation(value = "获取所有角色信息")
    @GetMapping("/getAllRole")
    public List<RoleDO> getAllRole() {
        return this.roleService.getAllRole();
    }

    @ApiOperation(value = "插入角色信息")
    @ApiImplicitParam(name = "roleDTO", value = "角色信息", required = true, paramType = "body", dataType = "RoleDTO")
    @PostMapping
    public Integer insert(@RequestBody RoleDTO roleDTO) {
        dateValidityCheck(roleDTO);
        return this.roleService.insert(roleDTO);
    }

    @ApiOperation(value = "更新角色信息")
    @ApiImplicitParam(name = "roleDTO", value = "角色信息", required = true, paramType = "body", dataType = "RoleDTO")
    @PutMapping
    public Integer update(@RequestBody RoleDTO roleDTO) {
        dateValidityCheck(roleDTO);
        return this.roleService.updateById(roleDTO);
    }

    @ApiOperation(value = "删除角色信息")
    @ApiImplicitParam(name = "id", value = "角色唯一标识", required = true, paramType = "path")
    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable Integer id) {
        return this.roleService.deleteById(id);
    }

    /**
     * 角色权限分配接口
     */
    @ApiOperation(value = "角色分配权限信息")
    /**
     * 数据有效性检查
     *
     * @param roleDTO 角色信息传输对象
     */
    private void dateValidityCheck(RoleDTO roleDTO) {
        String name = roleDTO.getRoleName();
        if (null == name || "".equals(name)) {
            throw new BizException("角色名不能为空");
        }
        /**
        String code = roleDTO.getRoleCode();
        if (null == code || "".equals(code)) {
            throw new BizException("角色CODE不能为空");
        }
        **/
    }
}
