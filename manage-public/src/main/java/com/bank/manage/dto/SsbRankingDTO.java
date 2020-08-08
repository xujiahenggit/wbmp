package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 首页当月网点业务量排行榜
 * 
 * @author
 * @date 2020-7-28
 */
@Data
@ApiModel(description = "首页当月网点业务量排行榜")
public class SsbRankingDTO implements Serializable {

    private static final long serialVersionUID = -7750319982838332612L;

    @ApiModelProperty(value = "自助银行名称")
    private String strssbname;

    @ApiModelProperty(value = "自助银行编号")
    private String strssbnum;

    @ApiModelProperty(value = "交易量")
    private String countnum;
}
