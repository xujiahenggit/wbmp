package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: cq
 * @Date: 2020/5/8 22:50
 * (t_repair)
 */
@Data
@ApiModel
public class RepairVo implements Serializable {
    private static final long serialVersionUID = -572104941560733167L;

    @ApiModelProperty(value = "ID")
    private Integer id;


    @ApiModelProperty(value = "设备编号")
    private String equipmentCode;


    @ApiModelProperty(value = "工单类型")
    private String repairType;

    @ApiModelProperty(value = "优先级编号")
    private String priorityCode;

    @ApiModelProperty(value = "模块编号")
    private String moduleCode;

    @ApiModelProperty(value = "创建人id")
    private String createId;

    @ApiModelProperty(value = "完成时间")
    private Date completeTime;

    @ApiModelProperty(value = "工单编号")
    private String repairCode;

    @ApiModelProperty(value = "工单状态")
    private String repairStatus;

    @ApiModelProperty(value = "创建人姓名")
    private String createName;

    @ApiModelProperty(value = "创建人手机号码")
    private String createPhone;


    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "现场联系人")
    private String contactName;
    @ApiModelProperty(value = "现场联系人号码")
    private String contactPhone;
    @ApiModelProperty(value = "描述类型")
    private String describeType;
    @ApiModelProperty(value = "描述描述")
    private String workOrderDescribe;
    @ApiModelProperty(value = "工单编号")
    private String workOrderCode;
    @ApiModelProperty(value = "服务信息")
    private ServiceInfoVo serviceInfoVo;
    @ApiModelProperty(value = "机构名称")
    private String orgName;
    @ApiModelProperty(value = "厂商名称")
    private String vendorName;
    @ApiModelProperty(value = "厂商id")
    private String vendor;
    @ApiModelProperty(value = "用户类型 2:分行管理员；1:总行管理员")
    private String userType;
    @ApiModelProperty(value = " 0:发起人,1:否")
    private String isCreateUser;

    private List<ContactVo> contactVoList;
}
