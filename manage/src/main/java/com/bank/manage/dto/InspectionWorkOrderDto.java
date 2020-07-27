package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: cq
 * @Date: 2020/5/8 22:50
 * 巡检工单新增
 */
@Data
@ApiModel
public class InspectionWorkOrderDto {
    @ApiModelProperty(value = "终端编号")
    private String terminalCode;
    @ApiModelProperty(value = "陪同人员")
    private String escortsPatrol;
    @ApiModelProperty(value = "巡检开始时间")
    private Date startTime;
    @ApiModelProperty(value = "巡检结束时间")
    private Date endTime;
    @ApiModelProperty(value = "巡检处理方式（1：处理方式 1 ；2：处理方式2；3:处理方式3；4：处理方式4；5：处理方式5；6：处理方式6）")
    private List<String> escortsHandlingList;
    @ApiModelProperty(value = "工单描述")
    private String workOrderDescribe;
    @ApiModelProperty(value = "工单类型 1-故障工单；2-投诉工单；3-巡检")
    private String workOrderType;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "创建人id")
    private String createId;
    @ApiModelProperty(value = "创建人姓名")
    private String createName;
    @ApiModelProperty(value = "工单状态  0 :待处理；1：待评价；2：办接；3：待分行确认；4：待总行确认；4：待厂商回复；6：总行知悉；7：分行知悉；8：退回；9：已关闭")
    private String workOrderStatus;
    @ApiModelProperty(value = "工单编号")
    private String workOrderCode;
    @ApiModelProperty(value = "处理多个处理方式")
    private String json;

}
