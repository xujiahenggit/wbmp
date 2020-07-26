package com.bank.esb.vo;

import cn.hutool.core.date.DateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: cq
 * @Date: 2020/7/2
 */
@ApiModel(description = "巡检单创建查询Vo")
@Data
public class InspectionSheetVo {
    @ApiModelProperty(value = "资源编码")
    private String resourceCode;

    @ApiModelProperty(value = "设备分类")
    private String deviceClassify;

    @ApiModelProperty(value = "设备类型")
    private String deviceType;

    @ApiModelProperty(value = "设备终端号")
    private String deviceNo;

    @ApiModelProperty(value = "序列号")
    private String serialNum;

    @ApiModelProperty(value = "统计时间 1-上季度；2-本季度；3-上个半年；4-本半年")
    private String statisticalTime;

    @ApiModelProperty(value = "开始时间")
    private DateTime startTime;

    @ApiModelProperty(value = "结束时间")
    private DateTime endTime;

    @ApiModelProperty(value = "机构号")
    private String orgId;

    @ApiModelProperty(value = "服务商")
    private String provider;

    @ApiModelProperty(value = "巡检标识 1-已巡检；2-未巡检")
    private String orderStatus;

    @ApiModelProperty(value = "分页大小")
    private int pageSize=10;

    @ApiModelProperty(value = "第几页")
    private int pageIndex=1;
}
