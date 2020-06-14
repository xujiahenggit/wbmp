package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(description = "运营看板：排名信息")
@Builder
@AllArgsConstructor
public class RankInfo {

    @ApiModelProperty(value = "柜员号")
    private String tellerId;


    @ApiModelProperty(value = "交易量排名")
    private Integer tradeNumRank;


    @ApiModelProperty(value = "平均交易量：笔")
    private BigDecimal tradeNumAverage;


    @ApiModelProperty(value = "在线时长排名")
    private Integer onlineTimeRank;


    @ApiModelProperty(value = "平均在线时长：分钟")
    private BigDecimal onlineTimeAverage;


    @ApiModelProperty(value = "在线人数")
    private Integer onlineSum;

}
