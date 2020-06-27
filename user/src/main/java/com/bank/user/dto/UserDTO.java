package com.bank.user.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wpp.arthur
 * @Date 2020/02/29
 */
@Data
@ApiModel(description = "用户信息模型")
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1041407982370766342L;

    @ApiModelProperty("用户id")
    private Integer id;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty(value = "盐值", hidden = true)
    private String salt;

    @ApiModelProperty("创建时间戳")
    private Long createTime;

    @ApiModelProperty("更新时间戳")
    private Long updateTime;
}
