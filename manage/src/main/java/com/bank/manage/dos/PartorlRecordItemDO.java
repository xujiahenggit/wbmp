package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Andy
 * @Date: 2020/5/12 14:57
 */
@Data
@TableName("T_PARTORL_RECORD_ITEM")
@ApiModel("巡查记录内容")
public class PartorlRecordItemDO extends Model<PartorlRecordItemDO> {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    @TableId(value = "PARTORL_RECORD_ITEM_ID", type = IdType.AUTO)
    private Integer partorlRecordItemId;

    /**
     * 巡查记录主表主键
     */
    @ApiModelProperty(value = "巡查记录ID")
    private Integer partorlRecordId;

    /**
     * 巡查内容表主键
     */
    @ApiModelProperty(value = "巡查内容ID")
    private Integer partorlContentId;

    /**
     * 巡查结果 0：是；1：否
     */
    @ApiModelProperty(value = "巡查结果 0：是；1：否")
    private String partorlResult;

    /**
     * 巡查备注
     */
    @ApiModelProperty(value = "巡查备注")
    private String partorlMark;
}
