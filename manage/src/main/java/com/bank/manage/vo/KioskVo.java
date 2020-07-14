package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: cq
 * @Date: 2020/5/8 22:50
 * 自助设备列表查询
 */
@Data
@ApiModel
public class KioskVo {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "终端编号")
    private String terminalCode;
    @ApiModelProperty(value = "设备名称 自动取款机 1，自动存款机 2，自动存取款机 3，自动查询机 4")
    private String equipmentName;
    @ApiModelProperty(value = "状态 待签收设备 0，库存设备 1，停用未回库设备 2，拒收设备 3，上线设备 4，已报废设备 5")
    private String status;
    @ApiModelProperty(value = "地址")
    private String address;

}
