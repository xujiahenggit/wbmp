package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: Andy
 * @Date: 2020/6/17 10:17
 */
@Data
@ApiModel(value = "柜面交易")
@TableName("wbmp_abs_transinfo")
public class WbmpAbsTransinfoDO extends Model<WbmpAbsTransinfoDO> {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    /**
     * 机构号
     */
    private String orgId;

    /**
     * 柜员号
     */
    private String tellerId;

    /**
     * 柜员名称
     */
    private String tellerName;

    /**
     * 交易码
     */
    private String tranCode;

    /**
     * 交易名称
     */
    private String tranName;

    /**
     * 交易类型
     */
    private String tranType;

    /**
     * 交易量
     */
    private BigDecimal tradeVolume;

    /**
     * 维度标志 1-机构交易总数，2-机构业务维度，3-柜员总交易，4-柜员业务员维度
     */
    private String flag;

}
