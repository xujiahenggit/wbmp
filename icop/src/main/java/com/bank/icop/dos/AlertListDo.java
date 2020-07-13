package com.bank.icop.dos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(description = "预警发起与识别数据列表接口10022")
public class AlertListDo implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("用户编号")
    private String userNo;
    @ApiModelProperty("预警编号")
    private String alertKey;
    @ApiModelProperty("预警开始时间")
    private Date startDt;
    @ApiModelProperty("预警结束时间")
    private Date endDt;
    @ApiModelProperty("风险度")
    private String risklev;
    @ApiModelProperty("状态")
    private String dealflag;
    @ApiModelProperty("客户经理号")
    private String fcettypecode;
    @ApiModelProperty("抽检状态")
    private String cjstatus;

}
