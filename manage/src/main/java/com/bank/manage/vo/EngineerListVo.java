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
public class EngineerListVo {

    @ApiModelProperty(value = "服务工程师名称")
    private String engineerName;
    @ApiModelProperty(value = "服务工程师手机号")
    private String engineer;
}
