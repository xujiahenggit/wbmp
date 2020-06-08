package com.bank.auth.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wpp.arthur
 * @Date 2020/02/29
 */
@Data
@ApiModel(description = "权限信息")
public class PermissionDTO implements Serializable {

    private static final long serialVersionUID = 3772378782995724975L;

    @ApiModelProperty("权限Id")
    private Integer permissionId;

    @ApiModelProperty("权限名称")
    private String permissionName;

    @ApiModelProperty("权限标识码")
    private String permissionCode;

    @ApiModelProperty("前端路由地址")
    private String routerPath;

    @ApiModelProperty("父ID")
    private Integer parentId;

    @ApiModelProperty("父模块描述")
    private String parentDesc;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("component")
    private String component;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("权限类型 1：一级菜单 2：二级菜单 3：接口 4：超连接")
    private String permissionType;

    @ApiModelProperty("权限类型描述")
    private String permissionTypeDesc;
}
