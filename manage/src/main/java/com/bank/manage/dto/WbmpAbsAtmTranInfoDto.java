package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/6/17 10:35
 */
@ApiModel(value = "柜面自助交易占比")
@Data
public class WbmpAbsAtmTranInfoDto implements Serializable {
    /**
     * 柜面交易量
     */
    @ApiModelProperty(value = "柜面交易量")
    private float  absTranNum;

    /**
     * 柜面交易占比
     */
    @ApiModelProperty(value = "柜面交易占比")
    private float absTranRate;

    /**
     * 自助交易量
     */
    @ApiModelProperty(value = "自助交易量")
    private float atmTranNum;

    /**
     * 自助交易占比
     */
    @ApiModelProperty(value = "自助交易占比")
    private float atmTranRate;
}
