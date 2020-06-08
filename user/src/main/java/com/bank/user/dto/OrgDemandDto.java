package com.bank.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/6/6 16:06
 */
@Data
@ApiModel(value = "按需加载")
public class OrgDemandDto implements Serializable {

    @ApiModelProperty(value = "组织机构ID")
    private String orgId;

    /**
     * 组织机构名称
     */
    @ApiModelProperty(value = "组织机构名称")
    private String orgName;


    /**
     * 末级标识
     */
    @ApiModelProperty(value = "末级标识",notes = "如果是0 有下属机构 1：表示无下属机构")
    private String orgLastflag;
}
