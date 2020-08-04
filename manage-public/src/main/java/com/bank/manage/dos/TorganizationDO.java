package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 组织机构信息表
 * </p>
 * @author
 * @since 2020-7-10
 */
@Data
@Builder
@TableName("t_organization")
public class TorganizationDO implements Serializable {

    /**
     * 组织机构ID
     */
    @TableId(value = "ORG_ID", type = IdType.INPUT)
    private String orgId;

    /**
     * 组织机构名称
     */
    private String orgName;

    /**
     * 父机构ID
     */
    private String parentId;

    /**
     * 父机构名称
     */
    private String parentName;

    /**
     * 单位ID
     */
    private String orgUnitId;

    /**
     * 单位名称
     */
    private String ORG_UNIT_NAME;

    /**
     * 部门ID
     */
    private String orgDepartId;

    /**
     * 部门名称
     */
    private String orgDepartName;

    /**
     * 核心机构号
     */
    private String orgCode;

    /**
     * 机构性质
     */
    private String orgType;

    /**
     * 营业状态
     */
    private String orgStatus;

    /**
     * 委托机构编号
     */
    private String orgTrustId;

    /**
     * 末级标识
     */
    private String orgLastflag;

    /**
     * 机构等级
     */
    private String orgLevel;
}
