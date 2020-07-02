package com.bank.esb.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Author: cq
 * @Date: 2020/7/2
 */
public class TransferInformationDto {
    @ApiModelProperty(value = "操作人ID")
    private String operatorUserId;

    @ApiModelProperty(value = "操作人姓名")
    private String operatorUserName;

    @ApiModelProperty(value = "操作人手机号")
    private String operatorUserPhone;

    @ApiModelProperty(value = "操作状态")
    private String operatorStatus;

    @ApiModelProperty(value = "操作时间")
    private Date operatorTime;

    @ApiModelProperty(value = "安装日期")
    private Date installDate;

}
