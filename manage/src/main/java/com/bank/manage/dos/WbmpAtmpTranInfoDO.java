package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author: Andy
 * @Date: 2020/6/17 10:17
 */
@Data
@ApiModel(value = "自助交易")
@TableName("wbmp_atmp_tran_info")
public class WbmpAtmpTranInfoDO extends Model<WbmpAtmpTranInfoDO> {
    private static final long serialVersionUID = 1L;

    /**
     * 机构号
      */
    private String orgId;
    /**
     * 交易码
     */
    private String termNo;

    /**
     * 交易名称
     */
    private String transName;

    /**
     * 交易数量
     */
    private Integer transCnt;

    /**
     * 日期
     */
    private String dataDt;
}
