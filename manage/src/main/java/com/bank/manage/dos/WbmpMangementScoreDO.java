package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @Author: Andy
 * @Date: 2020/6/19 9:30
 */
@TableName("WBMP_MANGEMENT_SCORE")
@Data
@ApiModel("运营分数表")
public class WbmpMangementScoreDO extends Model<WbmpMangementScoreDO> {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "WBMP_MANAGEMENT_ID", type = IdType.AUTO)
    @ApiModelProperty(value = "ID")
    private Integer wbmpManagementId;

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
     * 分数
     */
    @ApiModelProperty(value = "分数")
    private Float managementScore;

    /**
     * 日期
     */
    @ApiModelProperty(value = "日期")
    private LocalDate managementDate;
}
