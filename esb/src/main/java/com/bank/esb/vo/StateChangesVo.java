package com.bank.esb.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: cq
 * @Date: 2020/7/2
 */
@ApiModel(description = "状态变更Vo")
@Data
public class StateChangesVo {
    @ApiModelProperty(value = "工单编号")
    private String orderNo;

    @ApiModelProperty(value = "工程师ID")
    private String engineerId;
}
