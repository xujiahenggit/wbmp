package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author cq
 * @date 2020-07-23
 * @email
 */
@Data
@ApiModel("自助行,分行，支行信息")
public class BuffetLineVo {
    @ApiModelProperty(value = "编码")
    private String code;
    @ApiModelProperty(value = "名称")
    private String name;
}
