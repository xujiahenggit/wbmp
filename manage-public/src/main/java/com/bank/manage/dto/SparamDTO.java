package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "回单参数管理")
public class SparamDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键自增ID")
    private Integer id;

    @ApiModelProperty(value = "参数英文名")
    private String ename;

    @ApiModelProperty(value = "参数名称")
    private String cname;

    @ApiModelProperty(value = "参数值")
    private String value;

    @ApiModelProperty(value = "修改状态")
    private char statusUpdate;

    @ApiModelProperty(value = "渠道号")
    private String chanlid;

    @ApiModelProperty(value = "启用状态")
    private char status;
}
