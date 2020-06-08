package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ApiModel(description = "设备错误信息")
public class DeviceErrorLogDTO implements Serializable {

    private static final long serialVersionUID = 7267651138596166399L;

    @ApiModelProperty(value = "主键Id" )
    private Integer id;

    /**
     * 设备编号
     */
    @ApiModelProperty(value = "设备编号" )
    private String terminalNum;

    /**
     * 设备MAC地址
     */
    @ApiModelProperty(value = "设备MAC地址" )
    private String mac;

    /**
     * 错误信息
     */
    @ApiModelProperty(value = "错误信息" )
    private String messageLog;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间" )
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间" )
    private LocalDateTime updateTime;

}
