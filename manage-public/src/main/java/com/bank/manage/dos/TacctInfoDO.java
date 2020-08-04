package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 帐号及帐号参数表
 * </p>
 * @author
 * @since 2020-7-8
 */
@Data
@Builder
@TableName("t_acct_info")
public class TacctInfoDO implements Serializable {

    private static final long serialVersionUID = -6230379347979513456L;

    /**
     * 内部编号 主键自增ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 账户
     */
    private String acctno;

    /**
     * 账户名
     */
    private String acctna;

    /**
     * 客户号（10位）
     */
    private String custno;

    /**
     * 币种
     */
    private String crcycd;

    /**
     * 子户号
     */
    private String subsac;

    /**
     * 科目号
     */
    private String itemcd;

    /**
     * 机构号
     */
    private String brchno;

    /**
     * 开户日期
     */
    private LocalDateTime opendt;

    /**
     * 睡眠户标记
     */
    private String sleptg;

    /**
     * 明细科目代码
     */
    private String dtitcd;

    /**
     * 账户状态
     */
    private String acctst;

    /**
     * 重点账户标志；1是0否
     */
    private String flagKeyacct;

    /**
     * 不对账账户标志；1是0否
     */
    private String flagNotaccounting;

    /**
     * 二代支付标志；1是0否
     */
    private String flagPaymengt;

    /**
     * 对账机构
     */
    private String branchAccounting;

    /**
     * 是否纸质对账单绑定
     */
    private String flagBind;

    /**
     * 对账模式：1集中对账0是自行对账
     */
    private String model;

    /**
     * 更新柜员
     */
    private String tellerUpdate;

    /**
     * 更新时间
     */
    private LocalDateTime timeUpdate;

    /**
     * 备用1
     */
    private String col1;

    /**
     * 备用2
     */
    private String col2;

    /**
     * 备用3
     */
    private String col3;
}
