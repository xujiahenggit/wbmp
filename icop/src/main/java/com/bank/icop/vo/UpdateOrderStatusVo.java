package com.bank.icop.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "订单状态更新模型")
public class UpdateOrderStatusVo implements Serializable {

    private static final long serialVersionUID = 520186534864883510L;

    @ApiModelProperty(value = "交易用户ID  ")
    private String userId;

    @ApiModelProperty(value = "用户名  ")
    private String userName;

    @ApiModelProperty(value = "订单id  ")
    private String orderId;

    @ApiModelProperty(value = "操作对象：1-订单；2-凭证  ")
    private String operationObject;

    @ApiModelProperty(value = "操作类型：1-审核通过；2-驳回；3-确认（提交订单）；4-取消；5-收货；6-收货复核；7-号段审核；  ")
    private String operationType;

}
