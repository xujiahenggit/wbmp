package com.bank.icop.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "查询订单明细模型")
public class OrderQueryDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty("交易用户ID")
    private String userId;
    @ApiModelProperty("订单id")
    private String orderId;
    @ApiModelProperty("第几页 默认第一页")
    private Integer pageIndex = 1;
    @ApiModelProperty("分页大小 默认10条记录")
    private Integer pageSize = 10;


}
