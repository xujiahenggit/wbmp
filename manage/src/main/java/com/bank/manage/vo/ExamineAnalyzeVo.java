package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "季度考核分析图查询返回信息")
public class ExamineAnalyzeVo implements Serializable {
    private static final long serialVersionUID = 5075377069912848354L;

    @ApiModelProperty(value = "统计模块名" )
    private String oneModule;

    /*@ApiModelProperty(value = "平均值" )
    private String avgDeduction;*/

    @ApiModelProperty(value = "一季度" )
    private String oneQuarter;

    @ApiModelProperty(value = "二季度" )
    private String twoQuarter;

    @ApiModelProperty(value = "三季度" )
    private String threeQuarter;

    @ApiModelProperty(value = "四季度" )
    private String fourQuarter;
}
