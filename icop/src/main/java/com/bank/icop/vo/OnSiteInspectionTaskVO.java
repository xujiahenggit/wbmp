package com.bank.icop.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 现场检查任务VO
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
@NoArgsConstructor
@ApiModel(description = "现场检查任务代办VO")
public class OnSiteInspectionTaskVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3770434313423022359L;

    @ApiModelProperty(value = "任务ID")
    private String taskId;

    @ApiModelProperty(value = "任务年度")
    private String taskYear;

    @ApiModelProperty(value = "任务类型")
    private String taskType;

    @ApiModelProperty(value = "任务名称")
    private String taskName;

    @ApiModelProperty(value = "任务内容")
    private String taskContent;

    @ApiModelProperty(value = "任务开始时间")
    private String taskStartDate;

    @ApiModelProperty(value = "任务结束时间")
    private String taskEndDate;

    @ApiModelProperty(value = "任务检查项数量")
    private String number;

}
