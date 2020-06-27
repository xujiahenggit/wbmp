package com.bank.manage.dos;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: Andy
 * @Date: 2020/6/22 10:29
 */
@Data
@ApiModel("赛马制")
public class WbmpOperateRacingIndexMDO extends Model<WbmpOperateRacingIndexMDO> {
    private static final long serialVersionUID = 1L;

    /**
     * 加工日期
     */
    private String etlTime;

    /**
     * 机构号
     */
    private String orgId;

    /**
     * 指标编号
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
     * 数据日期 yyyyMM
     */
    private String dataDt;
}
