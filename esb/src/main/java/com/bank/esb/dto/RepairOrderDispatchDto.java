package com.bank.esb.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: cq
 * @Date: 2020/7/2
 */
@ApiModel(description = "工单分派Dto")
@Data
public class RepairOrderDispatchDto {
    @ApiModelProperty(value = "业务响应码 -1: 交易失败 ; 0: 正常 , ")
    private String repcode;
}
