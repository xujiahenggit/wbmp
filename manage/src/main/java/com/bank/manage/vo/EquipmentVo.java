package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: cq
 * @Date: 2020/5/8 22:50
 * (t_repair)
 */
@Data
@ApiModel
public class EquipmentVo {
    @ApiModelProperty(value = "终端编号")
    private String terminalCode;
    @ApiModelProperty(value = "设备类型")
    private String equipmentType;
    @ApiModelProperty(value = "分行机构")
    private String branchOrgId;
    @ApiModelProperty(value = "支行机构")
    private String subbranchOrgId;
    @ApiModelProperty(value = "详细地址")
    private String address;
    @ApiModelProperty(value = "安装日期")
    private Date installationTime;
}
