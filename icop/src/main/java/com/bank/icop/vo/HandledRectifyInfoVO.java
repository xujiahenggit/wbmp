package com.bank.icop.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * ClassName: HandledRectifyInfoVO
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/07/01 15:01:06
 */
@Data
@ApiModel(description = "已处理整改详细信息VO")
public class HandledRectifyInfoVO implements Serializable {

    /**
    *
    */
    private static final long serialVersionUID = 5394164019640487952L;

    @ApiModelProperty(value = "要求反馈期限")
    private String correctiveFeedbackDT;

    @ApiModelProperty(value = "整改责任人")
    private String qUser;

    @ApiModelProperty(value = "检查内容")
    private String childPointName;

    @ApiModelProperty(value = "检查要点")
    private String sunpointName;

    @ApiModelProperty(value = "问题类型")
    private String qType;

    @ApiModelProperty(value = "问题描述")
    private String qDes;

    @ApiModelProperty(value = "整改意见")
    private String correctiveSuggest;

    @ApiModelProperty(value = "整改反馈")
    private String correctiveFeedback;

    @ApiModelProperty(value = "审批日志")
    private List<ApproveLogVO> approveLogList;

    @ApiModelProperty(value = "附件列表")
    private List<CheckAccessoryVO> accessoryList;
}
