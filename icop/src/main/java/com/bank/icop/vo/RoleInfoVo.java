package com.bank.icop.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Andy
 * @Date: 2020/6/4 15:38
 */
@ApiModel(description = "角色信息")
@Data
public class RoleInfoVo implements Serializable {

    @ApiModelProperty(value = "角色编号")
    private String roleId;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "机构编号")
    private String orgId;

    @ApiModelProperty(value = "机构名称")
    private String orgName;
}
