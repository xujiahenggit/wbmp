package com.bank.esb.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: cq
 * @Date: 2020/7/2
 */
@ApiModel(description = "设备详细Vo")
@Data
public class EquipmentDetailVo {

    @ApiModelProperty(value = "终端编号 ")
    private  String deviceId;

}
