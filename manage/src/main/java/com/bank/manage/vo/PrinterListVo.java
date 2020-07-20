package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * cq
 * 打印机状态集合
 */
@Data
@ApiModel
public class PrinterListVo {
    @ApiModelProperty(value = "凭条打印机状态")
    private String slipPrinterStatus;
    @ApiModelProperty(value = "凭条打印机名称")
    private String slipPrinterName;
    @ApiModelProperty(value = "对账打印机状态")
    private String reconciliationPrinterStatus;
    @ApiModelProperty(value = "对账打印机名称")
    private String reconciliationPrinterName;
    @ApiModelProperty(value = "流水打印机状态")
    private String waterPrinterStatus;
    @ApiModelProperty(value = "流水打印机名称")
    private String waterPrinterName;
    @ApiModelProperty(value = "存折打印机状态")
    private String passbookPrinterStatus;
    @ApiModelProperty(value = "存折打印机名称")
    private String passbookPrinterName;

}
