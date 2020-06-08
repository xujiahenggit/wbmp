package com.bank.user.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.aspectj.weaver.ast.Or;

import java.time.LocalDateTime;

/**
 * @Author: Andy
 * @Date: 2020/4/1 16:31
 */
@ApiModel("组织机构信息")
@Data
@TableName("T_ORGANIZATION")
public class OrganizationDO extends Model<OrganizationDO> {
    /**
     * 组织机构ID
     */
    @TableId(value = "ORG_ID", type = IdType.UUID)
    @ApiModelProperty(value = "组织机构ID")
    private String orgId;

    /**
     * 组织机构名称
     */
    @ApiModelProperty(value = "组织机构名称")
    private String orgName;

    /**
     * 父机构ID
     */
    @ApiModelProperty(value = "父机构ID")
    private String parentId;

    /**
     * 父机构名称
     */
    @ApiModelProperty(value = "父机构名称")
    private String parentName;

    /**
     * 单位ID
     */
    @ApiModelProperty(value = "单位ID")
    private String orgUnitId;

    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称")
    private String orgUnitName;

    /**
     * 部门ID
     */
    @ApiModelProperty(value = "部门ID")
    private String orgDepartId;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    private String orgDepartName;

    /**
     * 机构性质
     */
    @ApiModelProperty(value = "机构性质")
    private String orgType;

    /**
     * 核心机构号
     */
    @ApiModelProperty(value = "核心机构号")
    private String orgCode;

    /**
     * 营业状态
     */
    @ApiModelProperty(value = "营业状态")
    private String orgStatus;

    /**
     * 委托机构编号
     */
    @ApiModelProperty(value = "委托机构编号")
    private String orgTrustId;

    /**
     * 末级标识
     */
    @ApiModelProperty(value = "末级标识")
    private String orgLastflag;

    /**
     * 机构等级
     */
    @ApiModelProperty(value = "机构等级")
    private String orgLevel;

}
