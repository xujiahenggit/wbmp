package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/4/22 10:59
 */
@ApiModel("驳回审批")
@Data
public class NewProcessRejectVo implements Serializable {
    @ApiModelProperty("流程编号")
    @NotNull(message = "流程编号不能为空")
    private Integer processId;

    @ApiModelProperty("驳回理由")
    @NotBlank(message = "驳回理由不能为空")
    private String rejectReason;
}
