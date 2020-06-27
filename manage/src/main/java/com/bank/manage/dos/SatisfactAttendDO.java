package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author: Andy
 * @Date: 2020/5/29 21:04
 */
@TableName("T_SATISFACT_ATTEND")
@ApiModel("满意度月度考核主表")
@Data
public class SatisfactAttendDO extends Model<SatisfactAttendDO> {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "SATISFACT_ATTEND_ID", type = IdType.AUTO)
    private Integer satisfactAttendId;

    /**
     * 引导员ID
     */
    private Integer usherId;

    /**
     * 考核年月
     */
    private LocalDate satisfactAttendYear;

    /**
     * 考核得分
     */
    private Integer satisfactAttendScore;

    /**
     * 提交状态 默认10；10：未提交；20：已提交
     */
    private String satisfactAttendSubmitState;

    /**
     * 提交人
     */
    private String satisfactAttendSubmitUserid;

    /**
     * 提交人姓名
     */
    private String satisfactAttendSubmitUsername;

    /**
     * 提交时间
     */
    private LocalDateTime satisfactAttendSubmitTime;

    /**
     * 银行主管评语
     */
    private String satisfactAttendRemark;
}
