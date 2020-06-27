package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/30 14:35
 */
@Data
@ApiModel("满意度月度考核指标")
public class SatisfactAssessmentActDto implements Serializable {

    /**
     * 主键
     */
    @ApiModelProperty(value = "编号")
    private Integer satisfactAttendId;

    /**
     * 引导员编号
     */
    @ApiModelProperty("行员编号")
    private Integer usherId;

    /**
     *引导员姓名
     */
    @ApiModelProperty("引导员姓名")
    private String usherName;

    /**
     * 机构名称
     */
    @ApiModelProperty("机构名称")
    private String orgName;

    /**
     * 考核年月
     */
    @ApiModelProperty("考核年月")
    private LocalDate satisfactAttendYear;

    /**
     * 考核得分
     */
    @ApiModelProperty("考核得分")
    private Integer satisfactAttendScore;

    /**
     * 提交状态 默认0；10：未提交；20：已提交
     */
    @ApiModelProperty(value = "提交状态 默认0；10：未提交；20：已提交")
    private String satisfactAttendSubmitState;

    /**
     * 银行主管评语
     */
    @ApiModelProperty("银行主管评语")
    private String satisfactAttendRemark;

    /**
     * 指标列表
     */
    @ApiModelProperty("指标列表")
    private List<SatisfactAssessmentDto> listAssessment;
}
