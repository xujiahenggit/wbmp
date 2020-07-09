package com.bank.icop.dos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
@ApiModel(description = "号段查看")
public class VoucherNumberDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("交易用户ID")
    private String userId;
    @ApiModelProperty("订单id")
    private String orderId;
    @ApiModelProperty("凭证代码")
    private String voucherNo;
    @ApiModelProperty("订单明细id")
    private String orderDeatild;

}
