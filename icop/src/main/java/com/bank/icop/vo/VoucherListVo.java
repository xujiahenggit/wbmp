package com.bank.icop.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Andy
 * @Date: 2020/6/4 15:46
 */
@ApiModel(description = "凭证列表")
@Data
public class VoucherListVo implements Serializable {

    @ApiModelProperty(value = "订单列表类型")
    private String listType;

    @ApiModelProperty(value = "订单类型")
    private String orderType;

    @ApiModelProperty(value = "订单编号")
    private String orderNo;

    @ApiModelProperty(value = "订单周期")
    private String orderCycle;

    @ApiModelProperty(value = "订单状态")
    private String orderStatus;

    @ApiModelProperty(value = "机构编号")
    private String orgId;

    @ApiModelProperty(value = "机构名称")
    private String orgName;

}
