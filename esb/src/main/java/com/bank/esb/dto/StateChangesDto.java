package com.bank.esb.dto;

import io.swagger.annotations.ApiModelProperty;
/**
 * @Author: cq
 * @Date: 2020/7/2
 */
public class StateChangesDto {
    @ApiModelProperty(value = "业务响应码 -1: 交易失败 ; 0: 正常 , ")
    private String repcode;
}
