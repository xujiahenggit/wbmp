package com.bank.icop.dos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
@ApiModel(description = "处理方式为登记风险事件的归档模型")
public class FileRiskEventDo implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("预警编号")
    private String alertkey;
    @ApiModelProperty("风险事件编号")
    private String mistakeno;
    @ApiModelProperty("风险事件类型")
    private String mistaketype;
    @ApiModelProperty("风险事件选项")
    private String mistakeitem;
    @ApiModelProperty("风险度")
    private String risk;
    @ApiModelProperty("风险事件说明")
    private String mistakedesc;
    @ApiModelProperty("协查行")
    private String sendorg;
    @ApiModelProperty("风险事件责任人")
    private String mistaketlr;

}
