package com.bank.quartz.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/4/2 11:12
 */
@Data
@ApiModel(description = "定时任务")
public class TaskEntityDTO implements Serializable {

    private static final long serialVersionUID = 1041407982370766342L;

    @ApiModelProperty(value = "任务ID",notes = "更新时不能为空")
    private Integer taskId;
    /**
     * 定时任务名称
     */
    @ApiModelProperty(value = "任务名称")
    private String taskName;

    @ApiModelProperty(value = "任务状态 0：已发布 1：暂停")
    /**
     * 任务状态 任务状态 默认为 0  0：已发布 1：暂停
     */
    private String taskStatus;

    /**
     * 定时任务组
     */
    @ApiModelProperty(value = "任务组")
    private String taskGroup;

    /**
     * cron表达式
     */
    @ApiModelProperty(value = "任务Corn表达式")
    private String taskCorn;

    /**
     * bean
     */
    @ApiModelProperty(value = "定时任务类")
    private String taskBean;

    /**
     * 备注
     */
    @ApiModelProperty(value = "定时任务备注")
    private String taskRemark;
}
