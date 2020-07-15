package com.bank.esb.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: zzy
 * @Date: 2020/7/15
 */
@ApiModel(description = "厂商信息Dto")
@Data
public class CSInfoDto {
    @ApiModelProperty(value = "编号")
    private String vendorId;

    @ApiModelProperty(value = "名称")
    private String vendorName;
}

