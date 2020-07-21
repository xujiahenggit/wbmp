package com.bank.esb.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * cq
 */
@Data
public class ResonseTransferInformationDto {
    @ApiModelProperty(value = "业务响应码 -1: 交易失败 ; 0: 正常 , ")
    private String repcode;

    @ApiModelProperty(value = "当前状态")
    private String orderStatus;

    @ApiModelProperty(value = "流转信息")
    private List<TransferInformationDto> list;
}
