package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: cq
 * @Date: 2020/5/8 22:50
 * 已完成工单详情信息
 */
@Data
@ApiModel
public class CompletedWordOrderVo {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "厂商")
    private String deviceVendor;
    @ApiModelProperty(value = "设备型号 ")
    private String deviceModel;
    @ApiModelProperty(value = "存款箱状态")
    private String depositStatus;
    @ApiModelProperty(value = "取款箱状态")
    private String withDrawalsStatus;
    @ApiModelProperty(value = "读卡器状态")
    private String readerStatus;
    @ApiModelProperty(value = "密码箱状态")
    private String passWordStatus;
    @ApiModelProperty(value = "流水打印机状态")
    private String flowPrinterStatus;
    @ApiModelProperty(value = "凭条打印机状态")
    private String slipPrinterStatus;
    @ApiModelProperty(value = "对账打印机状态")
    private String reconciliationPrinterStatus;
    @ApiModelProperty(value = "存折打印机状态")
    private String passbookPrinterStatus;
    @ApiModelProperty(value = "'服务状态")
    private String serviceStatus;
    @ApiModelProperty(value = "机构id")
    private String orgId;
    @ApiModelProperty(value = "地址")
    private String address;
    @ApiModelProperty(value = "首次安装日期")
    private Date installationTime;
    @ApiModelProperty(value = "终端编号")
    private String terminalCode;
    @ApiModelProperty(value = "设备类型：自动取款机 1，自动存款机 2，自动存取款机 3，自动查询机 4")
    private String deviceType;
    @ApiModelProperty(value = "服务主管")
    private String director;


}
