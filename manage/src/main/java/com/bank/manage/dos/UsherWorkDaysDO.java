package com.bank.manage.dos;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 引导员出勤天数
 * ClassName: UsherWorkDaysDO
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/04/24 10:07:24
 */
@Data
@TableName("T_USHER_WORK_DAYS")
public class UsherWorkDaysDO implements Serializable {

    /**
    *
    */
    private static final long serialVersionUID = -2683220201446584292L;

    /**
     *主键
     */
    @TableId(value = "USHER_WORK_DAYS_ID", type = IdType.AUTO)
    private Integer usherWorkDaysId;

    /**
     * 引导员ID
     */
    private Integer usherId;

    /**
     * 工作年月份
     */
    private String workYearMonth;

    /**
     * 本月出勤天数
     */
    private Integer workDays;

}
