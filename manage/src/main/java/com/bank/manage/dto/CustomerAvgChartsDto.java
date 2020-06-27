package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/6/16 11:01
 */
@Data
@ApiModel(value = "客群增长 趋势图")
public class CustomerAvgChartsDto implements Serializable {
    /**
     * 数值
     */
    @ApiModelProperty(value = "数值")
    private List<Integer> data;

    @ApiModelProperty(value = "坐标值")
    private List<String> xAxis;

    @ApiModelProperty(value = "横坐标名称")
    private String xAxisName;

    @ApiModelProperty(value = "纵坐标名称")
    private String yAxisName;
}
