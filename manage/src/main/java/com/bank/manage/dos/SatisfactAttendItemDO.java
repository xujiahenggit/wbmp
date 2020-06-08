package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author: Andy
 * @Date: 2020/5/29 21:06
 */
@TableName("T_SATISFACT_ATTEND_ITEM")
@Data
@ApiModel("满意度月度考核从表")
public class SatisfactAttendItemDO extends Model<SatisfactAttendItemDO> {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "SATISFACT_ATTEND_ITEM_ID", type = IdType.AUTO)
    private Integer satisfactAttendItemId;

    /**
     * 满意度月度考核主表主键
     */
    private Integer satisfactAttendId;

    /**
     * 一级指标ID
     */
    private Integer satisfactAssessmentFirstId;

    /**
     * 二级指标ID
     */
    private Integer satisfactAssessmentSecondId;

    /**
     * 一级指标得分
     */
    private Integer satisfactAssessmentFirstCore;

    /**
     * 二级指标得分
     */
    private Integer satisfactAssessmentSecondScore;

    /**
     * 备注
     */
    private String satisfactAttendItemRemark;
}
