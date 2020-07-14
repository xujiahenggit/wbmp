package com.bank.icop.service;

import com.bank.icop.dos.*;
import com.bank.icop.dto.TaskListsDto;

public interface EarlyWarnMonitorService {
    Object getdealDatas(String userNo);

    Object getT69Alerts(AlertsDo alertsDo);

    Object getTaskDetails(String taskkey);

    Object modifyTasks(ModifyTasksDo modifyTasksDo);

    Object deleteTasks(String taskkey);

    Object returnTasks(ReturnTasksDo returnTasksDo);

    Object addTaskDatas(AddTaskDatasDo addTaskDatasDo);

    Object queryT69alertlogs(String alertkey);

    Object queryRuleDatas(String tplakey);

    Object fileremoveRisk(FilereMoveRiskDo filereMoveRiskDo);

    Object fileRiskEvent(FileRiskEventDo fileRiskEventDo);

    Object queryTaskLists(TaskListsDto taskListsDto);

    Object updateDealRecords(UpdateDealRecordsDo updateDealRecordsDo);

    Object deleteDealRecords(String invtkey);

    Object submitTasks(SubmitTasksDo submitTasksDo);

    Object getT69AlertList(AlertListDo alertListDo);

    Object getReplyDataList(ReplyDataDo replyDataDo);

    Object getNotReplyLists(ReplyDataDo replyDataDo);

    Object returnAlerts(String alertkey);

    Object returnReplyLists(String taskkey);

    Object getAlertLogLists(String alertkey);

    Object getTplakeyLists(String tplakey);

    Object returnNotReplyLists(String taskkey);

    Object returnRoleLists(String userNo);

    Object getTaskByCode(String taskkey);

    Object getUserByNo(String userNo);

    Object getProcessingRecords(ProcessingRecordsDo processingRecordsDo);

    Object getRecordByKey(String taskkey);

    Object getMyTaskByNo(String userNo);

    Object getUpdateByKey(String alertKey);
}
