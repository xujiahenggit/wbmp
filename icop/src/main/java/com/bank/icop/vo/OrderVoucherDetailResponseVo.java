package com.bank.icop.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(value = "订单凭证详情接口")
public class OrderVoucherDetailResponseVo implements Serializable {
    @ApiModelProperty(value = "订单编号")
    private String orderNo;

    @ApiModelProperty(value = "订单明细编号")
    private String orderDeatiId;

    @ApiModelProperty(value = "订单明细状态 0 待审批1 待收货2 已收货，待记账3 已取消4 已收货记账")
    private String detailStatus;

    @ApiModelProperty(value = "凭证名称")
    private String voucherName;

    @ApiModelProperty(value = "凭证代码")
    private String voucherNo;

    @ApiModelProperty(value = "卡产品名称")
    private String cardName;

    @ApiModelProperty(value = "卡产品编号")
    private String cardNo;

    @ApiModelProperty(value = "单价")
    private String price;

    @ApiModelProperty(value = "总价")
    private String voucherAmt;

    @ApiModelProperty(value = "数量")
    private String totalNum;

    @ApiModelProperty(value = "规格数量")
    private String num;

    @ApiModelProperty(value = "规格")
    private String spec;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否可以录入号段&号段复核 0：无需分配， 1：待分配， 2:分配中，3：已分配")
    private String enterNum;

    @ApiModelProperty(value = "号段列表")
    List<VoucherNumberVo> list;
}


