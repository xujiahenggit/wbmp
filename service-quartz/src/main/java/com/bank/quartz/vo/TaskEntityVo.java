package com.bank.quartz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/4/20 10:56
 */
@ApiModel("定时任务 查询时用")
@Data
public class TaskEntityVo implements Serializable {
    @ApiModelProperty("第几页：默认第一页")
    private Integer pageIndex = 1;

    @ApiModelProperty("分页大小：默认10行")
    private Integer pageSize = 10;

    /**
     * 定时任务名称
     */
    @ApiModelProperty("定时任务名称")
    private String taskName;

    /**
     * 定时任务组
     */
    @ApiModelProperty("定时任务组")
    private String taskGroup;
}
