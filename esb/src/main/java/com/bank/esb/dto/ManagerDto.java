package com.bank.esb.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: zzy
 * @Date: 2020/7/15
 */
@ApiModel(description = "服务主管")
@Data
public class ManagerDto {
    @ApiModelProperty(value = "服务主管id")
    private String serverId;

    @ApiModelProperty(value = "服务主管名字")
    private String serverName;
}

