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
@ApiModel("自助行信息")
public class BuffetLineVo {
    @ApiModelProperty(value = "自助行编码")
    private String buffetLineCode;
    @ApiModelProperty(value = "自助行名称")
    private String buffetLineName;
}
