package com.bank.quartz.job;

import com.bank.manage.dos.WbmpMangementScoreDO;
import com.bank.manage.dos.WbmpOperateScoreDO;
import com.bank.manage.service.OperateCurveService;
import com.bank.manage.service.WbmpOperateScoreService;
import com.bank.quartz.dos.TaskLogDO;
import com.bank.quartz.service.TaskLogService;
import com.bank.quartz.utils.GetTaskLogModel;
import com.bank.user.dto.OrgNftDto;
import com.bank.user.service.NfrtOrgService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 网点综合排名 定时任务
 * @Author: Andy
 * @Date: 2020/6/19 14:44
 */
public class OperateManageJob implements Job {

    @Resource
    private NfrtOrgService nfrtOrgService;

    @Resource
    private TaskLogService taskLogService;

    @Resource
    private WbmpOperateScoreService wbmpOperateScoreService;

    @Resource
    private OperateCurveService operateCurveService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext){
        //系统当前时间
        LocalDateTime localDateTime=LocalDateTime.now();
        //开始时间
        long start=System.currentTimeMillis();
        try{
            //定时 时间 为当前时间-1天
            String date= LocalDate.now().minusDays(1).toString();
            //获取所有的网点列表
            List<OrgNftDto> listOrg=nfrtOrgService.getAllOutletsList();
            /**
             * 经营列表
             */
            List<WbmpMangementScoreDO> listManagement=new ArrayList<>();
            /**
             * 运营列表
             */
            List<WbmpOperateScoreDO> listOperate=new ArrayList<>();
            //每个网点 获取
            for (OrgNftDto item:listOrg){
                // 根据时间和机构号 来查询 经营分数
                float manageScore=operateCurveService.calcOrgMonthScore(item.getOrgId(),date);
                //经营得分模型
                WbmpMangementScoreDO wbmpMangementScoreDO=new WbmpMangementScoreDO();
                //设置日期
                wbmpMangementScoreDO.setManagementDate(LocalDate.parse(date));
                //设置机构号
                wbmpMangementScoreDO.setOrgId(item.getOrgId());
                //设置机构名称
                wbmpMangementScoreDO.setOrgName(item.getOrgName());
                //设置分数
                wbmpMangementScoreDO.setManagementScore(manageScore);

                listManagement.add(wbmpMangementScoreDO);

                //根据时间和机构号 查询运营分数
                float operateScore=wbmpOperateScoreService.calOperScore(item.getOrgId(),date);
                //运营得分模型
                WbmpOperateScoreDO wbmpOperateScoreDO=new WbmpOperateScoreDO();
                //设置时间
                wbmpOperateScoreDO.setOperateDate(LocalDate.parse(date));
                //设置机构号
                wbmpOperateScoreDO.setOrgId(item.getOrgId());
                //设置机构名称
                wbmpOperateScoreDO.setOrgName(item.getOrgName());
                //设置分数
                wbmpOperateScoreDO.setOperateScore(operateScore);

                listOperate.add(wbmpOperateScoreDO);

            }
            wbmpOperateScoreService.saveScore(listManagement,listOperate);
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
