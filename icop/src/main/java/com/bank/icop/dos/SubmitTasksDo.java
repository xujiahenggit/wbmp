package com.bank.icop.dos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
@ApiModel(description = "协查组任务调查提交模型")
public class SubmitTasksDo implements Serializable {
    private static final long serialVersionUID = 1L;
//    @ApiModelProperty("任务编码")
//    private String taskkey;
//    @ApiModelProperty("任务的当前处理状态")
//    private String dealflag;
//    @ApiModelProperty("委派人")
//    private String wpuser;
//    @ApiModelProperty("归档时间")
//    private String filetime;

    @ApiModelProperty("任务编码")
    private String taskkey;
    @ApiModelProperty("任务的当前处理状态")
    private String userNo;

}
