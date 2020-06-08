package com.bank.manage.dos;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 机构引导员人数控制-数据库映射实体
 * ClassName: UsherPopulationDO
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/04/22 15:23:51
 */
@Data
@TableName("T_USHER_POPULATION")
public class UsherPopulationDO implements Serializable {

    /**
    *
    */
    private static final long serialVersionUID = -7009673080386709133L;

    /**
     *主键
     */
    @TableId(value = "USHER_POPULATION_ID", type = IdType.AUTO)
    private Integer usherPopulationId;

    /**
     * 机构编号
     */
    private String orgId;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 人数控制
     */
    private Integer populationLimit;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 修改人
     */
    private String updatedBy;

    /**
     * 修改时间
     */
    private Date updatedTime;
}
