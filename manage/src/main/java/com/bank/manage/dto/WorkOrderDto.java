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
public class WorkOrderDto {
    @ApiModelProperty(value = "终端编号")
    private String terminalCode;
    @ApiModelProperty(value = "工单类型")
    private String workOrderType;
    @ApiModelProperty(value = "优先级编号")
    private String priorityCode;
    @ApiModelProperty(value = "服务主管")
    private String director;
    @ApiModelProperty(value = "要求完成时间")
    private Date requirCompleteTime;
    @ApiModelProperty(value = "工单描述")
    private String workOrderDescribe;
    @ApiModelProperty(value = "描述类型")
    private String describeType;
    @ApiModelProperty(value = "工单编号")
    private String workOrderCode;

    @ApiModelProperty(value = "工单状态")
    private String workOrderStatus;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
