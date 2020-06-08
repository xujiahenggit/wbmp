package com.bank.quartz.utils;

import com.bank.quartz.dos.TaskLogDO;
import org.quartz.JobExecutionContext;

import java.time.LocalDateTime;

/**
 * @Author: Andy
 * @Date: 2020/4/10 11:01
 */
public class GetTaskLogModel {
    /**
     * 组件任务执行 日志model
     * @param jobExecutionContext
     * @param state 状态 1：执行成功 2：异常
     * @param time 执行时间
     * @param consumTime 耗时
     * @param throwable 错误日志
     * @return
     */
    public static TaskLogDO getModel(JobExecutionContext jobExecutionContext, String state, LocalDateTime time,long consumTime,Throwable throwable) {
        TaskLogDO taskLogDO = new TaskLogDO();
        //设置任务组
        taskLogDO.setTasklogGroup(jobExecutionContext.getJobDetail().getKey().getGroup());
        //设置任务名称
        taskLogDO.setTasklogName(jobExecutionContext.getJobDetail().getKey().getName());
        //设置Cron 表达式
        //设置执行状态
        taskLogDO.setTasklogState(state);
        //设置执行时间
        taskLogDO.setTasklogTime(time);
        //设置耗时
        taskLogDO.setTasklogConsumTime(consumTime);
        //设置 异常信息
        if(throwable!=null){
            taskLogDO.setTasklogErrorInfo(stackTraceToString(throwable.getClass().getName(), throwable.getMessage(), throwable.getStackTrace()));
        }
        return taskLogDO;
    }

    /**
     * 异常信息 转化
     * @param exceptionName 异常名称
     * @param exceptionMessage 异常信息
     * @param elements 异常栈
     * @return
     */
    private static String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
        StringBuffer strbuff = new StringBuffer();
        for (StackTraceElement stet : elements) {
            strbuff.append(stet + "\n");
        }
        String message = exceptionName + ":" + exceptionMessage + "\n\t" + strbuff.toString();
        return message;
    }

}
