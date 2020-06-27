package com.bank.quartz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Andy
 * @Date: 2020/4/13 8:46
 */
@ApiModel("定时任务日志 查询时用")
@Data
public class TaskLogVo {
    @ApiModelProperty("第几页：默认第一页")
    private Integer pageIndex = 1;

    @ApiModelProperty("分页大小：默认10行")
    private Integer pageSize = 10;

    @ApiModelProperty("任务状态 1:执行成功 2：执行失败")
    private String tasklogState;

    @ApiModelProperty("开始时间 yyyy-MM-dd dd:HH:mm")
    private String startTime;

    @ApiModelProperty("结束时间 yyyy-MM-dd dd:HH:mm")
    private String endTime;
}
