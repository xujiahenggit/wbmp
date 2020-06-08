package com.bank.icop.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Andy
 * @Date: 2020/6/4 16:22
 */
@ApiModel(description = "订单查询用")
@Data
public class OrderQueryVo implements Serializable {

    @ApiModelProperty("交易用户ID")
    private String userId;

    @ApiModelProperty("订单编号")
    private String orderId;

    @ApiModelProperty("订单列表类型")
    private String orderType;
}
