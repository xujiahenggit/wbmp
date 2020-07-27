package com.bank.icop.dos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "协查组任务数据修改模型")
public class ModifyTasksDo implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty("任务编号")
    private String taskkey;
    @ApiModelProperty("任务名称")
    private String taskname;
    @ApiModelProperty("任务级别")
    private String deallev;
    @ApiModelProperty("描述信息")
    private String taskdesc;
    @ApiModelProperty("处理机构")
    private String dealgroup;


}
