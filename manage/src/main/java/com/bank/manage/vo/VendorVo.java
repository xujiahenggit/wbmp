package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: cq
 * @Date: 2020/5/8 22:50
 * 厂商信息
 */
@Data
@ApiModel
public class VendorVo {
    @ApiModelProperty(value = "厂商id")
    private String vendorId;
    @ApiModelProperty(value = "厂商姓名")
    private String vendorName;
}
