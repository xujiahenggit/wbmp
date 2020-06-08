package com.bank.log.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Andy
 * @Date: 2020/4/13 15:49
 */
@ApiModel(value = "异常日志 查询时用")
@Data
public class ErrLogVo {

    @ApiModelProperty("第几页：默认第一页")
    private Integer pageIndex = 1;

    @ApiModelProperty("分页大小：默认10行")
    private Integer pageSize = 10;
    /**
     * 操作用户名称
     */
    @ApiModelProperty("操作用户")
    private String optUserName;

    /**
     * 版本号 版本号
     */
    @ApiModelProperty("日志版本")
    private String optVersion;

    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    private String startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
    private String endTime;
}
