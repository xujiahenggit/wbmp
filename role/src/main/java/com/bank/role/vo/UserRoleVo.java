package com.bank.role.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/4/8 9:02
 */
@Data
@ApiModel(description = "角色信息模型")
public class UserRoleVo {
    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private String userId;

    /**
     * 角色ID 列表
     */
    @ApiModelProperty("角色ID 列表  直接传 数组")
    private List<Integer> roleIds;
}
