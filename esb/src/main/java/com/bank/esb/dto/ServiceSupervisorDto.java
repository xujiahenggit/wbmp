package com.bank.esb.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: cq
 * @Date: 2020/7/2
 */
@ApiModel(description = "服务主管Dto")
@Data
public class ServiceSupervisorDto {
    @ApiModelProperty(value = "服务主管ID")
    private String serverId;

    @ApiModelProperty(value = "服务主管姓名")
    private String serverName;
}
