package com.bank.icop.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 现场检查任务项VO
 * ClassName: OnSiteInspectionTaskVO
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/06/22 18:59:17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "现场检查任务项VO")
public class OnSiteInspectionTaskItemVO extends OnSiteInspectionTaskVO {

    /**
     *
     */
    private static final long serialVersionUID = -3770434313423022359L;

    @ApiModelProperty(value = "任务ID")
    private String taskId;

    @ApiModelProperty(value = "检查任务项ID")
    private String taskItemId;

    @ApiModelProperty(value = "检查任务项-内容名称")
    private String taskItemName;

    @ApiModelProperty(value = "检查任务项-执行检查信息ID")
    private String inspectionInfoId;

    @ApiModelProperty(value = "检查任务项-执行检查状态码")
    private String state;

    @ApiModelProperty(value = "检查任务项-执行检查状态名称")
    private String stateName;

    @ApiModelProperty(value = "执行检查人")
    private String examinUser;

    @ApiModelProperty(value = "执行检查所属机构")
    private String examinUserOrg;

    @ApiModelProperty(value = "是否存在问题")
    private String isQusetion;

    @ApiModelProperty(value = "再分配人员")
    private String assignationUser;
}
