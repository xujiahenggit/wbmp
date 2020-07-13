package com.bank.icop.dos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "已回复协查数据列表接口10023")
public class ReplyDataDo {
    @ApiModelProperty("用户编号")
    private String userNo;
    @ApiModelProperty("预警编号")
    private String alertKey;
    @ApiModelProperty("预警时间")
    private Date alertdt;
    @ApiModelProperty("风险度")
    private String risklev;
    @ApiModelProperty("状态")
    private String dealflag;
    @ApiModelProperty("客户经理号")
    private String fcettypecode;
    @ApiModelProperty("抽检状态")
    private String cjstatus;
}
