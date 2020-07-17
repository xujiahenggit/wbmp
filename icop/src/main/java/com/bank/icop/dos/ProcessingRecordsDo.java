package com.bank.icop.dos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "处理记录保存接口10025")
public class ProcessingRecordsDo {
    @ApiModelProperty("被调查对象")
    private String beinvted;
    @ApiModelProperty("创建人")
    private String creator;
    @ApiModelProperty("调查编码")
    private String invtkey;
    @ApiModelProperty("过程描述")
    private String peocessdes;
    @ApiModelProperty("任务编码")
    private String taskkey;
    @ApiModelProperty("调查主题")
    private String topic;
    @ApiModelProperty("调查结论")
    private String verdict;
    @ApiModelProperty("影像id")
    private String CONTENTID;
    @ApiModelProperty("影像时间")
    private String BUSISTARTDATE;
    @ApiModelProperty("影响流水号")
    private String BUSISERIALNO;
}
