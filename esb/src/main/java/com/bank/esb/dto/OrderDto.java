package com.bank.esb.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: cq
 * @Date: 2020/7/2
 */
@ApiModel(description = "工单Dto")
@Data
public class OrderDto {

    @ApiModelProperty(value = "工单状态")
    private String orderStatus;

    @ApiModelProperty(value = "工单号")
    private String orderNo;

    @ApiModelProperty(value = "工单创建人")
    private String createUser;

    @ApiModelProperty(value = "工单创建时间")
    private Date createTime;

    @ApiModelProperty(value = "优先级")
    private String priority;

    @ApiModelProperty(value = "机构号")
    private String orgId;

    @ApiModelProperty(value = "机构名称")
    private String orgName;

    @ApiModelProperty(value = "设备终端号")
    private String deviceNo;

    @ApiModelProperty(value = "设备类型")
    private String deviceType;

    @ApiModelProperty(value = "设备名称")
    private String deviceName;

    @ApiModelProperty(value = "设备品牌")
    private String deviceBrand;

    @ApiModelProperty(value = "工单描述")
    private String orderDetail;


}
