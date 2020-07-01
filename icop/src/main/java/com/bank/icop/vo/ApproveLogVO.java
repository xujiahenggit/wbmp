package com.bank.icop.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * ClassName: ApproveLogVO
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/07/01 15:09:39
 */
@Data
@ApiModel(description = "审批日志VO")
public class ApproveLogVO implements Serializable {

    /**
    *
    */
    private static final long serialVersionUID = -6770791701378524268L;

    @ApiModelProperty(value = "日志序号")
    private int logSeq;

    @ApiModelProperty(value = "处理人")
    private String dealMan;

    @ApiModelProperty(value = "处理角色")
    private String roleKey;

    @ApiModelProperty(value = "处理方式")
    private String decision;

    @ApiModelProperty(value = "处理意见")
    private String approveLog;

    @ApiModelProperty(value = "处理时间")
    private String createTime;
}
