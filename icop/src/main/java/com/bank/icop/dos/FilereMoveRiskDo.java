package com.bank.icop.dos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
@ApiModel(description = "处理方式为排除的归档模型")
public class FilereMoveRiskDo implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("预警编号")
    private String alertkey;
    @ApiModelProperty("排除原因")
    private String obviatereason;
    @ApiModelProperty("备注")
    private String comments;

}
