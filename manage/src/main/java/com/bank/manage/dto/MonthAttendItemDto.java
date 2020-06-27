package com.bank.manage.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/5/30 16:38
 */
@Data
@ApiModel("月度考勤审批")
public class MonthAttendItemDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Integer monthAttendItemId;

    /**
     * 月度考勤数据确认主表ID
     */
    @ApiModelProperty("月度考勤数据确认主表ID")
    private Integer monthAttendId;

    /**
     * 引导员ID
     */
    @ApiModelProperty("引导员ID")
    private Integer usherId;

    /**
     *引导员姓名
     */
    @ApiModelProperty("引导员姓名")
    private String usherName;

    /**
     * 满意度考核得分
     */
    @ApiModelProperty("满意度考核得分")
    private Integer monthAttendScore;

    /**
     * 应出勤天数
     */
    @ApiModelProperty("应出勤天数")
    private Integer monthAttendMustDays;

    /**
     * 出勤天数
     */
    @ApiModelProperty("出勤天数")
    private Integer monthAttendRealDays;

    /**
     * 工作日加班
     */
    @ApiModelProperty("工作日加班")
    private Float monthAttendWorkLength;

    /**
     * 法定节假日加班
     */
    @ApiModelProperty("法定节假日加班")
    private Float monthAttendRestLenght;
}
