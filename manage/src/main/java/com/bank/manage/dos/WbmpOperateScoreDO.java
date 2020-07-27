package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @Author: Andy
 * @Date: 2020/6/19 9:31
 */
@Data
@TableName("WBMP_OPERATE_SCORE")
@ApiModel("经营分数")
public class WbmpOperateScoreDO extends Model<WbmpOperateScoreDO> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "WBMP_OPERATE_SCORE_ID", type = IdType.AUTO)
    @ApiModelProperty(value = "ID")
    private Long wbmpOperateScoreId;

    /**
     * 机构号
     */
    @ApiModelProperty(value = "机构号")
    private String orgId;

    /**
     * 机构名称
     */
    @ApiModelProperty(value = "机构名称")
    private String orgName;

    /**
     * 得分
     */
    @ApiModelProperty(value = "得分")

    private Float operateScore;

    /**
     * 日期
     */
    @ApiModelProperty(value = "日期")
    private LocalDate operateDate;
}
