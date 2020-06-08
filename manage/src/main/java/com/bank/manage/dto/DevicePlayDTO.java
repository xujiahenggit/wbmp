package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ApiModel(description = "设备播放信息")
public class DevicePlayDTO implements Serializable {

    private static final long serialVersionUID = 232060722314387520L;
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
     * 播放信息
     */
    @ApiModelProperty(value = "播放信息" )
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

    @ApiModelProperty(value = "节目主键" )
    private Integer programId;

    @ApiModelProperty(value = "节目名称" )
    private String programName;

}
