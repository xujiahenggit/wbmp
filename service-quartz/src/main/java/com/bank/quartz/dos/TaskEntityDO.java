package com.bank.quartz.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: Andy
 * @Date: 2020/4/2 9:32
 * 定时任务
 */
@Data
@TableName("S_TASK_ENTITY")
public class TaskEntityDO extends Model<TaskEntityDO> {
    /**
     * 定时任务编号
     */
    @TableId(value = "TASK_ID", type = IdType.AUTO)
    private Integer taskId;

    /**
     * 定时任务名称
     */
    private String taskName;

    /**
     * 定时任务组
     */
    private String taskGroup;

    /**
     * 任务状态 任务状态 默认为 0  0：已发布 1：暂停
     */
    private String taskStatus;

    /**
     * cron表达式
     */
    private String taskCorn;

    /**
     * bean
     */
    private String taskBean;

    /**
     * 备注
     */
    private String taskRemark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 上次执行时间
     */
    private Long taskLastRuntime;

    /**
     * 下次执行时间
     */
    private Long taskNextRuntime;
}
