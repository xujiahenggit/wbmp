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
    @ApiModelProperty(value = "工单类型 01-故障工单；02-投诉工单；03-巡检")
    private String workOrderType;
    @ApiModelProperty(value = "优先级编号（1：一般 2：紧急）")
    private String priorityCode;
    @ApiModelProperty(value = "服务主管(手机号)")
    private String director;
    @ApiModelProperty(value = "服务主管(名称)")
    private String directorName;
    @ApiModelProperty(value = "要求完成时间")
    private String requirCompleteTime;
    @ApiModelProperty(value = "工单描述")
    private String workOrderDescribe;
    @ApiModelProperty(value = "描述类型")
    private String describeType;
    @ApiModelProperty(value = "工单编号")
    private String workOrderCode;
    @ApiModelProperty(value = "机构id")
    private String orgId;
    @ApiModelProperty(value = "机构名称")
    private String orgName;
    @ApiModelProperty(value = "工单状态 工单状态  0 :待处理；1：待评价；2：办接；3：待分行确认；4：待总行确认；4：待厂商回复；6：总行知悉；7：分行知悉；8：退回；9：已关闭")
    private String workOrderStatus;
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    @ApiModelProperty(value = "创建人id")
    private String createId;
    @ApiModelProperty(value = "创建人姓名")
    private String createName;
    @ApiModelProperty(value = "创建人手机号")
    private String createUserPhone;
    @ApiModelProperty(value = "现场联系人")
    private String contactName;
    @ApiModelProperty(value = "现场联系人号码")
    private String contactPhone;
    @ApiModelProperty(value = "厂商Id")
    private String vendorId;
    @ApiModelProperty(value = "厂商名称")
    private String vendorName;
    @ApiModelProperty(value = "设备型号")
    private String deviceModel;
    @ApiModelProperty(value = "设备类型")
    private String deviceType;

}
