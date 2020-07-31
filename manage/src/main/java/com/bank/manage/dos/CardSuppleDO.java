package com.bank.manage.dos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Andy
 * @Date: 2020/5/28 10:41
 */
@Data
@ApiModel(value = "外包人员补卡申请")
@TableName("T_CARD_SUPPLE")
public class CardSuppleDO extends Model<CardSuppleDO> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "CARD_SUPPLE_ID", type = IdType.AUTO)
    @ApiModelProperty(value = "编号", notes = "新增时不用传")
    private Integer cardSuppleId;

    /**
     * 引导员ID
     */
    @ApiModelProperty(value = "引导员ID", required = true)
    private String usherId;

    /**
     * 状态 10：待审核；20：审核通过；30：驳回；
     */
    @ApiModelProperty(value = "状态 10：待审核；20：审核通过；30：驳回；", notes = "新增不需要传")
    private String cardSuppleState;

    /**
     * 补卡日期
     */
    @ApiModelProperty(value = "补卡日期")
    private LocalDate cardSuppleDate;

    /**
     * 补卡上班时间
     */
    @ApiModelProperty(value = "补卡上班时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime cardSuppleStartWorkTime;

    /**
     * 补卡下班时间
     */
    @ApiModelProperty(value = "补卡下班时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime cardSuppleEndWorkTime;

    /**
     * 补卡理由
     */
    @ApiModelProperty(value = "补卡理由", required = true)
    private String cardSuppleResion;

    /**
     * 图片
     */
    @ApiModelProperty(value = "图片路径", required = true)
    private String cardSuppleImg;

    /**
     * 审批人工号
     */
    @ApiModelProperty(value = "审批人工号", notes = "新增不需传")
    private String cardSuppleProcessUserId;

    /**
     * 审批人姓名
     */
    @ApiModelProperty(value = "审批人姓名", notes = "新增不需传")
    private String cardSuppleProcessUserName;

    /**
     * 申请时间
     */
    @ApiModelProperty(value = "申请时间", notes = "新增不需传")
    private LocalDateTime cardSuppleCreatetime;

    /**
     * 审批时间
     */
    @ApiModelProperty(value = "审批时间", notes = "新增不需传")
    private LocalDateTime cardSuppleProcessTime;

    /**
     * 驳回原因
     */
    @ApiModelProperty(value = "驳回原因", notes = "新增不需传")
    private String cardSuppleRejectResion;

    /**
     * 删除标识 默认为 0  0：未删除 1：已删除
     */
    @ApiModelProperty(value = "删除标识 默认为 0  0：未删除 1：已删除", notes = "新增不需传")
    private String cardSuppleDeleteFlag;
}
