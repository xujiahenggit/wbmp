package com.bank.manage.dto;

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
public class CompletedWordOrderDto {
    @ApiModelProperty(value = "工单编号")
    private String workOrderCode;
    @ApiModelProperty(value = "要求完成时间")
    private Date requirCompleteTime;
    @ApiModelProperty(value = "创建人id")
    private String createId;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "工单描述")
    private String workOrderDescribe;
    @ApiModelProperty(value = "陪同人员")
    private String escortsPatrol;
}
