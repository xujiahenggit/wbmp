package com.bank.icop.service;


import java.util.List;
import java.util.Map;

/**
 * SOAP调用第三方接口日志 服务类
 *
 * @author zhaozhongyuan
 * @since 2020-06-09
 */
public interface OnSiteInspectionService {


    List<Map> inspectionTaskList(String userId);

    List<Map> taskItemList(String userId, String taskId, String createOrgId, String executeOrgId, String taskName, String taskStartDate, String taskEndDate);

    Object registerCheck(String currentUserId, String taskItemId, String inspectionInfoId);

    boolean check(String taskItemId, String inspectionInfoId);

    Object problemEdit(String pk, String taskpk);

    Object checkTaskSubmit(String currentUserId, String pk);
}
