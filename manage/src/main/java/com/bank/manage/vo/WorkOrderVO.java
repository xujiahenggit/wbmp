package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 *
 *
 * @author 陈强
 * @since
 */
@Data
@ApiModel(value = "工单列表查询")
public class WorkOrderVO {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "设备所在机构")
    private String orgId;
    @ApiModelProperty(value = "机构名称")
    private String orgName;
    @ApiModelProperty(value = "工单状态 0 :待处理；1：待评价；2：办接；3：待分行确认；4：待总行确认；4：待厂商回复；6：总行知悉；7：分行知悉；8：退回；9：已关闭")
    private String status;
    @ApiModelProperty(value = "设备类型")
    private String deviceType;
    @ApiModelProperty(value = "厂商id")
    private String vendorId;
    @ApiModelProperty(value = "厂商名称")
    private String vendorName;
    @ApiModelProperty(value = "终端编号")
    private String terminalCode;

    @ApiModelProperty(value = "工单编号")
    private String workOrderCode;
    @ApiModelProperty(value = "设备型号")
    private String deviceModel;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "处理方式")
    private String way;
    @ApiModelProperty(value = "服务工程师")
    private String engineer;
    @ApiModelProperty(value = "工单类型")
    private String workOrderType;
}
