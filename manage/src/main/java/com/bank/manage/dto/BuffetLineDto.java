package com.bank.manage.dto;

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
public class BuffetLineDto {
    @ApiModelProperty(value = "分行编码")
    private String branchCode;
    @ApiModelProperty(value = "支行编码")
    private String subbranchCode;

}
