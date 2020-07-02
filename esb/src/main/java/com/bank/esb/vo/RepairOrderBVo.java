package com.bank.esb.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: cq
 * @Date: 2020/7/2
 */
@ApiModel(description = "投诉工单Vo")
@Data
public class RepairOrderBVo {
    @ApiModelProperty(value = "工单编号")
    private String orderNo;

    @ApiModelProperty(value = "工程师ID")
    private String engineerId;

    @ApiModelProperty(value = "回复意见")
    private String orderDescribe;
}
