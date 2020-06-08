package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 引导员管理-数据库映射实体
 * ClassName: UsherDO
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/04/21 11:30:57
 */
@Data
@TableName("T_USHER")
public class UsherDO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -608688503179846262L;

    /**
     *主键
     */
    @TableId(value = "USHER_ID", type = IdType.AUTO)
    private Integer usherId;

    /**
     *引导员姓名
     */
    private String usherName;

    /**
     * 所属公司名称
     */
    private String companyName;

    /**
     * 所属机构编号
     */
    private String orgId;

    /**
     * 所属机构名称
     */
    private String orgName;

    /**
     * 电话号码
     */
    private String phoneNo;

    /**
     * 身份证号
     */
    private String identityNo;


    /**
     * 性别
     */
    private Integer sex;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 修改人
     */
    private String updatedBy;

    /**
     * 修改时间
     */
    private LocalDateTime updatedTime;

    /**
     * 删除标识
     */
    private String usherDelflag;
}
