package com.bank.icop.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhaozhongyuan
 * @date 2020-06-05 16:24:45
 * @email
 */
@Data
@ApiModel(description = "订单信息")
public class OrderInfoVo {

    @ApiModelProperty(value = "交易用户ID")
    private String userId;

    @ApiModelProperty(value = "订单类型")
    private String orderType;

    @ApiModelProperty(value = "机构id")
    private String orgId;

    @ApiModelProperty(value = "机构名称")
    private String orgName;

    @ApiModelProperty(value = "收货人")
    private String consignee;

    @ApiModelProperty(value = "收货地址")
    private String address;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "发票类型")
    private String invoiceType;

    @ApiModelProperty(value = "发票抬头")
    private String invoiceTitle;

    @ApiModelProperty(value = "税号")
    private String dutyPara;

    @ApiModelProperty(value = "备注")
    private String remark;

}
