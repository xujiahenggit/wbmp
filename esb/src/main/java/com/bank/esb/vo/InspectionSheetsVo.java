package com.bank.esb.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: cq
 * @Date: 2020/7/2
 */
@ApiModel(description = "巡检单创建查询Vo")
@Data
public class InspectionSheetsVo {

    @ApiModelProperty(value = "工单号")
    private String orderNo;

    @ApiModelProperty(value = "设备终端号")
    private String deviceNo;

    @ApiModelProperty(value = "陪同人编号")
    private String accompany;

    @ApiModelProperty(value = "巡检开始时间")
    private String startTime;

    @ApiModelProperty(value = "巡检结束时间")
    private String endTime;

    @ApiModelProperty(value = "处理方式1-机芯清洁和校准；" +
            "2-清洁各打印机内灰尘及纸屑；" +
            "3-检查各打印机色带是否到了需要更换程度；\n" +
            "4-清洁读卡器灰尘；\n" +
            "5-清洁机构内部灰尘；\n" +
            "6-更换磨损的皮带、吸咀和橡胶轮；\n" +
            "7-利用后台维护终端进行系统测试；\n" +
            "8客户屏幕显示器清洁；\n" +
            "9-其他")
    private String processMode;

    @ApiModelProperty(value = "工单描述")
    private String orderDescribe;

    @ApiModelProperty(value = "创建人编号")
    private String createUserId;

    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;

    @ApiModelProperty(value = "创建人电话")
    private String createUserPhone;

    @ApiModelProperty(value = "现场联系人姓名")
    private String sceneUserName;

    @ApiModelProperty(value = "现场联系人电话")
    private String sceneUserPhone;

}
