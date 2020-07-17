package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: cq
 * @Date: 2020/5/8 22:50
 * 终端详情
 */
@Data
@ApiModel
public class TerminalDetailsVo {
    @ApiModelProperty(value = "存款箱状态")
    private String depositStatus;
    @ApiModelProperty(value = "取款箱状态")
    private String withDrawalsStatus;
    @ApiModelProperty(value = "读卡器状态")
    private String readerStatus;
    @ApiModelProperty(value = "密码箱状态")
    private String passWordStatus;
    @ApiModelProperty(value = "凭条打印机状态")
    private String slipPrinterStatus;
    @ApiModelProperty(value = "对账打印机状态")
    private String reconciliationPrinterStatus;
    @ApiModelProperty(value = "流水打印机状态")
    private String waterPrinterStatus;
    @ApiModelProperty(value = "存折打印机状态")
    private String passbookPrinterStatus;
    @ApiModelProperty(value = "'服务状态")
    private String serviceStatus;
}
