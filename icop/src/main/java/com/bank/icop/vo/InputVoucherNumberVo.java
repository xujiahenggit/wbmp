package com.bank.icop.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "号段录入模块")
public class InputVoucherNumberVo implements Serializable {

    private static final long serialVersionUID = -1894663862423797627L;

    @ApiModelProperty(value = "交易用户ID  ")
    private String userId;

    @ApiModelProperty(value = "订单id  ")
    private String orderId;

    @ApiModelProperty(value = "订单明细id  ")
    private String orderDeatilId;

    @ApiModelProperty(value = "凭证代码  ")
    private String voucherNo;

    @ApiModelProperty(value = "号段信息列表  ")
    private List<VoucherNumberVo> data;

}
