package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/5/29 16:44
 */
@Data
@ApiModel("月满意度考勤审批驳回用")
public class MonthAttendPassRejectVo implements Serializable {
    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    private Integer monthAttendId;

    /**
     * 驳回原因
     */
    @ApiModelProperty(value = "驳回原因")
    private String monthAttendRejectResion;
}
