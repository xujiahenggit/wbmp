package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: zhangfuqiang
 * @Date: 2020/7/15 22:50
 * 自助设备列表
 */
@Data
@ApiModel
public class SsarunDeviceVo {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "设备名称 自动取款机 1，自动存款机 2，自动存取款机 3，自动查询机 4")
    private String deviceName;

    @ApiModelProperty(value = "终端编号")
    private String termNo;

    @ApiModelProperty(value = "设备运行状态")
    private String deviceRunStatus;

    @ApiModelProperty(value = "设备型号")
    private String strdevmodel;

    @ApiModelProperty(value = "设备厂商")
    private String strdevmanu;

    @ApiModelProperty(value = "机构号")
    private String organization;
}
