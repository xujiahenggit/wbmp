package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 首页今日交易总量
 * 
 * @author
 * @date 2020-7-28
 */
@Data
@ApiModel(description = "首页今日交易总量")
public class HomePageTransHnumDTO implements Serializable {

    private static final long serialVersionUID = 1690410646561259203L;

    @ApiModelProperty(value = "小时数")
    private String hour;

    @ApiModelProperty(value = "当时段交易量")
    private Integer count;
}
