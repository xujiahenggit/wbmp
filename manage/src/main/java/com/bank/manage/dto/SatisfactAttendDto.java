package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author: Andy
 * @Date: 2020/5/30 11:35
 */
@Data
@ApiModel(value = "月满意度考核")
public class SatisfactAttendDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "编号")
    private String satisfactAttendId;

    /**
     * 引导员ID
     */
    @ApiModelProperty(value = "引导员ID")
    private Integer usherId;

    /**
     * 引导员姓名
     */
    @ApiModelProperty(value = "引导员姓名")
    private String usherName;

    /**
     * 考核年月
     */
    @ApiModelProperty(value = "考核年月")
    private LocalDate satisfactAttendYear;

    /**
     * 考核得分
     */
    @ApiModelProperty(value = "考核得分")
    private Integer satisfactAttendScore;

    /**
     * 提交状态 默认0；10：未提交；20：已提交
     */
    @ApiModelProperty(value = "提交状态 默认0；10：未提交；20：已提交")
    private String satisfactAttendSubmitState;

    /**
     * 提交人
     */
    @ApiModelProperty(value = "提交人")
    private String satisfactAttendSubmitUserid;

    /**
     * 提交人姓名
     */
    @ApiModelProperty(value = "提交人姓名")
    private String satisfactAttendSubmitUsername;

    /**
     * 提交时间
     */
    @ApiModelProperty(value = "提交时间")
    private LocalDateTime satisfactAttendSubmitTime;

    /**
     * 银行主管评语
     */
    @ApiModelProperty(value = "银行主管评语")
    private String satisfactAttendRemark;
}
