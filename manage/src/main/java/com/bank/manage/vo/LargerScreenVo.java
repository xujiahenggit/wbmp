package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: cq
 * @Date: 2020/7/10 22:50
 * (t_repair)
 */
@Data
@ApiModel
public class LargerScreenVo {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "设备名称")
    private String equipmentName;
    @ApiModelProperty(value = "终端编号")
    private String terminalCode;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "机构号")
    private String  orgId ;
}
