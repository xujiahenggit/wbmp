package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "设备交易趋势信息")
public class DeviceTradeTrendVo {

    @ApiModelProperty(value = "日期")
    private String date;

    @ApiModelProperty(value = "交易笔数")
    private String tradeCount;

}
