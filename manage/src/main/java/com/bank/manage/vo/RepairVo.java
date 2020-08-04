package com.bank.manage.vo;

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
}