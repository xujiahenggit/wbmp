package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 终端设备详情列表
 *
 * @author
 * @date 2020-7-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "终端设备详情列表")
public class DeviceTermDTO implements Serializable {

    private static final long serialVersionUID = 7040388659064871233L;

    //终端表
    @ApiModelProperty(value = "主键自增ID")
    private Integer id;

    @ApiModelProperty(value = "终端编号")
    private String strtermnum;

    @ApiModelProperty(value = "上送终端号")
    private String aptlid;

    @ApiModelProperty(value = "网络地址")
    private String strnetaddr;

    @ApiModelProperty(value = "设备ID")
    private String deviceid;

    @ApiModelProperty(value = "终端地址")
    private String strtermaddr;
    //使用状态；待复核 0，复核不通过 1，待审批 2，审批通过 3，审批不通过 4，调试 5，暂停 6，启用 7，停用 8，撤销 9，默认为0
    @ApiModelProperty(value = "使用状态")
    private Integer usingstatus;

    //设备表
    @ApiModelProperty(value = "设备序列号")
    private String strdevsn;

    @ApiModelProperty(value = "设备厂商")
    private String strdevmanu;

    //设备类型：自动取款机 1，自动存款机 2，自动存取款机 3，自动查询机 4
    @ApiModelProperty(value = "设备类型")
    private Integer idevtype;

    //设备分类：现金自助0、非现金自助1、快柜设备2
    @ApiModelProperty(value = "设备分类")
    private Integer idevclass;
    //终端状态表
    // 无效-1,空闲: 0,交易中 : 1,硬件故障停止服务 : 2,管理命令暂停服务 : 3,维护中 : 4,通讯中断: 5,耗材尽停止服务: 6,已关机 : 7,正在重新启动 : 8
    @ApiModelProperty(value = "终端状态")
    private Integer svcstatus;
}
