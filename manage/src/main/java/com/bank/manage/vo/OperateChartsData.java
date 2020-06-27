package com.bank.manage.vo;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@ApiModel(description = "图表数据")
@Data
@Builder
public class OperateChartsData {

    @ApiModelProperty(value = "数据")
    private List<BigDecimal> data;

    @ApiModelProperty(value = "x轴数据")
    private List<String> xAxis;

    @ApiModelProperty(value = "x轴名称")
    private String xAxisName;

    @ApiModelProperty(value = "y轴名称")
    private String yAxisName;
}
