package com.bank.auth.dto;

import java.io.Serializable;
import java.util.Set;

import com.bank.role.dos.RoleDO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wpp.arthur
 * @Date 2020/02/29
 */
@Data
@ApiModel(description = "认证授权信息")
public class AuthDTO implements Serializable {

    private static final long serialVersionUID = 1041407982370766342L;

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("用户拥有的角色")
    private Set<RoleDO> roles;

    @ApiModelProperty("用户拥有的权限")
    private Set<PermissionDTO> permissions;

}
