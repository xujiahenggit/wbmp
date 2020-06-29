package com.bank.icop.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * ClassName: TaskCheckAuditDTO
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/06/29 19:30:24
 */
@Data
public class TaskCheckAuditDTO implements Serializable {

    /**
    *
    */
    private static final long serialVersionUID = -1156826723684895205L;

    @ApiModelProperty(value = "检查任务项ID")
    private String taskItemId;

    @ApiModelProperty(value = "检查任务项-执行检查信息ID")
    private String inspectionInfoId;

    @ApiModelProperty(value = "审核结论")
    private String result;

    @ApiModelProperty(value = "备注意见")
    private String remarkOpinion;

    @ApiModelProperty(value = "整改反馈期限")
    private String rectifyTimeLimit;
}
