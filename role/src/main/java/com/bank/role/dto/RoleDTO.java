package com.bank.role.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wpp.arthur
 * @Date 2020/02/29
 */
@Data
@ApiModel(description = "角色信息模型")
public class RoleDTO implements Serializable {

    private static final long serialVersionUID = -1227352394972849822L;

    @ApiModelProperty("角色Id")
    private Integer roleId;

    @ApiModelProperty(value = "角色名", required = true)
    private String roleName;

    @ApiModelProperty(value = "角色标识码")
    private String roleCode;

}
