package com.bank.manage.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/30 11:11
 */
@Data
@ApiModel("满意度考核指标")
public class SatisfactAssessmentDto implements Serializable {

    /**
     * 一级指标编号
     */
    @ApiModelProperty("一级指标编号")
    private Integer satisfactAssessmentId;


    /**
     * 一级考核指标描述
     */
    @ApiModelProperty("一级考核指标描述")
    private String satisfactAssessmentDisc;

    /**
     * 一级考核指标分值
     */
    @ApiModelProperty("一级考核指标分值")
    private Integer satisfactAssessmentScore;

    /**
     * 一级指标实际考核得分
     */
    @ApiModelProperty("一级考核指标实际得分")
    private Integer satisfactAssessmentTrueScore;

    /**
     * 一级考核指标内容
     */
    @ApiModelProperty("一级考核指标内容")
    private String satisfactAssessmentContent;

    /**
     * 一级考核评分标准
     */
    @ApiModelProperty("一级考核评分标准")
    private String satisfactAssessmentStandart;

    /**
     * 二级指标列表
     */
    @ApiModelProperty("二级指标列表")
    private List<SatisfactSecondAssessmentDto> secondAssessmentList;

}
