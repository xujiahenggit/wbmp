package com.bank.role.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wpp.arthur
 * @Date 2020/02/29
 */
@Data
@ApiModel(description = "系统角色权限表")
public class RolePermissionDTO implements Serializable {

    private static final long serialVersionUID = -1227352394972849822L;

    @ApiModelProperty("角色Id")
    private int roleId;

    @ApiModelProperty("权限Id")
    private String permissionIds;

}
