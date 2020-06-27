package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/6/2 17:30
 */
@ApiModel("月度考勤数查询用")
@Data
public class CheckWorkAttendQueryVo implements Serializable {
    @ApiModelProperty("第几页：默认第一页")
    private Integer pageIndex = 1;

    @ApiModelProperty("分页大小：默认10行")
    private Integer pageSize = 10;

    @ApiModelProperty("日期")
    private String date;
}
