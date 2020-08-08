package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 首页月度业务量统计
 * 
 * @author
 * @date 2020-7-28
 */
@Data
@ApiModel(description = "首页月度业务量统计")
public class SsbTransRankBarDTO implements Serializable {

    private static final long serialVersionUID = 778250537521594107L;

    @ApiModelProperty(value = "1月")
    private String month1;

    @ApiModelProperty(value = "2月")
    private String month2;

    @ApiModelProperty(value = "3月")
    private String month3;

    @ApiModelProperty(value = "4月")
    private String month4;

    @ApiModelProperty(value = "5月")
    private String month5;

    @ApiModelProperty(value = "6月")
    private String month6;

    @ApiModelProperty(value = "7月")
    private String month7;

    @ApiModelProperty(value = "8月")
    private String month8;

    @ApiModelProperty(value = "9月")
    private String month9;

    @ApiModelProperty(value = "10月")
    private String month10;

    @ApiModelProperty(value = "11月")
    private String month11;

    @ApiModelProperty(value = "12月")
    private String month12;
}
