package com.bank.esb.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: cq
 * @Date: 2020/7/2
 */
@ApiModel(description = "相应报文")
@Data
public class ResponseDto {
    @ApiModelProperty(value = "业务响应码 -1: 交易失败 ; 0: 正常 , ")
    private String repcode;

    @ApiModelProperty(value = "分页大小")
    private int pageSize;

    @ApiModelProperty(value = "第几页")
    private int pageIndex;

    @ApiModelProperty(value = "总记录数")
    private int total;

    @ApiModelProperty(value = "工单Dto集合")
    private List<OrderDto> orderDtoList;
}
