package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: cq
 * @Date: 2020/5/8 22:50
 * 故障工单信息
 */
@Data
@ApiModel
public class BreakDownWorkOrderVo {

    @ApiModelProperty(value = "工单编号")
    private String workOrderCode;
    @ApiModelProperty(value = "工单状态 工单状态  0 :待处理；1：待评价；2：办接；3：待分行确认；4：待总行确认；4：待厂商回复；6：总行知悉；7：分行知悉；8：退回；9：已关闭")
    private String workOrderStatus;
    @ApiModelProperty(value = "优先级编号")
    private String priorityCode;
    @ApiModelProperty(value = "要求完成时间")
    private Date requirCompleteTime;
    @ApiModelProperty(value = "创建人姓名")
    private String createName;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "工单描述")
    private String workOrderDescribe;
}
