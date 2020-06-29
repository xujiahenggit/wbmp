package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: Andy
 * @Date: 2020/6/12 16:51
 */
@TableName("wbmp_operate_cust")
@ApiModel("客户指标")
@Data
public class WbmpOperateCustDO extends Model<WbmpOperateCustDO> {
    private static final long serialVersionUID = 1L;

    /**
     * 加工时间
     */
    private String etlTime;

    /**
     * 机构号
     */
    private String orgId;


    /**
     * 指标编号 CUST_001-普通客户数,CUST_002-金卡客户数,CUST_003-白金客户数，CUST_004-钻石客户数
     */
    private String indexNo;

    /**
     * 指标名称
     */
    private String indexName;

    /**
     * 本期值
     */
    private BigDecimal indexVal;

    /**
     * 比上日
     */
    private BigDecimal compLastd;

    /**
     * 比上月
     */
    private BigDecimal compLastm;

    /**
     * 比上季
     */
    private BigDecimal compLastq;

    /**
     * 比年初
     */
    private BigDecimal compLasty;

    /**
     * 数据日期
     */
    private String dataDt;
}
