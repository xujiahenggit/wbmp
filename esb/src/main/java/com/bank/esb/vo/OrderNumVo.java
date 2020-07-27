package com.bank.esb.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
/**
 * @Author: cq
 * @Date: 2020/7/2
 */
@ApiModel(description = "工单Vo")
@Data
public class OrderNumVo {
    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "工单类型")
    private String orderType;

    @ApiModelProperty(value = "设备分类")
    private String deviceClassify;

    @ApiModelProperty(value = "设备类型")
    private String deviceType;

    @ApiModelProperty(value = "设备终端号")
    private String deviceNo;

    @ApiModelProperty(value = "工单号")
    private String orderNo;

    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "工单状态")
    private String orderStatus;

    @ApiModelProperty(value = "与我相关")
    private String related;

    @ApiModelProperty(value = "分页大小")
    private int pageSize;

    @ApiModelProperty(value = "第几页")
    private int pageIndex;
}
