package com.bank.icop.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
@ApiModel(description = "协查任务基本信息查询接口")
public class TaskListsDto implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("预警编号")
    private String alertkey;
    @ApiModelProperty("用户编号")
    private String userNo;
}
