package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 原监控平台 支行机构表
 * </p>
 * @author
 * @since 2020-7-27
 */
@Data
@Builder
@TableName("subbranch")
public class SubBranchDO implements Serializable {

    /**
     * 主键ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 支行编号
     */
    private String strsubbranchnum;

    /**
     * 支行名称
     */
    private String strsubbranchname;

    /**
     * 分行编号
     */
    private String strbranchnum;

    /**
     * 银行编号
     */
    private String strbanknum;

    /**
     * 组织机构号
     */
    private String orgCode;

    /**
     * 是否虚拟机构：1 是，0 否；
     */
    private String virtualOrg;
}
