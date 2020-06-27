package com.bank.manage.vo;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "快乐服务报表参数")
public class HappyParam {

    @ApiModelProperty(value = "要查询的年份，为空默认查询当前年",allowEmptyValue = true,example = "2020")
    private Integer year = DateUtil.thisYear();

    @ApiModelProperty(value = "要查询的季度，为空默认查询当前季度",allowEmptyValue = true,example = "2")
    private Integer quarter = DateUtil.quarter(DateUtil.date());

    @ApiModelProperty(value = "网点id，多个用','隔开; 为空查询全部",allowEmptyValue = true)
    private String networks;

    @ApiModelProperty(value = "分支行机构id，多个用','隔开; 为空查询全部",allowEmptyValue = true)
    private String orgids;

    @ApiModelProperty(hidden = true)
    private boolean hasAdmin;
    @ApiModelProperty(hidden = true)
    private String userId;
    @ApiModelProperty(hidden = true)
    private String lastYearQuarter;
    ;
}
