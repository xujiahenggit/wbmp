package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/5/29 16:42
 */
@Data
@ApiModel("月度考勤审批 查询用")
public class MonthAttendQueryVo implements Serializable {
    @ApiModelProperty("第几页：默认第一页")
    private Integer pageIndex = 1;

    @ApiModelProperty("分页大小：默认10行")
    private Integer pageSize = 10;
    /**
     * 引导员手机号码
     */
    @ApiModelProperty("引导员手机号码")
    private String userPhone;
}
