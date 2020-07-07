package com.bank.esb.dto;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: cq
 * @Date: 2020/7/2
 */
@ApiModel(description = "相应报文")
@Data
public class ResponseDto {

    @ApiModelProperty(value = "业务响应码 -1: 交易失败 ; 0: 正常 , ")
    private String status;

    @ApiModelProperty(value = "分页大小")
    private int pageSize;

    @ApiModelProperty(value = "第几页")
    private int pageIndex;

    @ApiModelProperty(value = "总记录数")
    private int total;

    @ApiModelProperty(value = "工单Dto集合")
    @JSONField(name = "List")
    private List<OrderDto> List;
}
