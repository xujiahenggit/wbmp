package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 对账指令维护
 *
 * @author
 * @date 2020-7-8
 */
@Data
@ApiModel(description = "对账指令维护")
public class TaccountingOrderDTO implements Serializable {

    private static final long serialVersionUID = 2031504375046901480L;

    @ApiModelProperty(value = "主键自增ID")
    private Integer id;

    @ApiModelProperty(value = "机构号", required = true)
    private String branch;

    @ApiModelProperty(value = "组织机构名称")
    private String orgName;

    @ApiModelProperty(value = "周期")
    private String cycle;

    @ApiModelProperty(value = "次数0不对账")
    private String numbers;

    @ApiModelProperty(value = "对账月份")
    private String month;

    @ApiModelProperty(value = "录入柜员")
    private String tellerInsert;

    @ApiModelProperty(value = "录入时间")
    private LocalDateTime timeInsert;

    @ApiModelProperty(value = "更新柜员")
    private String tellerUpdate;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime timeUpdate;

    @ApiModelProperty(value = "状态0-启用1-停用")
    private String status;



}
