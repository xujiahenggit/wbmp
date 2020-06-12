package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "设备交易量信息")
public class TransCntInfo {

    @ApiModelProperty(value = "交易名称")
    private String transName;

    @ApiModelProperty(value = "设备交易量")
    private Integer sum;

    @ApiModelProperty(value = "百分比")
    private BigDecimal percent;
}
