package com.bank.esb.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: cq
 * @Date: 2020/7/2
 */
@ApiModel(description = "机构列表Dto")
@Data
public class InstitutionsDto {

    @ApiModelProperty(value = "机构号")
    private String orgId;

    @ApiModelProperty(value = "机构名称")
    private String orgName;

}
