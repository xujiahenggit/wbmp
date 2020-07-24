package com.bank.quartz.job;

import com.bank.manage.service.WbmpDataClearService;
import com.bank.quartz.dos.TaskLogDO;
import com.bank.quartz.service.TaskLogService;
import com.bank.quartz.utils.GetTaskLogModel;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;
import java.time.LocalDate;
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
@Slf4j
public class OperateHistoryDataSaveJob implements Job {

    //系统当前时间
    LocalDateTime localDateTime=LocalDateTime.now();
    //开始时间
    long start=System.currentTimeMillis();

    @Resource
    private TaskLogService taskLogService;

    @Resource
    private WbmpDataClearService wbmpDataClearService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try{
            //这里调用 具体的 清数据 保存历史数据的 service
            String now = LocalDate.now().toString();
            log.info("******************日期"+now+"开始清理数据*********************");
            Boolean flag = false;
            //一天前
            String oneDate = LocalDate.now().minusDays(1).toString();
            //三天前
            String threeDate = LocalDate.now().minusDays(3).toString();
            //上个月
            String oneMonth = LocalDate.now().minusMonths(1).toString();
            //三个月
            String threeMonth = LocalDate.now().minusMonths(3).toString();
            //六个月
            String sixMonth = LocalDate.now().minusMonths(6).toString();
            //一年前
            String oneYear = LocalDate.now().minusYears(1).toString();
            //三年前
            String threeYear = LocalDate.now().minusYears(1).toString();

            //备份wbmp_abs_transinfo  date-上一日的日期【柜面交易明细表1】
            wbmpDataClearService.bakWbmpAbsTransinfo(oneDate);
            //删除上一日的数据 date-上一日的日期 【柜面交易明细表2】
            wbmpDataClearService.deleteWbmpAbsTransinfo(oneDate);
            //删除历史表小于一个月前的数据 date-上一日的日期 【柜面交易明细表3】
            wbmpDataClearService.deleteWbmpAbsTransinfoH(oneMonth);

            //删除wbmp_abs_teller_online_time 3天前的数据【柜员在线时长信息表】
            wbmpDataClearService.deleteWbmpAbsTellerOnlineTime(threeDate);

            //删除wbmp_abs_trade_time 3天前的数据【柜员交易耗时表】
            wbmpDataClearService.deleteWbmpAbsTradeTime(threeDate);

            //备份wbmp_abs_transinfo  date-上一日的日期【自助设备交易表1】
            wbmpDataClearService.bakWbmpAtmpTranInfo(oneDate);

            //删除上一日的数据 date-上一日的日期 【自助设备交易表2】
            wbmpDataClearService.deleteWbmpAtmpTranInfo(oneDate);

            //删除历史表小于一年前的数据 date-上一年的数据 【自助设备交易表3】
            wbmpDataClearService.deleteWbmpAtmpTranInfoH(oneYear);

            //删除上一日的数据 date-上一日的日期 【排队机】
            wbmpDataClearService.deleteWbmpBqmsQueue(threeDate);

            //删除三个月前的数据 date-三月前的日期 【客户满意度参数表T+1】
            wbmpDataClearService.deleteWbmpOperateBqmsQueueAvg(threeMonth);

            //删除上个月的数据 date-上个月的日期 【网点AUM表T+1】
            wbmpDataClearService.deleteWbmpOperateIndexAum(threeMonth);

            //删除三个月前的数据 date-三月前的日期 【赛马制表T+1】
            wbmpDataClearService.deleteWbmpOperateRacingIndexM(sixMonth);

            //删除三天前的数据 date-三天前的日期 【网点实时余额】
            wbmpDataClearService.deleteWbmpOrgBalance(oneMonth);

            //删除一个月前的数据 date-一月前的日期 【网点历史离线余额】
            wbmpDataClearService.deleteWbmpOrgBatchBalance(sixMonth);


            //备份wbmp_operate_cust  date-上一月的日期【客群指标表 T+1】1
            wbmpDataClearService.bakWbmpOperateCust(oneMonth);

            //删除wbmp_operate_cust  date-上一月的日期【客群指标表 T+1】2
            wbmpDataClearService.deleteWbmpOperateCust(oneMonth);

            //删除wbmp_operate_cust_h  date-三年前的日期【客群指标表 T+1】3
            wbmpDataClearService.deleteWbmpOperateCustH(threeYear);
            log.info("******************日期"+now+"结束清理数据*********************");

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
