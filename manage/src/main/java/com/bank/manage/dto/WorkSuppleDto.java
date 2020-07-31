package com.bank.manage.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Andy
 * @Date: 2020/5/28 16:31
 */
@ApiModel("外包人员加班申请")
@Data
public class WorkSuppleDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "WORK_SUPPLE_ID", type = IdType.AUTO)
    @ApiModelProperty(value = "编号")
    private Long workSuppleId;

    /**
     * 引导员ID
     */
    @ApiModelProperty(value = "引导员ID", notes = "新增时必传")
    private String usherId;

    /**
     * 引导员姓名
     */
    @ApiModelProperty(value = "引导员姓名", notes = "新增时必传")
    private String usherName;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", notes = "新增时不传")
    private String workSuppleState;

    /**
     * 加班日期
     */
    @ApiModelProperty(value = "加班日期")
    private LocalDate workSuppleDate;

    /**
     * 加班理由
     */
    @ApiModelProperty(value = "加班理由")
    private String workSuppleResion;

    /**
     * 加班时间开始
     */
    @ApiModelProperty(value = "加班时间开始", notes = "新增时必传", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime workSuppleStarttime;

    /**
     * 加班时间结束
     */
    @ApiModelProperty(value = "加班时间结束", notes = "新增时必传", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime workSuppleEndtime;

    /**
     * 加班时长
     */
    @ApiModelProperty(value = "加班时长", notes = "新增时不用传")
    private BigDecimal workSuppleLength;

    /**
     * 加班类型 0：工作日加班；1：节假日加班
     */
    @ApiModelProperty(value = "加班类型 0：工作日加班；1：节假日加班", notes = "新增是必传", required = true)
    private String workSuppleType;

    /**
     * 图片
     */
    @ApiModelProperty(value = "图片", notes = "新增是必传", required = true)
    private String workSuppleImg;

    /**
     * 审批人工号
     */
    @ApiModelProperty(value = "审批人工号", notes = "新增时不用传")
    private String workSuppleProcessUserId;

    /**
     * 审批人姓名
     */
    @ApiModelProperty(value = "审批人姓名", notes = "新增时不用传")
    private String workSuppleProcessUserName;

    /**
     * 申请时间
     */
    @ApiModelProperty(value = "申请时间", notes = "新增时不用传")
    private LocalDateTime workSuppleTime;

    /**
     * 审批时间
     */
    @ApiModelProperty(value = "审批时间", notes = "新增时不用传")
    private LocalDateTime workSuppleProcessTime;

    /**
     * 驳回原因
     */
    @ApiModelProperty(value = "驳回原因", notes = "新增时不用传")
    private String workSuppleRejectResion;

    /**
     * 删除标识 默认为 0  0：未删除 1：已删除
     */
    @ApiModelProperty(value = "删除标识 默认为 0  0：未删除 1：已删除", notes = "新增时不用传")
    private String workSuppleDeleteFlag;
}
