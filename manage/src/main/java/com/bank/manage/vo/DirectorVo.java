package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 服务信息
 * 陈强
 */
@Data
@ApiModel
public class DirectorVo {
    @ApiModelProperty(value = "服务主管名称")
    private String directorName;
    @ApiModelProperty(value = "服务主管手机号")
    private String director;
}
