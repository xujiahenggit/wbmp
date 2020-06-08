package com.bank.user.dto;

import com.bank.user.dos.UserDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/7 17:06
 */
@Data
@ApiModel("用户列表")
public class UserInfoDto extends UserDO {

    @ApiModelProperty(value = "核心机构号")
    private String orgCode;

    @ApiModelProperty(value = "角色列表")
    private List<String> Roles;
}
