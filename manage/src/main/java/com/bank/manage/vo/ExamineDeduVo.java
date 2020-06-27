package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "季度考核数据扣分明细返回信息")
public class ExamineDeduVo implements Serializable {
    private static final long serialVersionUID = 5889369338333032399L;

    @ApiModelProperty(value = "分支行名称")
    private String branchOrgName;

    @ApiModelProperty(value = "分支行机构号")
    private String branchOrgId;

    @ApiModelProperty(value = "网点名称")
    private String outOrgName;

    @ApiModelProperty(value = "网点机构号")
    private String outOrgId;

    @ApiModelProperty(value = "年份")
    private String excelDate;

    @ApiModelProperty(value = "季度")
    private String excelQuarter;

    @ApiModelProperty(value = "得分")
    private String examineScore;

    @ApiModelProperty(value = "第一模块")
    private String oneModule;

    @ApiModelProperty(value = "第二模块")
    private String twoModule;

    @ApiModelProperty(value = "扣分分值")
    private String deduction;

    @ApiModelProperty(value = "扣分描述")
    private String deductionSpec;
}
