package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/5/30 11:14
 */
@Data
@ApiModel("二级指标模型")
public class SatisfactSecondAssessmentDto implements Serializable {
    /**
     * 二级指标编号
     */
    @ApiModelProperty(value = "二级指标编号")
    private Integer secondsatisfactAssessmentId;


    /**
     * 二级考核指标描述
     */
    @ApiModelProperty(value = "二级考核指标描述")
    private String secondsatisfactAssessmentDisc;

    /**
     * 二级考核指标分值
     */
    @ApiModelProperty(value = "二级考核指标分值")
    private Integer secondsatisfactAssessmentScore;

    /**
     * 二级考核指标内容
     */
    @ApiModelProperty(value = "二级考核指标内容")
    private String secondsatisfactAssessmentContent;

    /**
     * 二级考核评分标准
     */
    @ApiModelProperty(value = "二级考核评分标准")
    private String secondsatisfactAssessmentStandart;

    /**
     * 二级指标实际得分
     */
    @ApiModelProperty(value = "二级指标实际得分")
    private Integer secondSatisfactTrueScore;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String satisfactAttendItemRemark;
}
