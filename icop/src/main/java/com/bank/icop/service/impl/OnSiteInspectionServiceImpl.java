package com.bank.icop.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bank.core.entity.BizException;
import com.bank.icop.dto.CheckItemCheckSubmitDTO;
import com.bank.icop.dto.CheckProblemDTO;
import com.bank.icop.dto.CheckTaskSaveDTO;
import com.bank.icop.service.OnSiteInspectionService;
import com.bank.icop.util.SoapUtil;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 现场检查接口实现类
 * ClassName: OnSiteInspectionServiceImpl
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/07/06 19:45:53
 */
@Service
public class OnSiteInspectionServiceImpl implements OnSiteInspectionService {

    @Override
    public List inspectionTaskList(String userId) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo", userId);

        return getIcopTagList(parmMap, "FXYJ11001", "获取运营检查任务列表", "1", "返回状态  -1:参数为空 ,  0:用户不存在");
    }

    @Override
    public List taskItemList(String userId, String taskId, String createOrgId, String executeOrgId, String taskName, String taskStartDate, String taskEndDate) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo", userId);
        parmMap.put("taskpk", taskId);
        parmMap.put("taskname", taskName);
        parmMap.put("taskstartdate", taskStartDate);
        parmMap.put("taskenddate", taskEndDate);
        return getIcopTagList(parmMap,
                "FXYJ11002",
                "获取运营检查大项展示列表",
                "1,2",
                "返回状态  -1:参数为空 ,  0:用户不存在  , 1:未查询出数据 ,2:正常 ");
    }

    @Override
    public Object registerCheck(String userId, String taskItemId, String inspectionInfoId) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo", userId);
        parmMap.put("pk", taskItemId);
        parmMap.put("qpk", inspectionInfoId);
        return getReport(parmMap,
                "FXYJ11003",
                "检查任务执行展示",
                "1,2",
                "返回状态  -1:参数为空 ,  0:用户不存在  , 1:未查询出数据 ,2:正常 ");
    }

    @Override
    public String check(String taskItemId, String inspectionInfoId) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("sunpointkey", taskItemId);
        parmMap.put("taskpk", inspectionInfoId);
        Map report = getReport(parmMap,
                "FXYJ11004",
                "检查要点查看",
                "1,2",
                "返回状态  -1:参数为空 , 0:未查询出数据 ,1:正常 ");
        Object value = report.get("value");
        if (StrUtil.isBlankIfStr(value)) {
            throw new BizException("返回的检查要点为空！");
        }
        return (String) value;
    }

    @Override
    public Object problemEdit(String pk, String taskpk) {
        Map<String, Object> parmMap = new HashMap();
        parmMap.put("pk", pk);
        parmMap.put("taskpk", taskpk);
        return getIcopTagData(parmMap,
                "FXYJ11005",
                "检查问题编辑",
                "1,2",
                "返回状态  -1:参数为空 , 0:未查询出数据 ,1:正常 ", "details");
    }

    @Override
    public Object checkTaskSave(String currentUserId, CheckTaskSaveDTO checkTaskSaveDTO) {
        Map<String, Object> parmMap = new HashMap();
        parmMap.put("userNo", currentUserId);
        parmMap.put("jsonstr", JSON.toJSONString(checkTaskSaveDTO));
        Map report = getReport(parmMap,
                "FXYJ11006",
                "检查任务的保存",
                "0",
                "返回状态  -1:失败 , 0:成功");
        Object value = report.get("status");
        if (StrUtil.isBlankIfStr(value)) {
            throw new BizException("检查任务的保存接口，status返回值为空！");
        }
        return value;
    }

    @Override
    public Object checkTaskSubmit(String currentUserId, String pk) {
        Map<String, Object> parmMap = new HashMap();
        parmMap.put("userNo", currentUserId);
        parmMap.put("pk", pk);
        Map report = getReport(parmMap,
                "FXYJ11007",
                "检查任务的提交",
                "0",
                "返回状态  -1:失败 , 0:成功");
        Object value = report.get("status");
        if (StrUtil.isBlankIfStr(value)) {
            throw new BizException("检查任务的提交失败！");
        }
        return value;
    }

    @Override
    public Object taskList(String userId, String runorgankey, String taskName, String taskStartDate, String taskEndDate) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo", userId);
        parmMap.put("runorgankey", runorgankey);
        parmMap.put("taskname", taskName);
        parmMap.put("taskstartdate", taskStartDate);
        parmMap.put("taskenddate", taskEndDate);
        return getIcopTagList(parmMap,
                "FXYJ11008",
                "获取运营检查大项展示列表",
                "1,2",
                "返回状态  -1:参数为空 ,  0:用户不存在  , 1:未查询出数据 ,2:正常 ");
    }

    @Override
    public Object checkDetail(String userId, String taskpk) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo", userId);
        parmMap.put("taskpk", taskpk);
        return getIcopTagList(parmMap,
                "FXYJ11009",
                "检查内容详细查看",
                "0,1",
                "返回状态  -1:参数为空 ,  0:未查询出数据  , 1:正常  ");
    }

    @Override
    public Object problemList(String userId, String taskpk) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo", userId);
        parmMap.put("taskpk", taskpk);
        return getIcopTagList(parmMap,
                "FXYJ11010",
                "检查问题查看列表",
                "0,1",
                "返回状态  -1:参数为空 ,  0:未查询出数据  , 1:正常  ");
    }

    @Override
    public Object checkList(String userId) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo", userId);
        return getIcopTagList(parmMap,
                "FXYJ11011",
                "检查问题查看列表",
                "1,2",
                "返回状态  -1:参数为空 ,  0:用户不存在  , 1:未查询出数据 ,2:正常 ");
    }

    @Override
    public Object contentList(String userId, String taskpk) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo", userId);
        parmMap.put("taskpk", taskpk);
        return getIcopTagList(parmMap,
                "FXYJ11012",
                "检查内容审核列表",
                "1,2",
                "返回状态  -1:参数为空 ,  0:用户不存在  , 1:未查询出数据 ,2:正常 ");
    }

    @Override
    public Object contentCheck(String pk) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("pk", pk);
        return getReport(parmMap,
                "FXYJ11013",
                "检查内容详细审核",
                "1,2",
                "返回状态  -1:参数为空 ,  0:用户不存在  , 1:未查询出数据 ,2:正常 ");
    }

    @Override
    public Object problemView(String sunpointkey, String qpk) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("sunpointkey", sunpointkey);
        parmMap.put("qpk", qpk);
        return getIcopTagList(parmMap,
                "FXYJ11014",
                "问题查看",
                "0",
                "返回状态  -1:参数为空 ,  0:正常 ");
    }

    @Override
    public Object coreCheck(String key) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("key", key);
        return getIcopTagList(parmMap,
                "FXYJ11015",
                "问题查看",
                "0",
                "返回状态  -1:参数为空 ,  0:正常 ");
    }

    @Override
    public Object childCheck(String currentUserId, CheckItemCheckSubmitDTO checkItemCheckSubmitDTO) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo", currentUserId);
        parmMap.put("taskpk", checkItemCheckSubmitDTO.getTaskpk());
        parmMap.put("feedback", checkItemCheckSubmitDTO.getFeedback());
        parmMap.put("feedbackdt", checkItemCheckSubmitDTO.getFeedbackdt());
        parmMap.put("feedbackdes", checkItemCheckSubmitDTO.getFeedbackdes());
        parmMap.put("epk", checkItemCheckSubmitDTO.getEpk());
        Map report = getReport(parmMap,
                "FXYJ11016",
                "检查子项审核提交",
                "0",
                "返回状态  -1:执行失败 ,  0:执行成功 ");
        Object value = report.get("status");
        if (StrUtil.isBlankIfStr(value)) {
            throw new BizException("检查子项审核提交，status返回值为空");
        }
        return value;
    }

    @Override
    public Object problemUpdateList(String userId) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo", userId);
        return getIcopTagList(parmMap,
                "FXYJ11017",
                "问题待整改列表",
                "0,1",
                "返回状态  -1:参数为空 ,  0:未查询出数据 ,1:正常");
    }

    @Override
    public Object problemRectifyInfo(String currentUserId, String key) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo", currentUserId);
        parmMap.put("cpk", key);
        Map data = getReport(parmMap,
                "FXYJ11018",
                "问题整改展示界面",
                "0",
                "返回状态  -1:参数为空 ,  0:正常 ");
        Map result = new HashMap();
        result.put("corrective", data.get("corrective"));
        result.put("List", getArray(data.get("List")));

        return result;
    }

    @Override
    public Object feedbackSave(String currentUserId, String key, String feedbackdes) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo", currentUserId);
        parmMap.put("cpk", key);
        parmMap.put("feedbackdes", feedbackdes);
        Map report = getReport(parmMap,
                "FXYJ11019",
                "整改反馈说明保存",
                "0",
                "返回状态  -1:执行失败 ,  0:执行成功 ");
        Object value = report.get("status");
        if (StrUtil.isBlankIfStr(value)) {
            throw new BizException("整改反馈说明保存,status返回值为空！");
        }
        return value;
    }

    @Override
    public Object feedbackSubmit(String currentUserId, String cpk) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo", currentUserId);
        parmMap.put("cpk", cpk);
        Map report = getReport(parmMap,
                "FXYJ11020",
                "整改提交",
                "0",
                "返回状态  -1:执行失败 ,  0:执行成功 ");
        Object value = report.get("status");
        if (StrUtil.isBlankIfStr(value)) {
            throw new BizException("整改提交，status返回值为空");
        }
        return value;
    }

    @Override
    public Object updateCheckList(String userId) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo", userId);
        return getIcopTagList(parmMap,
                "FXYJ11021",
                "整改待审核列表",
                "0,1",
                "返回状态  -1:参数为空 ,  0:未查询出数据 ,1:正常");
    }

    @Override
    public Object feedbackView(String currentUserId, String cpk) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo", currentUserId);
        parmMap.put("cpk", cpk);
        return getReport(parmMap,
                "FXYJ11022",
                "整改审批展示",
                "0",
                "返回状态  -1:参数为空 ,  0:正常");
    }

    @Override
    public Object feedbackCheckSubmit(String currentUserId, String cpk, String decision, String approvelog) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo", currentUserId);
        parmMap.put("cpk", cpk);
        parmMap.put("decision", decision);
        parmMap.put("approvelog", approvelog);
        Map report = getReport(parmMap,
                "FXYJ11023",
                "整改审批提交",
                "0",
                "返回状态  -1:参数为空 ,  0:正常");
        Object value = report.get("status");
        if (StrUtil.isBlankIfStr(value)) {
            throw new BizException("整改审批提交，status返回值为空");
        }
        return value;
    }

    @Override
    public Object checkTaskDetail(String currentUserId, String taskpk) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo", currentUserId);
        parmMap.put("taskpk", taskpk);
        return getIcopTagList(parmMap,
                "FXYJ11024",
                "检查任务详细查看",
                "0,1",
                "返回状态  -1:参数为空 ,  0:未查询出数据  , 1:正常  ");
    }

    @Override
    public Object problemDetail(String pk) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("pk", pk);
        return getReport(parmMap,
                "FXYJ11025",
                "问题详细查看",
                "0,1",
                "返回状态  -1:参数为空 ,  0:未查询出数据  , 1:正常");
    }

    @Override
    public List handledRectifyList(String userId) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("username", userId);//4095

        return getIcopTagList(parmMap, "FXYJ11028", "已处理整改列表", "0,1", "返回状态  -1:参数为空 ,  0:未查询出数据 ,1:正常");
    }

    @Override
    public Object handledRectifyInfo(String userId, String rectifyId) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo", userId);
        parmMap.put("cpk", rectifyId);

        Map returnData = getReport(parmMap, "FXYJ11029", "已整改详细信息", "1", "返回状态  -1:参数为空 ,  0:用户不存在");

        Map result = new HashMap();
        result.put("corrective", returnData.get("corrective"));
        result.put("List", getArray(returnData.get("List")));

        return result;
    }

    @Override
    public boolean problemFeedback(String problemId, String auditFeedback) {
        Map<String, Object> parmMap = new HashMap<>();
        //反馈意见
        parmMap.put("details", auditFeedback);
        //问题PK
        parmMap.put("pk", problemId);
        getReport(parmMap, "FXYJ11026", "问题反馈", "0", "返回状态  -1:失败 ,  0:成功");
        return true;
    }

    @Override
    public boolean problemEditSave(String userId, CheckProblemDTO checkProblemDTO) {
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("taskPk", checkProblemDTO.getTaskId());
        dataMap.put("pk", checkProblemDTO.getProblemId());
        dataMap.put("qdate", checkProblemDTO.getProblemDate());
        dataMap.put("qtype", checkProblemDTO.getProblemType());
        dataMap.put("qorgan", checkProblemDTO.getProblemRectifyOrg());
        dataMap.put("quser", checkProblemDTO.getRectifyDutyUser());
        dataMap.put("quserName", checkProblemDTO.getRectifyDutyUserName());
        dataMap.put("qdes", checkProblemDTO.getProblemDes());

        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo", userId);
        parmMap.put("jsonstr", JSONObject.toJSONString(dataMap));

        getReport(parmMap, "FXYJ11027", "问题保存", "0", "返回状态  -1:失败 ,  0:成功");
        return true;
    }

    @Override
    public List getUserOfOrgInfo(String userId) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo", userId);
        Object data = getIcopTagData(parmMap, "FXYJ11030", "用户所属机构", "0", "返回状态  -1:失败 ,  0:成功", "organ");
        return getArray(data);
    }

    @Override
    public List getRectifyTellerInfo(String userId, String tellerId, String tellerName) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo", userId);
        parmMap.put("userId", tellerId);
        parmMap.put("userName", tellerName);

        return getIcopTagList(parmMap, "FXYJ11031", "获取整改柜员", "1,2", "返回状态  -1:传入参数为空 ,  0:查询用户不存在 ,1:未查询出柜员 ,2:执行正常");
    }

    @Override
    public List getUserOfRoleInfo(String userId) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo", userId);

        return getIcopTagList(parmMap, "FXYJ11032", "用户所属角色", "1", "返回状态  -1:传入参数为空 ,  0:查询用户不存在 ,1:执行正常");
    }

    @Override
    public Object problemAddQuery(String sunpointkey, String taskpk) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("key", sunpointkey);
        parmMap.put("taskpk", taskpk);

        return getIcopTagData(parmMap, "FXYJ11033", "检查问题添加", "0,1", "返回状态 -1:参数为空 , 0:未查询出数据 ,1:正常", "check");
    }

    private Object getIcopTagData(Map<String, Object> parmMap, String serviceCode, String serviceName, String rightCode, String errMsg, String tag) {
        Map report = getReport(parmMap, serviceCode, serviceName, rightCode, errMsg);
        Object list = report.get(tag);
        if (list != null) {
            return list;
        }
        else {
            throw new BizException("返回值不包含" + tag + "标签！");
        }
    }

    private List getIcopTagList(Map<String, Object> parmMap, String serviceCode, String serviceName, String rightCode, String errMsg) {
        Object data = getIcopTagData(parmMap, serviceCode, serviceName, rightCode, errMsg, "List");
        return getArray(data);
    }

    private List getArray(Object data) {
        if (data instanceof ArrayList) {
            return (List) data;
        }
        else {
            List result = new ArrayList();
            if (data instanceof String) {
            }
            else {
                result.add(data);
            }
            return result;
        }
    }

    private Map getReport(Map<String, Object> parmMap, String serviceCode, String serviceName, String rightCode, String errMsg) {
        Map report = null;
        try {
            report = SoapUtil.sendReport(serviceCode, "812", parmMap);
        }
        catch (Exception e) {
            String errorMess = e.getMessage();
            if (StringUtils.equals("Read timed out", e.getMessage())) {
                errorMess = "请求超时！排查ICOP、ESB、源系统应用服务是否正常！";
            }
            throw new BizException(serviceName + "【" + serviceCode + "】报错：" + errorMess);
        }
        if (CollectionUtil.isEmpty(report)) {
            throw new BizException("返回报文为空！");
        }
        checkStatus(serviceName, rightCode, errMsg, report);
        return report;
    }

    private void checkStatus(String serviceName, String rightCode, String errMsg, Map report) {
        String status = (String) report.get("status");
        if (StrUtil.isBlankIfStr(status)) {
            throw new BizException(serviceName + "接口返回状态码为空！");
        }
        else {
            if (StringUtils.indexOf(rightCode, status) == -1) {
                throw new BizException(errMsg + "；该接口返回状态码为：" + status + "！");
            }
        }
    }

}
