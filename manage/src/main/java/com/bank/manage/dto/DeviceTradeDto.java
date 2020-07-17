package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: zhangfuqiang
 * @Date: 2020/7/17 16:50
 * 自助设备列表查询
 */
@Data
@ApiModel
public class DeviceTradeDto {


    @ApiModelProperty(value = "机构号")
    private String orgId;

    @ApiModelProperty(value ="终端编号")
    private String termNum ;

    @ApiModelProperty(value ="查询条件 按月-01、按年-02")
    private String  queryType ;

}
