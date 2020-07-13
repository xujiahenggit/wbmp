package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: cq
 * @Date: 2020/5/8 22:50
 * 服务信息查询
 */
@Data
@ApiModel
public class ServiceInformationsVo {
    @ApiModelProperty(value = "厂商")
    private String deviceVendor;
    @ApiModelProperty(value = "服务主管")
    private String director;
    @ApiModelProperty(value = "服务工程师")
    private String engineer;
    @ApiModelProperty(value = "处理类型（1：远程处理  2：现场处理）")
    private String dealType;
    @ApiModelProperty(value = "巡检开始时间")
    private Date escortsStartTime;
    @ApiModelProperty(value = "巡检结束时间")
    private Date escortsCompleteTime;
    @ApiModelProperty(value = "工单完成时间")
    private Date workOrderCompleteTime;

}

