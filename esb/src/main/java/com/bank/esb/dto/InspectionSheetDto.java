package com.bank.esb.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: cq
 * @Date: 2020/7/2
 */
@ApiModel(description = "巡检单创建查询Dto")
@Data
public class InspectionSheetDto {
    @ApiModelProperty(value = "设备编号")
    private String inprocessNo;

    @ApiModelProperty(value = "巡检状态")
    private String inprocessStatus;

    @ApiModelProperty(value = "序列号")
    private String serialNum;

    @ApiModelProperty(value = "设备属性")
    private String deviceProperty;

    @ApiModelProperty(value = "所属机构")
    private String orgId;

    @ApiModelProperty(value = "机构地址")
    private String orgAddress;

    @ApiModelProperty(value = "保修截止时间")
    private Date warrantyTime;
}
