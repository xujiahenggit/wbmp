package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 首页设备运行状态
 * 
 * @author
 * @date 2020-7-27
 */
@Data
@ApiModel(description = "首页设备运行状态")
public class HomePageTermStatusDTO implements Serializable {

    private static final long serialVersionUID = -4429750708816450034L;

    @ApiModelProperty(value = "在线数")
    private Integer onlinenum;

    @ApiModelProperty(value = "离线数")
    private Integer offlinenum;

    @ApiModelProperty(value = "故障数")
    private Integer faultnum;

    @ApiModelProperty(value = "维护数")
    private Integer maintainnum;

    @ApiModelProperty(value = "总数")
    private Integer totalnum;
}
