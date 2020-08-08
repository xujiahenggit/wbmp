package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: zhangfuqiang
 * @Date: 2020/7/17 22:50
 * 自助设备模块名称
 */
@Data
@ApiModel
public class SsarunDeviceModelVo {

    @ApiModelProperty(value = "模块id")
    private String id;

    @ApiModelProperty(value = "虚模块名称")
    private String dcname;

    @ApiModelProperty(value = "模块classid")
    private String classid;

    @ApiModelProperty(value = "虚模块描述")
    private String dcdesc;

    @ApiModelProperty(value = "虚模块版本号")
    private String versionnum;

}
