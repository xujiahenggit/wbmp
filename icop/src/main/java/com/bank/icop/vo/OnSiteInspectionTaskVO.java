package com.bank.icop.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
     * 获取运营检查任务列表
     * @param taskId
     * @param taskYear
     * @param taskName
     * @param taskStartDate
     * @param taskEndDate
     * @param number
     */
    public OnSiteInspectionTaskVO(Object taskId, Object taskYear, Object taskName, Object taskStartDate, Object taskEndDate, Object number) {
        this.taskId = taskId;
        this.taskYear = taskYear;
        this.taskName = taskName;
        this.taskStartDate = taskStartDate;
        this.taskEndDate = taskEndDate;
        this.number = number;
    }

    /**
     *
     */
    private static final long serialVersionUID = -3770434313423022359L;

    @ApiModelProperty(value = "任务ID")
    private Object taskId;

    @ApiModelProperty(value = "任务年度")
    private Object taskYear;

    @ApiModelProperty(value = "任务编号")
    private Object taskNo;

    @ApiModelProperty(value = "任务类型")
    private Object taskType;

    @ApiModelProperty(value = "任务名称")
    private Object taskName;

    @ApiModelProperty(value = "任务内容")
    private Object taskContent;

    @ApiModelProperty(value = "任务开始时间")
    private Object taskStartDate;

    @ApiModelProperty(value = "任务结束时间")
    private Object taskEndDate;

    @ApiModelProperty(value = "任务检查项数量")
    private Object number;

    @ApiModelProperty(value = "任务创建时间")
    private Object taskCreateDate;
    
    @ApiModelProperty(value = "任务创建机构")
    private Object taskCreateOrg;
    
    @ApiModelProperty(value = "任务创建人")
    private Object taskCreateUser;
    
    @ApiModelProperty(value = "任务状态")
    private Object taskSatus;
}
