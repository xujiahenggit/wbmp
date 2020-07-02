package com.bank.esb.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: cq
 * @Date: 2020/7/2
 */
@ApiModel(description = "工程师Dto")
@Data
public class EngineerDto {
    @ApiModelProperty(value = "工程师编号")
    private String engineerId;

    @ApiModelProperty(value = "工程师名称")
    private String engineerName;

    @ApiModelProperty(value = "工程师手机号")
    private String phone;
}

