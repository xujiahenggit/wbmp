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
    @ApiModelProperty(value = "打印机状态")
    private String status;
    @ApiModelProperty(value = "名称")
    private String name;

}
