package com.bank.quartz.job;

import com.bank.quartz.dos.TaskLogDO;
import com.bank.quartz.service.TaskLogService;
import com.bank.quartz.utils.GetTaskLogModel;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 运营视图 历史数据删除 保存定时任务
 * 任务名称： 运营视图历史数据保存
 * 定时任务类：  com.bank.quartz.job.OperateHistoryDataSaveJob
 * 定时任务组：   历史数据保存
 * Corn表达式：   0 0 23 1 1/1 ? （每晚23点执行）
 * 定时任务备注： 运营视图数据 保存
 * 定时任务状态： 已发布
 */
public class OperateHistoryDataSaveJob implements Job {

    //系统当前时间
    LocalDateTime localDateTime=LocalDateTime.now();
    //开始时间
    long start=System.currentTimeMillis();

    @Resource
    private TaskLogService taskLogService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try{
            //这里调用 具体的 清数据 保存历史数据的 service

            //记录日志 运行成功
            //14.保存日志
            TaskLogDO taskLogDO = GetTaskLogModel.getModel(jobExecutionContext, "1" , localDateTime, System.currentTimeMillis() - start, null);
            taskLogService.save(taskLogDO);
        }catch (Exception e){
            //记录日志  运行失败
            TaskLogDO taskLogDO = GetTaskLogModel.getModel(jobExecutionContext, "2" , localDateTime, System.currentTimeMillis() - start, e.fillInStackTrace());
            taskLogService.save(taskLogDO);
        }
    }
}
