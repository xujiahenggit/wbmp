package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 原监控平台 分行机构表
 * </p>
 * @author
 * @since 2020-7-27
 */
@Data
@Builder
@TableName("branch")
public class BranchDO implements Serializable {

    /**
     * 主键ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 分行编号
     */
    private String strbranchnum;

    /**
     * 分行名称
     */
    private String strbranchname;

    /**
     * 银行编号
     */
    private String strbanknum;

    /**
     * 组织机构号
     */
    private String orgCode;
}
