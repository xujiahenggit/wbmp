package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @Author: Andy
 * @Date: 2020/6/2 19:23
 */
@ApiModel("巡查记录头")
@Data
public class PartorlRecordHeadDto implements Serializable {
    @ApiModelProperty(value = "待办日期")
    private LocalDate partorlProcessDate;

    @ApiModelProperty(value = "填报人姓名")
    private String saveUserName;

    @ApiModelProperty(value = "是否超时")
    private String isOverTime;

    @ApiModelProperty(value = "分支行名称")
    private String  branchName;

    @ApiModelProperty(value = "网点名称")
    private String orgName;
}
