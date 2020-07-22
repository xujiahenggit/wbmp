package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 服务信息
 * 陈强
 */
@Data
@ApiModel
public class ServiceInfoVo {


    @ApiModelProperty(value = "厂商id")
    private String vendorId;


    @ApiModelProperty(value = "厂商名称")
    private String vendorName;

    @ApiModelProperty(value = "服务主管名称")
    private String directorName;
    @ApiModelProperty(value = "服务主管手机号")
    private String director;
    @ApiModelProperty(value = "服务工程师名称")
    private String engineerName;
    @ApiModelProperty(value = "服务工程师手机号")
    private String engineer;
    @ApiModelProperty(value = "处理类型")
    private String dealType;
    @ApiModelProperty(value = "工单完成时间")
    private String workOrderCompleteTime;

}
