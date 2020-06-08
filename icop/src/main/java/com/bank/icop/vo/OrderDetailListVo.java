package com.bank.icop.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhaozhongyuan
 * @date 2020-06-05 17:14:33
 * @email
 */
@Data
@ApiModel(description = "订单明细列表")
public class OrderDetailListVo {

    @ApiModelProperty(value = "交易用户ID")
    private String userId;

    @ApiModelProperty(value = "订单id")
    private String orderId;

    @ApiModelProperty(value = "第几页")
    private Integer pageIndex = 1;

    @ApiModelProperty(value = "分页大小")
    private Integer pageSize = 10;

}
