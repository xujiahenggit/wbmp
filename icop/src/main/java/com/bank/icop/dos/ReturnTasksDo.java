package com.bank.icop.dos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
@ApiModel(description = "协查组任务退回模型")
public class ReturnTasksDo implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(" 任务编号")
    private String taskkey;
    @ApiModelProperty("用户编号")
    private String userNo;
    @ApiModelProperty("预警编号")
    private String alertKey;
    @ApiModelProperty("退回意见说明")
    private String opinion;

}
