package com.bank.manage.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author: Andy
 * @Date: 2020/5/28 10:55
 */
@ApiModel("外包人员补卡申请")
@Data
public class CardSuppleDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @ApiModelProperty("编号")
    @TableId(value = "CARD_SUPPLE_ID", type = IdType.AUTO)
    private Integer cardSuppleId;

    /**
     * 引导员ID
     */
    @ApiModelProperty("引导员ID")
    private String usherId;

    /**
     * 引导员姓名
     */
    @ApiModelProperty("引导员姓名")
    private String usherName;

    /**
     * 状态 10：待审核；20：审核通过；30：驳回；
     */
    @ApiModelProperty(value = "状态 10：待审核；20：审核通过；30：驳回；")
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
    private LocalDateTime cardSuppleStartWorkTime;

    /**
     * 补卡下班时间
     */
    @ApiModelProperty(value = "补卡下班时间")
    private LocalDateTime cardSuppleEndWorkTime;

    /**
     * 补卡理由
     */
    @ApiModelProperty(value = "补卡理由")
    private String cardSuppleResion;

    /**
     * 图片
     */
    @ApiModelProperty(value = "图片")
    private String cardSuppleImg;

    /**
     * 审批人工号
     */
    @ApiModelProperty(value = "审批人工号")
    private String cardSuppleProcessUserId;

    /**
     * 审批人姓名
     */
    @ApiModelProperty(value = "审批人姓名")
    private String cardSuppleProcessUserName;

    /**
     * 申请时间
     */
    @ApiModelProperty(value = "申请时间")
    private LocalDateTime cardSuppleCreatetime;

    /**
     * 审批时间
     */
    @ApiModelProperty(value = "审批时间")
    private LocalDateTime cardSuppleProcessTime;

    /**
     * 驳回原因
     */
    @ApiModelProperty(value = "驳回原因")
    private String cardSuppleRejectResion;

    /**
     * 删除标识 默认为 0  0：未删除 1：已删除
     */
    @ApiModelProperty(value = "删除标识 默认为 0  0：未删除 1：已删除")
    private String cardSuppleDeleteFlag;
}
