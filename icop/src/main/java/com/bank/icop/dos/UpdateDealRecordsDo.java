package com.bank.icop.dos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
@ApiModel(description = "处理记录修改模型")
public class UpdateDealRecordsDo implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("调查编码")
    private String invtkey;
    @ApiModelProperty("调查主题")
    private String topic;
    @ApiModelProperty("调查人")
    private String creator;
    @ApiModelProperty("被调查对象")
    private String beinvted;
    @ApiModelProperty("调查结论")
    private String verdict;
    @ApiModelProperty("任务编号")
    private String taskkey;
    @ApiModelProperty("过程描述")
    private String processdes;

}
