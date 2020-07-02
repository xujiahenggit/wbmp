package com.bank.esb.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: cq
 * @Date: 2020/7/2
 */
@ApiModel(description = "机构列表")
@Data
public class ResponseInspectionSheetDto {
    @ApiModelProperty(value = "业务响应码 -1: 交易失败 ; 0: 正常 , ")
    private String repcode;
    @ApiModelProperty(value = "机构列表")
    private List<InspectionSheetDto> inspectionSheetDtoList;
}
