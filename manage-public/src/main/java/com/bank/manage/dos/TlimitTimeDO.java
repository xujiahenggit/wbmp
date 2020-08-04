package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 逾期催收时限表
 * </p>
 * @author
 * @since 2020-7-8
 */
@Data
@Builder
@TableName("t_limittime")
    public class TlimitTimeDO implements Serializable {

    /**
     * 主键自增ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 机构号
     */
    private String branch;

    /**
     * 催收时限(天)
     */
    private String timeLimit;

    /**
     * 频次
     */
    private String frequency;

    /**
     * 录入柜员
     */
    private String tellerInsert;

    /**
     * 录入时间
     */
    private LocalDateTime timeInsert;

    /**
     * 更新柜员
     */
    private String tellerUpdate;

    /**
     * 更新时间
     */
    private LocalDateTime timeUpdate;

    /**
     * 状态0-启用1-停用
     */
    private String status;

}
