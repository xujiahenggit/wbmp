package com.bank.esb.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: cq
 * @Date: 2020/7/2
 */
@ApiModel(description = "工程师")
@Data
public class ResponseEngineerDto {
    @ApiModelProperty(value = "业务响应码 -1: 交易失败 ; 0: 正常 , ")
    private String repcode;

    @ApiModelProperty(value = "总记录条数")
    private int total;

    @ApiModelProperty(value = "工程师dto")
    private List<EngineerDto> List;
}
