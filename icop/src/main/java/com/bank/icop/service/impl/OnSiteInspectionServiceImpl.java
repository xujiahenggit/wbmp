package com.bank.icop.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.bank.core.entity.BizException;
import com.bank.icop.service.OnSiteInspectionService;
import com.bank.icop.util.SoapUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SOAP调用第三方接口 现场检查实现类
 *
 * @author zhaozhongyuan
 * @since 2020-06-09
 */
@Service
public class OnSiteInspectionServiceImpl implements OnSiteInspectionService {


    @Override
    public List<Map> inspectionTaskList(String userId) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo", userId);//4095
        return getIcopTagList(parmMap,
                "FXYJ11001",
                "获取运营检查任务列表",
                1,
                "返回状态  -1:参数为空 ,  0:用户不存在");
    }

    @Override
    public List<Map> taskItemList(String userId, String taskId, String createOrgId, String executeOrgId, String taskName, String taskStartDate, String taskEndDate) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo", userId);
        parmMap.put("taskpk", taskId);
        parmMap.put("taskname", taskName);
        parmMap.put("taskstartdate", taskStartDate);
        parmMap.put("taskenddate", taskEndDate);
        return getIcopTagList(parmMap,
                "FXYJ11002",
                "获取运营检查大项展示列表",
                2,
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
                2,
                "返回状态  -1:参数为空 ,  0:用户不存在  , 1:未查询出数据 ,2:正常 ");
    }

    @Override
    public boolean check(String taskItemId, String inspectionInfoId) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("sunpointkey", taskItemId);
        parmMap.put("taskpk", inspectionInfoId);
        Map report = getReport(parmMap,
                "FXYJ11004",
                "检查要点查看",
                1,
                "返回状态  -1:参数为空 , 0:未查询出数据 ,1:正常 ");
        Object value = report.get("value");
        if (StrUtil.isBlankIfStr(value)){
            throw new BizException("返回的检查要点为空！");
        }
        return (boolean) value;
    }

    @Override
    public Object problemEdit(String pk, String taskpk) {
        Map<String, Object> parmMap = new HashMap();
        parmMap.put("pk", pk);
        parmMap.put("taskpk", taskpk);
        return getIcopTagData(parmMap,
                "FXYJ11005",
                "检查要点查看",
                1,
                "返回状态  -1:参数为空 , 0:未查询出数据 ,1:正常 ","details");
    }

    @Override
    public Object problemSave(String currentUserId, String jsonstr) {
        Map<String, Object> parmMap = new HashMap();
        parmMap.put("userNo", currentUserId);
        parmMap.put("jsonstr", jsonstr);
        Map report = getReport(parmMap,
                "FXYJ11006",
                "检查任务的保存",
                0,
                "返回状态  -1:失败 , 0:成功");
        Object value = report.get("status");
        if (StrUtil.isBlankIfStr(value)){
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
                0,
                "返回状态  -1:失败 , 0:成功");
        Object value = report.get("status");
        if (StrUtil.isBlankIfStr(value)){
            throw new BizException("检查任务的提交失败！");
        }
        return value;
    }

    @Override
    public List<Map> taskList(String userId, String runorgankey, String taskName, String taskStartDate, String taskEndDate) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo", userId);
        parmMap.put("runorgankey", runorgankey);
        parmMap.put("taskname", taskName);
        parmMap.put("taskstartdate", taskStartDate);
        parmMap.put("taskenddate", taskEndDate);
        return getIcopTagList(parmMap,
                "FXYJ11008",
                "获取运营检查大项展示列表",
                2,
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
                1,
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
                1,
                "返回状态  -1:参数为空 ,  0:未查询出数据  , 1:正常  ");
    }

    @Override
    public Object checkList(String userId) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo", userId);
        return getIcopTagList(parmMap,
                "FXYJ11011",
                "检查问题查看列表",
                2,
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
                2,
                "返回状态  -1:参数为空 ,  0:用户不存在  , 1:未查询出数据 ,2:正常 ");
    }

    @Override
    public Object contentCheck(String pk) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("pk", pk);
        return getReport(parmMap,
                "FXYJ11013",
                "检查内容详细审核",
                2,
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
                0,
                "返回状态  -1:参数为空 ,  0:正常 ");
    }

    @Override
    public Object coreCheck(String key) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("key", key);
        return getIcopTagList(parmMap,
                "FXYJ11015",
                "问题查看",
                0,
                "返回状态  -1:参数为空 ,  0:正常 ");
    }

    @Override
    public Object childCheck(String currentUserId, String taskpk, String feedback, String feedbackdt, String feedbackdes, String epk) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo", currentUserId);
        parmMap.put("taskpk", taskpk);
        parmMap.put("feedback", feedback);
        parmMap.put("feedbackdt", feedbackdt);
        parmMap.put("feedbackdes", feedbackdes);
        parmMap.put("epk", epk);
        Map report = getReport(parmMap,
                "FXYJ11016",
                "检查子项审核提交",
                0,
                "返回状态  -1:执行失败 ,  0:执行成功 ");
        Object value = report.get("status");
        if (StrUtil.isBlankIfStr(value)){
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
                0,
                "返回状态  -1:参数为空 ,  0:正常 ");
    }

    @Override
    public Object problemUI(String currentUserId, String key) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo", currentUserId);
        parmMap.put("cpk", key);
        return getIcopTagList(parmMap,
                "FXYJ11018",
                "问题整改展示界面",
                0,
                "返回状态  -1:参数为空 ,  0:正常 ");
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
                0,
                "返回状态  -1:执行失败 ,  0:执行成功 ");
        Object value = report.get("status");
        if (StrUtil.isBlankIfStr(value)){
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
                0,
                "返回状态  -1:执行失败 ,  0:执行成功 ");
        Object value = report.get("status");
        if (StrUtil.isBlankIfStr(value)){
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
                0,
                "返回状态  -1:参数为空 ,  0:正常 ");
    }

    @Override
    public Object feedbackView(String currentUserId, String cpk) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo", currentUserId);
        parmMap.put("cpk", cpk);
        return getReport(parmMap,
                "FXYJ11022",
                "整改审批展示",
                0,
                "返回状态  -1:参数为空 ,  0:正常");
    }


    @Override
    public Object feedbackCheckSubmit(String currentUserId, String cpk, String decision, String approvelog) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo", currentUserId);
        parmMap.put("cpk", cpk);
        parmMap.put("decision", decision);
        parmMap.put("approvelog", approvelog);
        Map report =getReport(parmMap,
                "FXYJ11023",
                "整改审批提交",
                0,
                "返回状态  -1:参数为空 ,  0:正常");
        Object value = report.get("status");
        if (StrUtil.isBlankIfStr(value)){
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
                1,
                "返回状态  -1:参数为空 ,  0:未查询出数据  , 1:正常  ");
    }

    @Override
    public Object problemDetail(String pk) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("pk", pk);
        return getReport(parmMap,
                "FXYJ11025",
                "问题详细查看",
                1,
                "返回状态  -1:参数为空 ,  0:未查询出数据  , 1:正常");
    }


    private List<Map> getIcopTagData(Map<String, Object> parmMap, String serviceCode, String serviceName, Integer rightCode, String errMsg, String tag) {
        Map report = getReport(parmMap, serviceCode, serviceName, rightCode, errMsg);
        Object list = report.get(tag);
        if (list != null) {
            List<Map> tagData = null;
            try {
                tagData = (List<Map>) list;
            } catch (Exception e) {
                throw new BizException(tag + "标签数据转List<Map>出错，" + e.getMessage());
            }
            return tagData;
        } else {
            throw new BizException("返回值不包含" + tag + "标签！");
        }
    }


    private List<Map> getIcopTagList(Map<String, Object> parmMap, String serviceCode, String serviceName, Integer rightCode, String errMsg) {
        return getIcopTagData(parmMap, serviceCode, serviceName, rightCode, errMsg, "List");
    }

    private Map getReport(Map<String, Object> parmMap, String serviceCode, String serviceName, Integer rightCode, String errMsg) {
        Map report = null;
        try {
            report = SoapUtil.sendReport(serviceCode, "812", parmMap);
        } catch (Exception e) {
            throw new BizException(serviceName + "报错！" + e.getMessage());
        }
        if (CollectionUtil.isEmpty(report)) {
            throw new BizException("返回报文为空！");
        }
        checkStatus(serviceName, rightCode, errMsg, report);
        return report;
    }

    private void checkStatus(String serviceName, Integer rightCode, String errMsg, Map report) {
        Object status = report.get("status");
        if (StrUtil.isBlankIfStr(status)) {
            throw new BizException(serviceName + "接口返回状态码为空！");
        } else {
            if (!status.equals(String.valueOf(rightCode))) {
                throw new BizException(errMsg + "；该接口返回状态码为：" + status + "！");
            }
        }
    }


}
