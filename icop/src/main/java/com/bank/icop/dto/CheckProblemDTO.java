package com.bank.icop.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * ClassName: CheckProblemDTO
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/06/28 10:38:21
 */
@Data
public class CheckProblemDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2056550328884797969L;

    @ApiModelProperty(value = "检查任务ID")
    private String taskId;

    @ApiModelProperty(value = "问题Id")
    private String problemId;

    @ApiModelProperty(value = "问题登记时间")
    private String problemDate;

    @ApiModelProperty(value = "问题类型")
    private String problemType;

    @ApiModelProperty(value = "问题整改机构")
    private String problemRectifyOrg;

    @ApiModelProperty(value = "整改责任人编号，多个用半角逗号隔开")
    private String rectifyDutyUser;

    @ApiModelProperty(value = "整改责任人名称，多个用半角逗号隔开")
    private String rectifyDutyUserName;

    @ApiModelProperty(value = "问题描述")
    private String problemDes;

}
