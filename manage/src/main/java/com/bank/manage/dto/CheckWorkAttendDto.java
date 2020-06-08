package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/6/2 14:38
 */
@ApiModel("外包人员考勤")
@Data
public class CheckWorkAttendDto implements Serializable {

    /**
     * 满意度 已完成人数
     */
    @ApiModelProperty(value = "满意度 已完成人数")
    private int satisfactAttendFinshNum;
    /**
     * 满意度 待审核人数
     */
    @ApiModelProperty(value = "满意度 未完成人数")
    private int satisfactAttendWaitNum;
    /**
     * 满意度 已驳回人数
     */
    @ApiModelProperty(value = "满意度 已驳回人数")
    private int satisfactAttendRejectNum;
    /**
     * 月度考勤 已完成人数
     */
    @ApiModelProperty(value = "月度考勤 已完成人数")
    private int monthAttendFinshNum;
    /**
     * 月度考勤 待审核人数
     */
    @ApiModelProperty(value = "月度考勤 待审核人数")
    private int monthAttendWaitNum;
    /**
     * 月度考勤  驳回人数
     */
    @ApiModelProperty(value = "月度考勤  驳回人数")
    private int monthAttendRejectNum;
}
