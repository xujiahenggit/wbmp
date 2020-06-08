package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author: Andy
 * @Date: 2020/5/29 16:30
 */
@ApiModel("月度考勤数据确认")
@TableName("T_MONTH_ATTEND")
@Data
public class MonthAttendDO extends Model<MonthAttendDO> {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "MONTH_ATTEND_ID", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Integer monthAttendId;

    /**
     * 考勤数据确认年月
     */
    @ApiModelProperty(value = "考勤数据确认年月")
    private LocalDate monthAttendYear;

    /**
     * 机构号
     */
    @ApiModelProperty(value = "机构号")
    private String monthAttendOrgId;

    /**
     * 机构名称
     */
    @ApiModelProperty(value = "机构名称")
    private String monthAttendOrgName;

    /**
     * 状态 10：待审核；20：审核通过；30：驳回；
     */
    @ApiModelProperty(value = "状态 10：待审核；20：审核通过；30：驳回；")
    private String monthAttendState;

    /**
     * 审批人工号
     */
    @ApiModelProperty(value = "审批人工号")
    private String monthAttendProcessUserId;

    /**
     * 审批人姓名
     */
    @ApiModelProperty(value = "审批人姓名")
    private String monthAttendProcessUserName;

    /**
     * 审批时间
     */
    @ApiModelProperty(value = "审批时间")
    private LocalDateTime monthAttendProcessTime;

    /**
     * 驳回原因
     */
    @ApiModelProperty(value = "驳回原因")
    private String monthAttendRejectResion;
}
