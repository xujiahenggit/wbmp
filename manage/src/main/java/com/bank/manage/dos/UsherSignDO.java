package com.bank.manage.dos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * 引导员签到
 * ClassName: UsherSignDO
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/04/23 11:10:13
 */
@Data
@TableName("T_USHER_SIGN")
public class UsherSignDO extends Model<UsherSignDO> {

    /**
    *
    */
    private static final long serialVersionUID = 4527383766669700543L;

    /**
     *主键
     */
    @TableId(value = "USHER_SIGN_ID", type = IdType.AUTO)
    private Integer usherSignId;

    /**
     * 引导员ID
     */
    private Integer usherId;

    /**
     * 签到日期
     */
    private Date signDate;

    /**
     * 签到状态
     */
    private String signStatus;

    /**
     * 上班时间
     */
    private Date onDutyTime;

    /**
     * 下班时间
     */
    private Date offDutyTime;

    /**
     * 工时
     */
    private BigDecimal workingHours;

}
