package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 *
 *
 * @author 陈强
 * @since
 */
@Data
@ApiModel(value = "巡检单详情")
public class InspectionRepairVo {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "工单编号")
    private String repairCode;
    @ApiModelProperty(value = "工单类型")
    private String repairType;
    @ApiModelProperty(value = "工单状态 0 :待处理；1：待评价；2：办接；3：待分行确认；4：待总行确认；4：待厂商回复；6：总行知悉；7：分行知悉；8：退回；9：已关闭")
    private String repairStatus;
    @ApiModelProperty(value = "创建人姓名")
    private String createName;
    @ApiModelProperty(value = "创建人手机")
    private String createPhone;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "巡检陪同人员")
    private String escortsPatrolName;
    @ApiModelProperty(value = "巡检陪同人员手机号")
    private String escortsPatrolPhone;
    @ApiModelProperty(value = "工单描述")
    private String workOrderDescribe;
    @ApiModelProperty(value = "厂商名称")
    private String vendorName;
    @ApiModelProperty(value = "巡检开始时间")
    private Date escortsStartTime;
    @ApiModelProperty(value = "巡检结束时间")
    private Date escortsCompletTime;
    @ApiModelProperty(value = "巡检处理方式 1：处理方式 1 ；2：处理方式2；3:处理方式3；4：处理方式4；5：处理方式5；6：处理方式6")
    private String  escortsHandling;
    @ApiModelProperty(value = "用户类型 2:分行；1:总行")
    private String userType;
    @ApiModelProperty(value = " 0:发起人,1:否")
    private String isCreateUser;
    @ApiModelProperty(value = "服务信息")
    private ServiceInfoVo serviceInfoVo;
}
