package com.bank.quartz.job;

import com.bank.core.utils.ConfigFileReader;
import com.bank.quartz.dos.TaskLogDO;
import com.bank.quartz.service.TaskLogService;
import com.bank.quartz.utils.GetTaskLogModel;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: Andy
 * @Date: 2020/4/2 12:54
 * 一个测试任务
 */
@Slf4j
public class testJob implements Job {

    @Autowired
    private TaskLogService taskLogService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        LocalDateTime localDateTime = LocalDateTime.now();
        long start = System.currentTimeMillis();
        try {
            TaskLogDO taskLogDO = GetTaskLogModel.getModel(jobExecutionContext, "1" , localDateTime, System.currentTimeMillis() - start, null);
            taskLogService.save(taskLogDO);
        } catch (Exception e) {
            log.info("任务执行失败");
            TaskLogDO taskLogDO =GetTaskLogModel.getModel(jobExecutionContext, "2" , localDateTime, System.currentTimeMillis() - start, e.fillInStackTrace());
            taskLogService.save(taskLogDO);
        }
    }
}
