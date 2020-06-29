package com.bank.user.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Andy
 * @Date: 2020/6/10 17:36
 */
@Data
@ApiModel("分支行网点用")
public class OrgNftDto implements Serializable {

    /**
     * 机构号  分支行时 显示核心机构号  网点时 显示 人力资源机构号
     */
    @ApiModelProperty("支行时 显示支行机构号  网点时 显示网点机构号")
    private String orgId;

    /**
     * 机构名称
     */
    @ApiModelProperty("机构名称")
    private String orgName;

    /**
     * 类型
     */
    @ApiModelProperty("类型 网点列表时 无返回")
    private String type;

    @ApiModelProperty("核心机构号")
    private String orgCode;
}
