package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "季度考核数据排名查询返回信息")
public class ExamineDataRankVo implements Serializable {
    private static final long serialVersionUID = -6694175898592027042L;

    @ApiModelProperty(value = "分支行机构名称" )
    private String branchName;

    @ApiModelProperty(value = "一季度" )
    private String oneQuarter;

    @ApiModelProperty(value = "二季度" )
    private String twoQuarter;

    @ApiModelProperty(value = "三季度" )
    private String threeQuarter;

    @ApiModelProperty(value = "四季度" )
    private String fourQuarter;

    @ApiModelProperty(value = "排序" )
    private Integer sort;

}
