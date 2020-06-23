package com.bank.icop.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhaozhongyuan
 * @date 2020-06-05 16:45:04
 * @email
 */
@Data
@ApiModel(description = "订单明细信息")
public class OrderDetailVo {

    @ApiModelProperty(value = "交易用户ID")
    private String userId;

    @ApiModelProperty("凭证ID")
    private String voucherId;

    @ApiModelProperty(value = "订单id")
    private String orderId;

    @ApiModelProperty(value = "凭证名称")
    private String voucherName;

    @ApiModelProperty(value = "凭证代码")
    private String voucherNo;

    @ApiModelProperty(value = "数量")
    private String num;

    @ApiModelProperty(value = "卡产品名称")
    private String cardName;

    @ApiModelProperty(value = "卡产品编号")
    private String cardNo;

    @ApiModelProperty(value = "备注")
    private String remark;

}
