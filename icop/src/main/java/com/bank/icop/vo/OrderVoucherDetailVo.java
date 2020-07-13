package com.bank.icop.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "订单凭证详情接口 请求用")
public class OrderVoucherDetailVo implements Serializable {

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "机构ID")
    private String orgId;

    @ApiModelProperty(value = "订单详细信息")
    private String orderDeatilId;
}
