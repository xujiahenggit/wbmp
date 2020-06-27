package com.bank.quartz.job;

import cn.hutool.core.date.DateUtil;
import com.bank.core.sysConst.NewProcessStatusFile;
import com.bank.core.utils.DateUtils;
import com.bank.manage.dos.SatisfactAttendDO;
import com.bank.manage.dos.UsherDO;
import com.bank.manage.service.SatisfactAttendService;
import com.bank.manage.service.UsherService;
import com.bank.quartz.dos.TaskLogDO;
import com.bank.quartz.service.TaskLogService;
import com.bank.quartz.utils.GetTaskLogModel;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 生成 满意度 考核列表生成
 * cron 表达式 每月 1号 早上8：00
 * 0 0 8 1 1/1 ?
 * @Author: Andy
 * @Date: 2020/5/30 17:16
 */
public class SatisfactAttendJob implements Job {

    @Autowired
    private SatisfactAttendService satisfactAttendService;

    @Autowired
    private UsherService usherService;

    @Autowired
    private TaskLogService taskLogService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        //系统当前时间
        LocalDateTime localDateTime=LocalDateTime.now();
        //开始时间
        long start=System.currentTimeMillis();
        try{
            List<SatisfactAttendDO> listattend=new ArrayList<>();
            List<UsherDO> listUsher=usherService.SelectUseFullUsher();
            if(listUsher.size()>0){
                for (UsherDO usherDO:listUsher){
                    SatisfactAttendDO satisfactAttendDO=new SatisfactAttendDO();
                    //设置引导员编号
                    satisfactAttendDO.setUsherId(usherDO.getUsherId());
                    //设置考核年份
                    satisfactAttendDO.setSatisfactAttendYear(LocalDate.now().minusMonths(1));
                    //考核得分 默认满分 100
                    satisfactAttendDO.setSatisfactAttendScore(100);
                    //默认状态为 未提交 10
                    satisfactAttendDO.setSatisfactAttendSubmitState(NewProcessStatusFile.PROCESS_WAIT);
                    //创建时间
                    satisfactAttendDO.setSatisfactAttendSubmitTime(LocalDateTime.now());

                    listattend.add(satisfactAttendDO);
                }
                satisfactAttendService.saveBatch(listattend);
            }
            //14.保存日志
            TaskLogDO taskLogDO = GetTaskLogModel.getModel(jobExecutionContext, "1" , localDateTime, System.currentTimeMillis() - start, null);
            taskLogService.save(taskLogDO);
        }catch (Exception e){
            //这里记录错误日志
            TaskLogDO taskLogDO = GetTaskLogModel.getModel(jobExecutionContext, "2" , localDateTime, System.currentTimeMillis() - start, e.fillInStackTrace());
            taskLogService.save(taskLogDO);
        }
    }
}
