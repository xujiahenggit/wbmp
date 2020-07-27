package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author: Zhangfuqiang
 * @Date: 2020/6/15 10:15
 */
@Data
@ApiModel("运营曲线图分数")
public class OrgScoreVo {

    /**
     * 所属机构号
     */
    @ApiModelProperty("机构号必填")
    private String orgId;


    /**
     * 分数
     */
    @ApiModelProperty("分数")
    private String score;


    /**
     * 日期
     */
    @ApiModelProperty("日期")
    private String dateDt;



}
