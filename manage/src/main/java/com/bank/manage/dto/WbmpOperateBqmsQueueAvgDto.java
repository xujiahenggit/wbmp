package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: Andy
 * @Date: 2020/6/12 17:29
 */
@ApiModel("客户满意度")
@Data
public class WbmpOperateBqmsQueueAvgDto implements Serializable {
    /**
     * 平均等待时长-秒
     */
    @ApiModelProperty(value = "平均等待时长-秒")
    private String indexCnt;

    /**
     * 平均弃号率
     */
    @ApiModelProperty(value = "平均弃号率")
    private String avgAbandonedLv;

    /**
     * 弃号率
     */
    @ApiModelProperty(value = "弃号率")
    private String abandonedLv;

    /**
     * 客户满意度
     */
    @ApiModelProperty(value = "客户满意度")
    private float cunstomerAvg;
}
