package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: cq
 * @Date: 2020/5/8 22:50
 * (t_repair)
 */
@Data
@ApiModel
public class InspectionEquipmentVo {

    @ApiModelProperty(value = "终端编号")
    private String terminalCode;
    @ApiModelProperty(value = "巡检标识 1:已巡检 0：未巡检")
    private String logo;
    @ApiModelProperty(value = "序列号")
    private String sequence;
    @ApiModelProperty(value = "设备类型")
    private String equipmentType;
    @ApiModelProperty(value = "自助银行名称")
    private String name;
}
