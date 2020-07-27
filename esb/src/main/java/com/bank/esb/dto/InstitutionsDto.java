package com.bank.esb.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: cq
 * @Date: 2020/7/2
 */
@ApiModel(description = "机构列表Dto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstitutionsDto {

    @ApiModelProperty(value = "机构号")
    private String orgId;

    @ApiModelProperty(value = "机构名称")
    private String orgName;

}
