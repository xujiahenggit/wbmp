package com.bank.log.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/4/13 15:49
 */
@ApiModel("正常日志  查询时用")
@Data
public class LogQueryVo implements Serializable {

    @ApiModelProperty("第几页：默认第一页")
    private Integer pageIndex = 1;

    @ApiModelProperty("分页大小：默认10行")
    private Integer pageSize = 10;

    /**
     * 操作模块 操作模块
     */
    @ApiModelProperty("操作模块")
    private String optModual;

    /**
     * 操作类型 操作类型
     */
    @ApiModelProperty("操作类型")
    private String optType;

    @ApiModelProperty("操作用户")
    private String optUserName;

    /**
     * 版本号 版本号
     */
    @ApiModelProperty("日志版本号")
    private String optVersion;

    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间 yyyy-MM-dd HH:mm:ss")
    private String startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间 yyyy-MM-dd HH:mm:ss")
    private String endTime;
}
