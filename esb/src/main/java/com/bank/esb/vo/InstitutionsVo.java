package com.bank.esb.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: cq
 * @Date: 2020/7/2
 */
@ApiModel(description = "机构列表Vo")
@Data
public class InstitutionsVo {
    @ApiModelProperty(value = "机构号")
    private String orgId;

}
