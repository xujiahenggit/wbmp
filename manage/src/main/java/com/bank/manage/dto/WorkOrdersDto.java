package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: cq
 * @Date: 2020/5/8 22:50
 * (t_repair)
 */
@Data
@ApiModel
public class WorkOrdersDto {
    @ApiModelProperty("第几页：默认第一页")
    private Integer pageIndex = 1;

    @ApiModelProperty("分页大小：默认10行")
    private Integer pageSize = 10;
    @ApiModelProperty("排序字段")
    private String sort;

    @ApiModelProperty("排序方式")
    private String order;

    @ApiModelProperty("工单类型（1-故障工单；2-投诉工单；3-巡检）")
    private String workOrderType;

    @ApiModelProperty("来源类型 1 我发起的，2 我审批的、3 我办结的、4 系统自建；5 所有")
    private String sourceType;
    @ApiModelProperty("登录人id")
    private String logUserId;
    @ApiModelProperty(value = "厂商")
    private String vendor;
    @ApiModelProperty(value = "0 :待处理；1：待评价；2：办接；3：待分行确认；4：待总行确认；4：待厂商回复；6：总行知悉；7：分行知悉；8：退回；9：已关闭")
    private String status;
    @ApiModelProperty(value = "设备所在机构")
    private String orgId;
    @ApiModelProperty(value = "终端编号")
    private String terminalCode;


}
