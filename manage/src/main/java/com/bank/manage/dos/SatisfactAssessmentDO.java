package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author: Andy
 * @Date: 2020/5/29 21:01
 */
@TableName("T_SATISFACT_ASSESSMENT")
@Data
@ApiModel("满意度月度考核指标")
public class SatisfactAssessmentDO extends Model<SatisfactAssessmentDO> {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "SATISFACT_ASSESSMENT_ID", type = IdType.AUTO)
    private Integer satisfactAssessmentId;

    /**
     * 一级指标 为0表示一级指标
     */
    private Integer satisfactAssessmentTopid;

    /**
     * 考核指标描述
     */
    private String satisfactAssessmentDisc;

    /**
     * 考核指标分值
     */
    private Integer satisfactAssessmentScore;

    /**
     * 考核指标内容
     */
    private String satisfactAssessmentContent;

    /**
     * 考核评分标准
     */
    private String satisfactAssessmentStandart;
}
