package com.bank.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/4/9 18:41
 */
@ApiModel("组织机构 添加更新时用")
@Data
public class OrgVo implements Serializable {
    /**
     * 机构ID
     */
    @ApiModelProperty(value = "机构ID 更新时 必填")
    private String orgId;

    /**
     * 组织机构名称
     */
    @ApiModelProperty("组织机构名称")
    private String orgName;

    /**
     * 父机构ID
     */
    @ApiModelProperty("父机构ID")
    private String parentId;

    /**
     * 机构性质
     */
    @ApiModelProperty("机构性质")
    private String orgType;

    /**
     * 营业状态
     */
    @ApiModelProperty("营业状态")
    private String orgStatus;

    /**
     * 委托机构编号
     */
    @ApiModelProperty("委托机构编号")
    private String orgTrustId;

    /**
     * 机构地址
     */
    @ApiModelProperty("机构地址")
    private String orgAddress;

    /**
     * 末级标识
     */
    @ApiModelProperty("末级标识")
    private String orgLastflag;

    /**
     * 机构电话
     */
    @ApiModelProperty("机构电话")
    private String orgPhone;

    /**
     * 机构等级
     */
    @ApiModelProperty("机构等级")
    private String orgLevel;
}
