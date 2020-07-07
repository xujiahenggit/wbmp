package com.bank.icop.service;

import java.util.List;

import com.bank.icop.dto.CheckItemCheckSubmitDTO;
import com.bank.icop.dto.CheckProblemDTO;
import com.bank.icop.vo.HandledRectifyInfoVO;
import com.bank.icop.vo.HandledRectifyVO;
import com.bank.icop.vo.OnSiteInspectionTaskVO;

/**
 * SOAP调用第三方接口日志 服务类
 *
 * @author zhaozhongyuan
 * @since 2020-06-09
 */
public interface OnSiteInspectionService {

    List<OnSiteInspectionTaskVO> inspectionTaskList(String userId);

    List taskItemList(String userId, String taskId, String createOrgId, String executeOrgId, String taskName, String taskStartDate, String taskEndDate);

    Object registerCheck(String currentUserId, String taskItemId, String inspectionInfoId);

    String check(String taskItemId, String inspectionInfoId);

    Object problemEdit(String pk, String taskpk);

    Object checkTaskSubmit(String currentUserId, String pk);

    Object taskList(String userId, String runorgankey, String taskName, String taskStartDate, String taskEndDate);

    Object checkDetail(String userId, String taskpk);

    Object problemList(String userId, String taskpk);

    Object checkList(String userId);

    Object contentList(String userId, String taskpk);

    Object contentCheck(String pk);

    Object problemView(String sunpointkey, String qpk);

    Object coreCheck(String key);

    Object problemUpdateList(String userId);

    Object problemRectifyInfo(String currentUserId, String key);

    Object feedbackSave(String currentUserId, String key, String feedbackdes);

    Object feedbackSubmit(String currentUserId, String cpk);

    Object childCheck(String currentUserId, CheckItemCheckSubmitDTO checkItemCheckSubmitDTO);

    Object updateCheckList(String userId);

    Object feedbackView(String currentUserId, String cpk);

    Object checkTaskSave(String currentUserId, String jsonstr);

    Object feedbackCheckSubmit(String currentUserId, String cpk, String decision, String approvelog);

    Object checkTaskDetail(String currentUserId, String taskpk);

    Object problemDetail(String pk);

    List<HandledRectifyVO> handledRectifyList(String userId);

    HandledRectifyInfoVO handledRectifyInfo(String userId, String rectifyId);

    boolean problemFeedback(String problemId, String auditFeedback);

    boolean problemEditSave(String userId, CheckProblemDTO checkProblemDTO);

    List getUserOfOrgInfo(String userId);

    List getRectifyTellerInfo(String userId, String tellerId, String tellerName);

    List getUserOfRoleInfo(String userId);

    Object problemAddQuery(String sunpointkey, String taskpk);
}
