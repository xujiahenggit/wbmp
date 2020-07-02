package com.bank.esb.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: cq
 * @Date: 2020/7/2
 */
@ApiModel(description = "工单分派Vo")
@Data
public class RepairOrderDispatchVo {

    @ApiModelProperty(value = "工单编号")
    private String orderId;

    @ApiModelProperty(value = "工程师ID")
    private String engineerId;

    @ApiModelProperty(value = "工程师名称")
    private String engineerName;
}
