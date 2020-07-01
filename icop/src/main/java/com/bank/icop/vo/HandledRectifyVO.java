package com.bank.icop.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * ClassName: HandledRectifyVO
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/07/01 11:10:05
 */
@Data
@ApiModel(description = "已处理整改VO")
public class HandledRectifyVO implements Serializable {

    /**
    *
    */
    private static final long serialVersionUID = 6155351171823829923L;

    @ApiModelProperty(value = "整改ID")
    private String rectifyId;

    @ApiModelProperty(value = "检查任务")
    private String taskName;

    @ApiModelProperty(value = "检查子项")
    private String childPointName;

    @ApiModelProperty(value = "问题描述")
    private String qDes;

    @ApiModelProperty(value = "整改机构")
    private String orgName;

    @ApiModelProperty(value = "整改责任人")
    private String qUserName;

    @ApiModelProperty(value = "问题登记时间")
    private String qDate;

    @ApiModelProperty(value = "要求整改反馈期限")
    private String correctiveFeedbackDT;

    @ApiModelProperty(value = "整改反馈说明")
    private String correctiveFeedbackDes;

    @ApiModelProperty(value = "整改状态")
    private String status;

}
