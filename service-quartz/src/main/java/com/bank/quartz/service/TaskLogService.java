package com.bank.quartz.service;

import com.bank.quartz.dos.TaskLogDO;
import com.bank.quartz.vo.TaskLogVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author: Andy
 * @Date: 2020/4/10 10:59
 */
public interface TaskLogService extends IService<TaskLogDO> {
    /**
     * 多维度 查询 定时任务日志列表
     * @param taskLogVo
     * @return
     */
    IPage<TaskLogDO> getLogPage(TaskLogVo taskLogVo);
}
