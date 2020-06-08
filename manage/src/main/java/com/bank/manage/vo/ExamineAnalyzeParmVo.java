package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
@ApiModel(description = "季度考核数据分析图参数信息")
public class ExamineAnalyzeParmVo implements Serializable {
    private static final long serialVersionUID = -1885700522909043698L;

    @ApiModelProperty(value = "统计开始时间  年份" )
    private String startYear;

    @ApiModelProperty(value = "统计结束时间  年份" )
    private String endYear;

    @ApiModelProperty(value = "统计开始季度" )
    private String startQuarter;

    @ApiModelProperty(value = "统计结束季度" )
    private String endQuarter;

    @ApiModelProperty(value = "统计分支行机构号" )
    private String branchOrgId;

    @ApiModelProperty(value = "统计网点机构号" )
    private String outOrgId;

    @ApiModelProperty(value = "统计模块" )
    private String module;

}
