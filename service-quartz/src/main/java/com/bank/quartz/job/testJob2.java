package com.bank.quartz.job;

import com.bank.quartz.dao.TaskLogDao;
import com.bank.quartz.dos.TaskLogDO;
import com.bank.quartz.service.TaskLogService;
import com.bank.quartz.utils.GetTaskLogModel;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @Author: Andy
 * @Date: 2020/4/8 10:25
 */
@Slf4j
public class testJob2 implements Job {
    @Resource
    private TaskLogService taskLogService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext){
        LocalDateTime localDateTime = LocalDateTime.now();
        long start = System.currentTimeMillis();
        try {
            log.info("任务2 执行了 "+localDateTime);
            TaskLogDO taskLogDO = GetTaskLogModel.getModel(jobExecutionContext, "1", localDateTime, System.currentTimeMillis() - start, null);
            taskLogService.save(taskLogDO);
        } catch (Exception e) {
            log.info("任务执行失败");
            TaskLogDO taskLogDO = GetTaskLogModel.getModel(jobExecutionContext, "2", localDateTime, System.currentTimeMillis() - start, e.fillInStackTrace());
            taskLogService.save(taskLogDO);
        }
    }
}
