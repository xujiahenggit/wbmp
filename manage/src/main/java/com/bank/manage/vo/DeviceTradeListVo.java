package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: zhangfuqiang
 * @Date: 2020/6/16 11:13
 */
@Data
@ApiModel(value = "自助设备交易趋势查询用")
public class DeviceTradeListVo implements Serializable {

    @ApiModelProperty(value = "机构编号")
    private String orgId;

    @ApiModelProperty(value = "查询类型 按月-01，按年-02")
    private String queryType;

    @ApiModelProperty(value = "设备终端编号")
    private String termNo;
}
