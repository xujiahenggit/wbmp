package com.bank.esb.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: cq
 * @Date: 2020/7/2
 */
@ApiModel(description = "设备详细Dto")
@Data
public class EquipmentDetailDto {
    @ApiModelProperty(value = "终端编号")
    private String deviceId;

    @ApiModelProperty(value = "设备型号")
    private String deviceModel;

    @ApiModelProperty(value = "机构号")
    private String orgId;

    @ApiModelProperty(value = "机构名称")
    private String orgName;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "安装日期")
    private Date installDate;

    @ApiModelProperty(value = "设备服务商")
    private String deviceVendor;

    @ApiModelProperty(value = "服务主管集合")
    private List<ServiceSupervisorDto> serviceSupervisorDtoList;


}
