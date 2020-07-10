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
public class DeviceErrorLogVo {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "流水打印机状态")
    private String flowPrinterStatus;
    @ApiModelProperty(value = "凭条打印机状态")
    private String stripPrinterStatus;
    @ApiModelProperty(value = "对账打印机状态")
    private String reconciliationPrinterStatus;
    @ApiModelProperty(value = "存折打印机状态")
    private String passbookPrinter;

}
