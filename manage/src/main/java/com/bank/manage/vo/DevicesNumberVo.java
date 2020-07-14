package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: cq
 * @Date: 2020/7/10 22:50
 * (t_repair)
 */
@Data
@ApiModel
public class DevicesNumberVo {
    @ApiModelProperty(value = "设备总数")
    private String totalNumber;
    @ApiModelProperty(value = "在线数")
    private String onlineNumber;
    @ApiModelProperty(value = "暂停数")
    private String suspendedNumber;
    @ApiModelProperty(value = "离线数")
    private String offlineNumber;
    @ApiModelProperty(value = "故障数")
    private String faultNumber;
    @ApiModelProperty(value = "可用率")
    private double availability;
}
