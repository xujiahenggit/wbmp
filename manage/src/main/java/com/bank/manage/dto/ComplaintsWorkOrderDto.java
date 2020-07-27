package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: cq
 * @Date: 2020/5/8 22:50
 * 投诉工单新增
 */
@Data
@ApiModel
public class ComplaintsWorkOrderDto {
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
    @ApiModelProperty(value = "分行")
    private String branch;
    @ApiModelProperty(value = "支行")
    private String subBranch;
    @ApiModelProperty(value = "自助行")
    private String buffetLine;
    @ApiModelProperty(value = "厂商")
    private String vendor;
    @ApiModelProperty(value = "工单描述")
    private String workOrderDescribe;

}
