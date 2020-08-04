package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 逾期催收时限
 *
 * @author
 * @date 2020-7-8
 */
@Data
@ApiModel(description = "逾期催收时限")
public class TlimitTimeDTO implements Serializable {

	private static final long serialVersionUID = 3724656268296968552L;

    @ApiModelProperty(value = "主键自增ID")
    private Integer id;

    @ApiModelProperty(value = "机构号", required = true)
    private String branch;

    @ApiModelProperty(value = "催收时限(天)")
    private String timeLimit;

    @ApiModelProperty(value = "频次")
    private String frequency;

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
