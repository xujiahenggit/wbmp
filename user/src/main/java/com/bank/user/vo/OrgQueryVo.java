package com.bank.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/4/9 18:40
 */
@ApiModel("组织机构 查询用")
@Data
public class OrgQueryVo implements Serializable {
    @ApiModelProperty("第几页：默认第一页")
    private Integer pageIndex = 1;

    @ApiModelProperty("分页大小：默认10行")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "机构ID")
    private String orgId;

    @ApiModelProperty("组织机构名称")
    private String orgName;

    @ApiModelProperty("父机构ID")
    private String parentId;

    @ApiModelProperty("机构等级")
    private String orgLevel;
}
