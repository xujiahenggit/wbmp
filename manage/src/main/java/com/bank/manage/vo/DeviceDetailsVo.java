package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: cq
 * @Date: 2020/5/8 22:50
 * 自助设备详情
 */
@Data
@ApiModel
public class DeviceDetailsVo {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "厂商id")
    private String deviceVendor;
    @ApiModelProperty(value = "厂商名称")
    private String deviceVendorName;
    @ApiModelProperty(value = "设备型号 ")
    private String deviceModel;
    @ApiModelProperty(value = "设备类型：自动取款机 1，自动存款机 2，自动存取款机 3，自动查询机 4")
    private String deviceType;
    @ApiModelProperty(value = "机构id")
    private String orgId;
    @ApiModelProperty(value = "机构名称")
    private String orgName;
    @ApiModelProperty(value = "地址")
    private String address;
    @ApiModelProperty(value = "首次安装日期")
    private Date installationTime;
    @ApiModelProperty(value = "终端编号")
    private String terminalCode;
    @ApiModelProperty(value = "设备状态")
    private String deviceStatus;
    @ApiModelProperty(value = "终端详情")
    private TerminalDetailsVo terminalDetailsVo;
    @ApiModelProperty(value = "服务主管信息")
    private List<DeviceVendorVo> deviceVendorVos;
}
