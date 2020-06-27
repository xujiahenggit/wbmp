package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/6/16 10:55
 */
@Data
@ApiModel(value = "客群增长")
public class CustomerAvgDto implements Serializable {

    @ApiModelProperty(value = "客群类型编号")
    private String customerTypeCode;

    @ApiModelProperty(value = "客群类型名称")
    private String customerTypeName;

    @ApiModelProperty(value = "周期类型")
    private String cycleCode;

    @ApiModelProperty(value = "周期名称")
    private String cycleName;

    @ApiModelProperty(value = "趋势图信息")
    private CustomerAvgChartsDto charts;
}
