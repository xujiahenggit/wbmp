package com.bank.icop.service;

import com.bank.icop.dos.*;

public interface EarlyWarnMonitorService {
    Object getdealDatas(String userNo);

    Object getT69Alerts(String alertkey);

    Object getTaskDetails(String taskkey);

    Object modifyTasks(ModifyTasksDo modifyTasksDo);

    Object deleteTasks(String taskkey);

    Object returnTasks(ReturnTasksDo returnTasksDo);

    Object addTaskDatas(AddTaskDatasDo addTaskDatasDo);

    Object queryT69alertlogs(String alertkey);

    Object queryRuleDatas(String tplakey);

    Object fileremoveRisk(FilereMoveRiskDo filereMoveRiskDo);

    Object fileRiskEvent(FileRiskEventDo fileRiskEventDo);

    Object queryTaskLists(String alertkey);

    Object updateDealRecords(UpdateDealRecordsDo updateDealRecordsDo);

    Object deleteDealRecords(String invtkey);

    Object submitTasks(SubmitTasksDo submitTasksDo);

    Object getT69AlertList(String userNo);

    Object getReplyDataList(String userNo);

    Object getNotReplyLists(String userNo);

    Object returnAlerts(String alertkey);

    Object returnReplyLists(String taskkey);

    Object getAlertLogLists(String alertkey);

    Object getTplakeyLists(String tplakey);

    Object returnNotReplyLists(String taskkey);
}
