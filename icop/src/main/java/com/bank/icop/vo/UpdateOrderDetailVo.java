package com.bank.icop.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "订单明细更新模型")
public class UpdateOrderDetailVo implements Serializable {

    private static final long serialVersionUID = -7941489013419487042L;

    @ApiModelProperty("交易用户ID")
    private String userId;

    @ApiModelProperty("订单编号")
    private String orderId;

    @ApiModelProperty("订单明细id")
    private String orderDeatild;

    @ApiModelProperty("凭证名称")
    private String voucherName;

    @ApiModelProperty("凭证代码")
    private String voucherNo;

    @ApiModelProperty("数量")
    private Integer num;

    @ApiModelProperty("卡产品名称")
    private String cardName;

    @ApiModelProperty("卡产品编号")
    private String cardNo;

    @ApiModelProperty("备注")
    private String remark;

}
