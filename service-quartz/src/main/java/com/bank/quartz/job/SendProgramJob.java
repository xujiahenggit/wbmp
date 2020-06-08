package com.bank.quartz.job;

import com.alibaba.fastjson.JSON;
import com.bank.core.utils.ConfigFileReader;
import com.bank.core.utils.HttpUtil;
import com.bank.manage.service.PlayAreaMaterialService;
import com.bank.manage.util.ProgramUtil;
import com.bank.manage.vo.ProgramVo;
import com.bank.quartz.dos.TaskLogDO;
import com.bank.quartz.service.TaskLogService;
import com.bank.quartz.utils.GetTaskLogModel;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
public class SendProgramJob implements Job {

    @Resource
    private TaskLogService taskLogService;

    @Autowired
    private PlayAreaMaterialService playAreaMaterialService;

    @Resource
    private ConfigFileReader configFileReader;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        LocalDateTime localDateTime = LocalDateTime.now();
        long start = System.currentTimeMillis();
        try {
            sendProgram();
            TaskLogDO taskLogDO = GetTaskLogModel.getModel(jobExecutionContext, "1", localDateTime, System.currentTimeMillis() - start, null);
            taskLogService.save(taskLogDO);
        } catch (Exception e) {
            log.info("任务执行失败");
            TaskLogDO taskLogDO = GetTaskLogModel.getModel(jobExecutionContext, "2", localDateTime, System.currentTimeMillis() - start, e.fillInStackTrace());
            taskLogService.save(taskLogDO);
        }
    }

    private void sendProgram() {
        List<ProgramVo> programVoList = playAreaMaterialService.queryProgramList();
        log.info("{}",programVoList);
        List<Map<String,Object>> listMap= ProgramUtil.getProgramMap(programVoList,configFileReader.getHTTP_PATH());
        for (Map<String,Object> item:listMap ) {
            HttpUtil.sendPost(configFileReader.getMESSAGE_PATH(),item);
            //System.out.println(JSON.toJSON(item));
            log.info("发送的节目信息：{}",item);
        }
    }
}
