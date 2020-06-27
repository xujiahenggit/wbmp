package com.bank.quartz.job;

import com.bank.core.sysConst.NewProcessStatusFile;
import com.bank.manage.dos.PartorlProcessDO;
import com.bank.manage.dos.PartorlRecordDO;
import com.bank.manage.service.PartorlProcessService;
import com.bank.quartz.dos.TaskLogDO;
import com.bank.quartz.service.TaskLogService;
import com.bank.quartz.utils.GetTaskLogModel;
import com.bank.user.dos.OrganizationDO;
import com.bank.user.dto.OrgNftDto;
import com.bank.user.service.NfrtOrgService;
import com.bank.user.service.OrganizationService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 大堂经理巡查考核生成 定时任务
 * cron 表达式 工作日 每天8：00
 * 0 0 8 ? * 2,3,4,5,6 *
 * @Author: Andy
 * @Date: 2020/5/30 17:56
 */
public class PartorlRecordJob implements Job {

    @Autowired
    private TaskLogService taskLogService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private PartorlProcessService partorlProcessService;

    @Autowired
    private NfrtOrgService nfrtOrgService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        //系统当前时间
        LocalDateTime localDateTime=LocalDateTime.now();
        //开始时间
        long start=System.currentTimeMillis();
        try{
            //所有网点列表
            //List<OrganizationDO> listOrg=organizationService.getOpenOrgList();
            List<OrgNftDto> listOrg=nfrtOrgService.getAllOutletsList();
            //待审核列表
            List<PartorlProcessDO> listProcess=new ArrayList<>();
            if(listOrg.size()>0){
                for (OrgNftDto item:listOrg){
                    for(int i=1;i<=2;i++){
                        PartorlProcessDO partorlProcessDO=new PartorlProcessDO();
                        //待办日期
                        partorlProcessDO.setPartorlProcessDate(LocalDate.now());
                        //待办机构
                        partorlProcessDO.setPartorlProcessOrgId(item.getOrgId());
                        //待办机构名称
                        partorlProcessDO.setPartorlProcessOrgName(item.getOrgName());
                        //序号
                        partorlProcessDO.setPartorlProcessNum(String.valueOf(i));
                        //默认为 未办
                        partorlProcessDO.setPartorlProcessState(NewProcessStatusFile.PROCESS_WAIT);
                        listProcess.add(partorlProcessDO);
                    }
                }
                partorlProcessService.saveBatch(listProcess);
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
