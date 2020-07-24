package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: cq
 * @Date: 2020/5/8 22:50
 * (t_repair)
 */
@Data
@ApiModel
public class ConditionsDto {
    @ApiModelProperty("工单编号")
    private  String repairCode;
    @ApiModelProperty("1：系统自建；2：人工创建")
    private  String flog;
}
