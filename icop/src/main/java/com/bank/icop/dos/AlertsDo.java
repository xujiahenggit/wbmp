package com.bank.icop.dos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "预警任务基本信息查询接口10002")
public class AlertsDo {
    @ApiModelProperty("任务编号")
    private String userNo;
    @ApiModelProperty("预警编号")
    private String alertKey;
}
