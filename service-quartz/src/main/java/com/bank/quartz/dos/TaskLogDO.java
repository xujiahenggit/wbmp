package com.bank.quartz.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: Andy
 * @Date: 2020/4/10 10:57
 */
@Data
@TableName("T_TASKLOG")
public class TaskLogDO extends Model<TaskLogDO> {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "TASKLOG_ID", type = IdType.AUTO)
    private Integer tasklogId;

    /**
     * 任务组
     */
    private String tasklogGroup;

    /**
     * 任务名称
     */
    private String tasklogName;

    /**
     * 任务状态 1:执行成功 2：执行失败
     */
    private String tasklogState;

    /**
     * 执行时间
     */
    private LocalDateTime tasklogTime;

    /**
     * 执行总耗时
     */
    private Long tasklogConsumTime;

    /**
     * 异常信息
     */
    private String tasklogErrorInfo;
}
