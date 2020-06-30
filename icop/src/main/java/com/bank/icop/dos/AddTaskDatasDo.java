package com.bank.icop.dos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
@ApiModel(description = "协查组任务数据新增接口模型")
public class AddTaskDatasDo implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("任务编号")
    private String taskkey;
    @ApiModelProperty("任务名称")
    private String taskname;
    @ApiModelProperty("反馈日期")
    private String feedbackdt;
    @ApiModelProperty("主查组")
    private String dealgroup;
    @ApiModelProperty("任务级别")
    private String deallev;
    @ApiModelProperty("描述信息")
    private String taskdesc;

}
