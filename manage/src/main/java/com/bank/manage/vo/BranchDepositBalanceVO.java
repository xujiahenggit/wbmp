package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@ApiModel(description = "网点存款时点余额VO")
@Data
@Builder
public class BranchDepositBalanceVO {

    @ApiModelProperty(value = "存款余额")
    private String deposit;

    @ApiModelProperty(value = "存款类型")
    private String depositTypeCode;

    @ApiModelProperty(value = "存款类型名称")
    private String depositTypeName;

    @ApiModelProperty(value = "图表数据")
    private OperateChartsData charts;
}
