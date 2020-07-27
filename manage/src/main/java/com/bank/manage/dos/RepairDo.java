package com.bank.manage.dos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: cq
 * @Date: 2020/5/8 22:50
 * (t_repair)
 */
@Data
@ApiModel
public class RepairDo implements Serializable {
    private static final long serialVersionUID = -572104941560733167L;

    @ApiModelProperty(value = "ID")
    private Integer id;


    @ApiModelProperty(value = "终端编号")
    private String terminalCode;


    @ApiModelProperty(value = "工单类型")
    private String workOrderType;

    @ApiModelProperty(value = "优先级编号")
    private String priorityCode;

    @ApiModelProperty(value = "模块编号")
    private String moduleCode;

    @ApiModelProperty(value = "创建人id")
    private String createId;

    @ApiModelProperty(value = "要求完成时间")
    private Date requirCompleteTime;

    @ApiModelProperty(value = "工单编号")
    private String workOrderCode;

    @ApiModelProperty(value = "工单状态")
    private String workOrderStatus;

    @ApiModelProperty(value = "工单描述")
    private String workOrderDescribe;

    @ApiModelProperty(value = "服务主管")
    private String director;

    @ApiModelProperty(value = "服务工程师")
    private String engineer;

    @ApiModelProperty(value = "工单完成时间")
    private Date workOrderCompleteTime;

    @ApiModelProperty(value = "描述类型")
    private String describeType;

    @ApiModelProperty(value = "评价等级")
    private String rating;

    @ApiModelProperty(value = "评价备注")
    private String ratingNote;

    @ApiModelProperty(value = "处理类型")
    private String dealType;

    @ApiModelProperty(value = "处理备注")
    private String dealNote;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "巡检陪同人员")
    private String escortsPatrol;
    @ApiModelProperty(value = "巡检开始时间")
    private Date escortsStartTime;
    @ApiModelProperty(value = "巡检完成时间")
    private Date escortsCompletetTime;
    @ApiModelProperty(value = "巡检处理方式")
    private String escortsHandling;
    @ApiModelProperty(value = "分行")
    private String branch;
    @ApiModelProperty(value = "支行")
    private String subBranch;
    @ApiModelProperty(value = "自助行")
    private String buffetLine;
    @ApiModelProperty(value = "厂商")
    private String vendor;

}
