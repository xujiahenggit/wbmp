package com.bank.esb.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: cq
 * @Date: 2020/7/2
 */
@ApiModel(description = "流转信息Vo")
@Data
public class TransferInformationVo {
    @ApiModelProperty(value = "工单id")
    private String orderId;
}