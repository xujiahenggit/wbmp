package com.bank.esb.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * cq
 */
@Data
public class ResponServiceInformationDto {

    @ApiModelProperty(value = "业务响应码 -1: 交易失败 ; 0: 正常 , ")
    private String repcode;

    @ApiModelProperty(value = "服务信息")
    private List<ServiceInformationDto> serviceInformationDtoList;
}
