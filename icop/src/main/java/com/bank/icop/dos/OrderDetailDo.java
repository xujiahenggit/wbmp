package com.bank.icop.dos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "查询订单明细返回信息")
public class OrderDetailDo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单明细id")
    private String orderDeatiId;
    @ApiModelProperty("凭证明细状态")
    private String detailStatus;
    @ApiModelProperty("凭证名称")
    private String voucherName;
    @ApiModelProperty("凭证代码")
    private String voucherNo;
    @ApiModelProperty("卡产品名称")
    private String cardName;
    @ApiModelProperty("卡产品编号")
    private String cardNo;
    @ApiModelProperty("单价")
    private String price;
    @ApiModelProperty("总价")
    private String voucherAmt;
    @ApiModelProperty("数量")
    private String num;
    @ApiModelProperty("规格")
    private String spec;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("是否可以录入号段&号段复核 1-可录入号段；2-可复核；3-不可录入号段，不可复核")
    private String enterNum;


}
