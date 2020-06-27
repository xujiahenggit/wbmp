package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author: Andy
 * @Date: 2020/5/28 16:24
 */
@TableName("T_WORK_SUPPLE")
@Data
@ApiModel("外包人员加班时长申请")
public class WorkSuppleDO extends Model<WorkSuppleDO> {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "WORK_SUPPLE_ID", type = IdType.AUTO)
    @ApiModelProperty(value = "编号")
    private Integer workSuppleId;

    /**
     * 引导员ID
     */
    @ApiModelProperty(value = "引导员ID")
    private String usherId;

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
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String workSuppleState;

    /**
     * 加班时间开始
     */
    @ApiModelProperty(value = "加班时间开始")
    private LocalDateTime workSuppleStarttime;

    /**
     * 加班时间结束
     */
    @ApiModelProperty(value = "加班时间结束")
    private LocalDateTime workSuppleEndtime;

    /**
     * 加班时长
     */
    @ApiModelProperty(value = "加班时长")
    private BigDecimal workSuppleLength;

    /**
     * 加班类型 0：工作日加班；1：节假日加班
     */
    @ApiModelProperty(value = "加班类型 0：工作日加班；1：节假日加班")
    private String workSuppleType;

    /**
     * 图片
     */
    @ApiModelProperty(value = "图片")
    private String workSuppleImg;

    /**
     * 审批人工号
     */
    @ApiModelProperty(value = "审批人工号")
    private String workSuppleProcessUserId;

    /**
     * 审批人姓名
     */
    @ApiModelProperty(value = "审批人姓名")
    private String workSuppleProcessUserName;

    /**
     * 申请时间
     */
    @ApiModelProperty(value = "申请时间")
    private LocalDateTime workSuppleTime;

    /**
     * 审批时间
     */
    @ApiModelProperty(value = "审批时间")
    private LocalDateTime workSuppleProcessTime;

    /**
     * 驳回原因
     */
    @ApiModelProperty(value = "驳回原因")
    private String workSuppleRejectResion;

    /**
     * 删除标识 默认为 0  0：未删除 1：已删除
     */
    @ApiModelProperty(value = "删除标识 默认为 0  0：未删除 1：已删除")
    private String workSuppleDeleteFlag;
}
