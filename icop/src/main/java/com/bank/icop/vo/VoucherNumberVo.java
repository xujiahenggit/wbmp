package com.bank.icop.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "凭证号段查看返回模块信息")
public class VoucherNumberVo implements Serializable {

    private static final long serialVersionUID = 1334887238528866112L;

    @ApiModelProperty(value = "凭证起始号码  ")
    private String startNo;

    @ApiModelProperty(value = "凭证结束号码  ")
    private String endNo;

    @ApiModelProperty(value = "数量  ")
    private Integer num;

}
