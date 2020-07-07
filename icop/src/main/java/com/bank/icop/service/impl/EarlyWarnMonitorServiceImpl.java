package com.bank.icop.service.impl;

import com.bank.core.entity.BizException;
import com.bank.icop.dos.*;
import com.bank.icop.dto.TaskListsDto;
import com.bank.icop.service.EarlyWarnMonitorService;
import com.bank.icop.util.SoapUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EarlyWarnMonitorServiceImpl implements EarlyWarnMonitorService {
    @Override
    public Object getdealDatas(String userNo) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo",userNo);
        Map report = null;
        try {
            report = SoapUtil.sendReport("FXYJ10001","812",parmMap);
        } catch (Exception e) {
            throw new BizException("获取代办、未办结、已办数据列表报错！"+e.getMessage());
        }
        if(!"0".equals((String)report.get("status"))){
            throw new BizException("用户不存在,状态码:"+(String)report.get("status"));
        }
        return report;
    }

    @Override
    public Object getT69Alerts(String alertkey) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("alertkey",alertkey);
        Map report = null;
        try {
            report = SoapUtil.sendReport("FXYJ10002","812",parmMap);
        } catch (Exception e) {
            throw new BizException("预警任务基本信息查询报错！"+e.getMessage());
        }

        if("-1".equals((String)report.get("status"))){
            throw new BizException("预警编号为空,状态码:"+(String)report.get("status"));
        }else if("1".equals((String)report.get("status"))){
            throw new BizException("编号未查询出预警信息,状态码:"+(String)report.get("status"));
        }else if("3".equals((String)report.get("status"))){
            throw new BizException("无预警日志,状态码:"+(String)report.get("status"));
        }

        return report;
    }

    @Override
    public Object getTaskDetails(String alertKey) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("alertKey",alertKey);
        Map report = null;
        try {
            report = SoapUtil.sendReport("FXYJ10003","812",parmMap);
        } catch (Exception e) {
            throw new BizException("协查组任务查看详情数据查询（有、无调查记录)报错！"+e.getMessage());
        }
        if("-1".equals((String)report.get("status"))){
            throw new BizException("预警编号为空,状态码:"+(String)report.get("status"));
        }else if("1".equals((String)report.get("status"))){
            throw new BizException("编号未查询出预警信息,状态码:"+(String)report.get("status"));
        }else if("3".equals((String)report.get("status"))){
            throw new BizException("无预警日志,状态码:"+(String)report.get("status"));
        }
        return report;
    }

    @Override
    public Object modifyTasks(ModifyTasksDo modifyTasksDo) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("taskkey",modifyTasksDo.getTaskkey());
        parmMap.put("taskname",modifyTasksDo.getTaskname());
        parmMap.put("deallev",modifyTasksDo.getDeallev());
        parmMap.put("taskdesc",modifyTasksDo.getTaskdesc());
        parmMap.put("dealgroup",modifyTasksDo.getDealgroup());
        Map report = null;
        try {
            report = SoapUtil.sendReport("FXYJ10004","812",parmMap);
        } catch (Exception e) {
            throw new BizException("协查组任务数据修改报错！"+e.getMessage());
        }
        if(!"0".equals((String)report.get("status"))){
            throw new BizException("执行失败,状态码:"+(String)report.get("status"));
        }
        return report;
    }

    @Override
    public Object deleteTasks(String taskkey) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("taskkey",taskkey);
        Map report = null;
        try {
            report = SoapUtil.sendReport("FXYJ10005","812",parmMap);
        } catch (Exception e) {
            throw new BizException("协查组任务数据删除报错！"+e.getMessage());
        }
        if(!"0".equals((String)report.get("status"))){
            throw new BizException("执行失败,状态码:"+(String)report.get("status"));
        }
        return report;
    }

    @Override
    public Object returnTasks(ReturnTasksDo returnTasksDo) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("taskkey",returnTasksDo.getTaskkey());
        parmMap.put("userNo",returnTasksDo.getUserNo());
        parmMap.put("alertKey",returnTasksDo.getAlertKey());
        parmMap.put("opinion",returnTasksDo.getOpinion());
        Map report = null;
        try {
            report = SoapUtil.sendReport("FXYJ10006","812",parmMap);
        } catch (Exception e) {
            throw new BizException("协查组任务退回报错！"+e.getMessage());
        }
        if(!"0".equals((String)report.get("status"))){
            throw new BizException("执行失败,状态码:"+(String)report.get("status"));
        }
        return report;
    }

    @Override
    public Object addTaskDatas(AddTaskDatasDo addTaskDatasDo) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("taskkey",addTaskDatasDo.getTaskkey());
        parmMap.put("taskname",addTaskDatasDo.getTaskname());
        parmMap.put("feedbackdt",addTaskDatasDo.getFeedbackdt());
        parmMap.put("dealgroup",addTaskDatasDo.getDealgroup());
        parmMap.put("deallev",addTaskDatasDo.getDeallev());
        parmMap.put("taskdesc",addTaskDatasDo.getTaskdesc());
        parmMap.put("userNo",addTaskDatasDo.getUserNo());
        parmMap.put("alertKey",addTaskDatasDo.getAlertKey());
        Map report = null;
        try {
            report = SoapUtil.sendReport("FXYJ10007","812",parmMap);
        } catch (Exception e) {
            throw new BizException("协查组任务数据新增接口报错！"+e.getMessage());
        }
        if(!"0".equals((String)report.get("status"))){
            throw new BizException("执行失败,状态码:"+(String)report.get("status"));
        }
        return report;
    }

    @Override
    public Object queryT69alertlogs(String alertkey) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("alertkey",alertkey);
        Map report = null;
        try {
            report = SoapUtil.sendReport("FXYJ10008","812",parmMap);
        } catch (Exception e) {
            throw new BizException("查看预警任务日志信息查询报错！"+e.getMessage());
        }

        if("-1".equals((String)report.get("status"))){
            throw new BizException("预警编号为空,状态码:"+(String)report.get("status"));
        }else if("1".equals((String)report.get("status"))){
            throw new BizException("编号未查询出预警信息,状态码:"+(String)report.get("status"));
        }else if("3".equals((String)report.get("status"))){
            throw new BizException("无预警日志,状态码:"+(String)report.get("status"));
        }

        return report;
    }

    @Override
    public Object queryRuleDatas(String alertKey) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("alertKey",alertKey);
        Map report = null;
        try {
            report = SoapUtil.sendReport("FXYJ10009","812",parmMap);
        } catch (Exception e) {
            throw new BizException("查看规则信息查询报错！"+e.getMessage());
        }

        if("-1".equals((String)report.get("status"))){
            throw new BizException("预警编号为空,状态码:"+(String)report.get("status"));
        }else if("1".equals((String)report.get("status"))){
            throw new BizException("编号未查询出预警信息,状态码:"+(String)report.get("status"));
        }else if("3".equals((String)report.get("status"))){
            throw new BizException("无预警日志,状态码:"+(String)report.get("status"));
        }

        return report;
    }

    @Override
    public Object fileremoveRisk(FilereMoveRiskDo filereMoveRiskDo) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("alertkey",filereMoveRiskDo.getAlertkey());
        parmMap.put("obviatereason",filereMoveRiskDo.getObviatereason());
        parmMap.put("userNo",filereMoveRiskDo.getUserNo());
        parmMap.put("dept",filereMoveRiskDo.getDept());
        parmMap.put("rolekey",filereMoveRiskDo.getRolekey());
        parmMap.put("rulekey",filereMoveRiskDo.getRulekey());
        parmMap.put("tplakey",filereMoveRiskDo.getTplakey());
        parmMap.put("comments",filereMoveRiskDo.getComments());
        Map report = null;
        try {
            report = SoapUtil.sendReport("FXYJ10010","812",parmMap);
        } catch (Exception e) {
            throw new BizException("处理方式为排除的归档报错！"+e.getMessage());
        }
        if(!"0".equals((String)report.get("status"))){
            throw new BizException("执行失败,状态码:"+(String)report.get("status"));
        }
        return report;
    }

    @Override
    public Object fileRiskEvent(FileRiskEventDo fileRiskEventDo) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("alertkey",fileRiskEventDo.getAlertkey());
        parmMap.put("mistakeno",fileRiskEventDo.getMistakeno());
        parmMap.put("mistaketype",fileRiskEventDo.getMistaketype());
        parmMap.put("mistakeitem",fileRiskEventDo.getMistakeitem());
        parmMap.put("risk",fileRiskEventDo.getRisk());
        parmMap.put("mistakedesc",fileRiskEventDo.getMistakedesc());
        parmMap.put("sendorg",fileRiskEventDo.getSendorg());
        parmMap.put("mistaketlr",fileRiskEventDo.getMistaketlr());
        parmMap.put("orderfeedbackdt",fileRiskEventDo.getOrderfeedbackdt());
        parmMap.put("tradesum",fileRiskEventDo.getTradesum());
        parmMap.put("sendorg_disp",fileRiskEventDo.getSendorg_disp());
        parmMap.put("mistaketlr_disp",fileRiskEventDo.getMistaketlr_disp());
        parmMap.put("userNo",fileRiskEventDo.getUserNo());
        Map report = null;
        try {
            report = SoapUtil.sendReport("FXYJ10011","812",parmMap);
        } catch (Exception e) {
            throw new BizException("处理方式为登记风险事件的归档报错！"+e.getMessage());
        }
        if(!"0".equals((String)report.get("status"))){
            throw new BizException("执行失败,状态码:"+(String)report.get("status"));
        }
        return report;
    }

    @Override
    public Object queryTaskLists(TaskListsDto taskListsDto) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("alertkey",taskListsDto.getAlertkey());
        parmMap.put("userNo",taskListsDto.getUserNo());
        Map report = null;
        try {
            report = SoapUtil.sendReport("FXYJ10018","812",parmMap);
        } catch (Exception e) {
            throw new BizException("协查任务基本信息查询报错！"+e.getMessage());
        }

        if("-1".equals((String)report.get("status"))){
            throw new BizException("预警编号为空,状态码:"+(String)report.get("status"));
        }else if("1".equals((String)report.get("status"))){
            throw new BizException("编号未查询出预警信息,状态码:"+(String)report.get("status"));
        }else if("3".equals((String)report.get("status"))){
            throw new BizException("无预警日志,状态码:"+(String)report.get("status"));
        }
        return report;
    }

    @Override
    public Object updateDealRecords(UpdateDealRecordsDo updateDealRecordsDo) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("invtkey",updateDealRecordsDo.getInvtkey());
        parmMap.put("topic",updateDealRecordsDo.getTopic());
        parmMap.put("creator",updateDealRecordsDo.getCreator());
        parmMap.put("beinvted",updateDealRecordsDo.getBeinvted());
        parmMap.put("verdict",updateDealRecordsDo.getVerdict());
        parmMap.put("processdes",updateDealRecordsDo.getProcessdes());
        Map report = null;
        try {
            report = SoapUtil.sendReport("FXYJ10019","812",parmMap);
        } catch (Exception e) {
            throw new BizException("处理记录修改报错！"+e.getMessage());
        }
        if(!"0".equals((String)report.get("status"))){
            throw new BizException("执行失败,状态码:"+(String)report.get("status"));
        }
        return report;
    }

    @Override
    public Object deleteDealRecords(String invtkey) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("invtkey",invtkey);
        Map report = null;
        try {
            report = SoapUtil.sendReport("FXYJ10020","812",parmMap);
        } catch (Exception e) {
            throw new BizException("处理记录删除报错！"+e.getMessage());
        }
        if(!"0".equals((String)report.get("status"))){
            throw new BizException("执行失败,状态码:"+(String)report.get("status"));
        }
        return report;
    }

    @Override
    public Object submitTasks(SubmitTasksDo submitTasksDo) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("taskkey",submitTasksDo.getTaskkey());
        parmMap.put("dealflag",submitTasksDo.getDealflag());
        parmMap.put("wpuser",submitTasksDo.getWpuser());
        parmMap.put("filetime",submitTasksDo.getFiletime());
        Map report = null;
        try {
            report = SoapUtil.sendReport("FXYJ10021","812",parmMap);
        } catch (Exception e) {
            throw new BizException("协查组任务调查提交报错！"+e.getMessage());
        }
        if(!"0".equals((String)report.get("status"))){
            throw new BizException("执行失败,状态码:"+(String)report.get("status"));
        }
        return report;
    }

    @Override
    public Object getT69AlertList(String userNo) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo",userNo);
        Map report = null;
        try {
            report = SoapUtil.sendReport("FXYJ10022","812",parmMap);
        } catch (Exception e) {
            throw new BizException("预警发起与识别数据列表报错！"+e.getMessage());
        }
        if(!"0".equals((String)report.get("status"))){
            throw new BizException("执行失败,状态码:"+(String)report.get("status"));
        }
        return report;
    }

    @Override
    public Object getReplyDataList(String userNo) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo",userNo);
        Map report = null;
        try {
            report = SoapUtil.sendReport("FXYJ10023","812",parmMap);
        } catch (Exception e) {
            throw new BizException("已回复协查数据列表报错！"+e.getMessage());
        }
        if(!"0".equals((String)report.get("status"))){
            throw new BizException("执行失败,状态码:"+(String)report.get("status"));
        }
        return report;
    }

    @Override
    public Object getNotReplyLists(String userNo) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo",userNo);
        Map report = null;
        try {
            report = SoapUtil.sendReport("FXYJ10024","812",parmMap);
        } catch (Exception e) {
            throw new BizException("未回复协查数据列表报错！"+e.getMessage());
        }
        if(!"0".equals((String)report.get("status"))){
            throw new BizException("执行失败,状态码:"+(String)report.get("status"));
        }
        return report;
    }

    @Override
    public Object returnAlerts(String alertkey) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("alertkey",alertkey);
        Map report = null;
        try {
            report = SoapUtil.sendReport("FXYJ10012","812",parmMap);
        } catch (Exception e) {
            throw new BizException("已回复协查任务预警任务基本信息查询报错！"+e.getMessage());
        }
        if("-1".equals((String)report.get("status"))){
            throw new BizException("预警编号为空,状态码:"+(String)report.get("status"));
        }else if("1".equals((String)report.get("status"))){
            throw new BizException("编号未查询出预警信息,状态码:"+(String)report.get("status"));
        }else if("3".equals((String)report.get("status"))){
            throw new BizException("无预警日志,状态码:"+(String)report.get("status"));
        }
        return report;
    }

    @Override
    public Object returnReplyLists(String taskkey) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("taskkey",taskkey);
        Map report = null;
        try {
            report = SoapUtil.sendReport("FXYJ10013","812",parmMap);
        } catch (Exception e) {
            throw new BizException("已回复协查组任务查看详情数据查询（有、无调查记录）报错！"+e.getMessage());
        }
        if(!"0".equals((String)report.get("status"))){
            throw new BizException("执行失败,状态码:"+(String)report.get("status"));
        }
        return report;
    }

    @Override
    public Object getAlertLogLists(String alertkey) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("alertkey",alertkey);
        Map report = null;
        try {
            report = SoapUtil.sendReport("FXYJ10014","812",parmMap);
        } catch (Exception e) {
            throw new BizException("查看预警任务日志信息查询报错！"+e.getMessage());
        }
        if(!"0".equals((String)report.get("status"))){
            throw new BizException("执行失败,状态码:"+(String)report.get("status"));
        }
        return report;
    }

    @Override
    public Object getTplakeyLists(String tplakey) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("tplakey",tplakey);
        Map report = null;
        try {
            report = SoapUtil.sendReport("FXYJ10015","812",parmMap);
        } catch (Exception e) {
            throw new BizException("查看规则信息查询报错！"+e.getMessage());
        }
        if(!"0".equals((String)report.get("status"))){
            throw new BizException("执行失败,状态码:"+(String)report.get("status"));
        }
        return report;
    }

    @Override
    public Object returnNotReplyLists(String taskkey) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("taskkey",taskkey);
        Map report = null;
        try {
            report = SoapUtil.sendReport("FXYJ10016","812",parmMap);
        } catch (Exception e) {
            throw new BizException("未回复协查组任务查看详情数据查询（有、无调查记录）报错！"+e.getMessage());
        }
        if(!"0".equals((String)report.get("status"))){
            throw new BizException("执行失败,状态码:"+(String)report.get("status"));
        }
        return report;
    }
}
