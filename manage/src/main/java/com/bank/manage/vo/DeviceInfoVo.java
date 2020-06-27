package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "设备列表信息")
public class DeviceInfoVo {

    @ApiModelProperty(value = "设备名称")
    private String deviceName;

    @ApiModelProperty(value = "终端编号")
    private String termNo;

    @ApiModelProperty(value = "设备状态 10-在线，11-离线，-1-空闲，0-交易中，1-故障，2-暂停服务，3-维护中，4-通信中断，5-耗材尽，6-已关机，7-重启中")
    private String deviceRunStatus;

    @ApiModelProperty(value = "设备ID")
    private String deviceId;

}
