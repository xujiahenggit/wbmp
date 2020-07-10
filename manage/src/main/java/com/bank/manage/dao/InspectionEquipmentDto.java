package com.bank.manage.dao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: cq
 * @Date: 2020/5/8 22:50
 * (t_repair)
 */
@Data
@ApiModel
public class InspectionEquipmentDto {

    @ApiModelProperty(value = "设备分类")
    private String equipmentClass;
    @ApiModelProperty(value = "终端编号")
    private String terminalCode;
    @ApiModelProperty(value = "设备类型")
    private String equipmentType;
    @ApiModelProperty(value = "统计时间  1:本季度 2：上季度  3：本半年 4：上半年")
    private String statisticalTime;
    @ApiModelProperty(value = "巡检开始时间")
    private Date startTime;
    @ApiModelProperty(value = "巡检结束时间")
    private Date endTime;
    @ApiModelProperty(value = "机构")
    private String orgId;
    @ApiModelProperty(value = "服务商")
    private String serviceProviders;
    @ApiModelProperty(value = "巡检标识 1:已巡检 0：未巡检")
    private String logo;

}
