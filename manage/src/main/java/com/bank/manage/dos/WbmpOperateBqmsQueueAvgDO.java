package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: Andy
 * @Date: 2020/6/12 16:46
 */
@TableName("wbmp_operate_bqms_queue_avg")
@ApiModel("客户满意度")
@Data
public class WbmpOperateBqmsQueueAvgDO extends Model<WbmpOperateBqmsQueueAvgDO> {
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
     * 平均等待时长-秒
     */
    private BigDecimal indexCnt;

    /**
     * 平均弃号率
     */
    private BigDecimal avgAbandonedLv;

    /**
     * 弃号率
     */
    private BigDecimal abandonedLv;

    /**
     * 数据日期
     */
    private String dataDt;
}
