package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/4/22 18:00
 */
@ApiModel("审批通过")
@Data
public class NewProcessPassVo implements Serializable {
    @ApiModelProperty(value = "流程编号")
    @NotNull(message = "流程编号不能为空")
    private Integer processId;

    @ApiModelProperty(value = "素材列表")
    private List<ForcePlayVo> listMaterial;
}
