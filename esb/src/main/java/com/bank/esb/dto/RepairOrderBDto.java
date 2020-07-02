package com.bank.esb.dto;

import io.swagger.annotations.ApiModelProperty;

public class RepairOrderBDto {
    @ApiModelProperty(value = "业务响应码 -1: 交易失败 ; 0: 正常 , ")
    private String repcode;
}
