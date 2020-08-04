package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 组织机构信息表
 *
 * @author
 * @date 2020-7-8
 */
@Data
@ApiModel(description = "组织机构信息")
public class TorganizationDTO implements Serializable {


    @ApiModelProperty(value = "组织机构ID")
    private String orgId;

    @ApiModelProperty(value = "组织机构名称")
    private String orgName;
}
