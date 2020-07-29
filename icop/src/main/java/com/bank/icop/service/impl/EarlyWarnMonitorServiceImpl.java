package com.bank.icop.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.bank.core.entity.BizException;
import com.bank.icop.dos.*;
import com.bank.icop.dto.TaskListsDto;
import com.bank.icop.service.EarlyWarnMonitorService;
import com.bank.icop.util.SoapUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public Object getT69Alerts(AlertsDo alertsDo) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("alertkey",alertsDo.getAlertKey());
        parmMap.put("userNo",alertsDo.getUserNo());
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
        parmMap.put("TASKKEY",alertKey);
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
        return getIcopTagList(parmMap, "FXYJ10018", "协查任务列表", "0", "执行失败");


    }

    @Override
    public Object updateDealRecords(UpdateDealRecordsDo updateDealRecordsDo) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("invtkey",updateDealRecordsDo.getInvtkey());
        parmMap.put("topic",updateDealRecordsDo.getTopic());
        parmMap.put("creator",updateDealRecordsDo.getCreator());
        parmMap.put("beinvted",updateDealRecordsDo.getBeinvted());
        parmMap.put("taskkey",updateDealRecordsDo.getTaskkey());
        parmMap.put("verdict",updateDealRecordsDo.getVerdict());
        parmMap.put("processdes",updateDealRecordsDo.getProcessdes());
        parmMap.put("CONTENTID",updateDealRecordsDo.getCONTENTID());
        parmMap.put("BUSISTARTDATE",updateDealRecordsDo.getBUSISTARTDATE());
        parmMap.put("BUSISERIALNO",updateDealRecordsDo.getBUSISERIALNO());
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
//        parmMap.put("taskkey",submitTasksDo.getTaskkey());
//        parmMap.put("dealflag",submitTasksDo.getDealflag());
//        parmMap.put("wpuser",submitTasksDo.getWpuser());
//        parmMap.put("filetime",submitTasksDo.getFiletime());
        parmMap.put("taskkey",submitTasksDo.getTaskkey());
        parmMap.put("userNo",submitTasksDo.getUserNo());

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
    public Object getT69AlertList(AlertListDo alertListDo) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo",alertListDo.getUserNo());
        parmMap.put("alertKey",alertListDo.getAlertKey());
        parmMap.put("startDt",alertListDo.getStartDt());
        parmMap.put("endDt",alertListDo.getEndDt());
        parmMap.put("risklev",alertListDo.getRisklev());
        parmMap.put("dealflag",alertListDo.getDealflag());
        parmMap.put("fcettypecode",alertListDo.getFcettypecode());
        if(alertListDo.getCjstatus() != null && !"".equals(alertListDo.getCjstatus())){
            parmMap.put("cjstatus",alertListDo.getCjstatus());
        }

        parmMap.put("limit",alertListDo.getLimit());
        parmMap.put("offset",alertListDo.getOffset());

        return getIcopTagList(parmMap, "FXYJ10022", "预警发起与识别数据列表", "0", "执行失败");
    }

    @Override
    public Object getReplyDataList(ReplyDataDo replyDataDo) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo",replyDataDo.getUserNo());
        parmMap.put("alertKey",replyDataDo.getAlertKey());
        parmMap.put("endDt",replyDataDo.getEndDt());
        parmMap.put("startDt",replyDataDo.getStartDt());
        parmMap.put("risklev",replyDataDo.getRisklev());
        parmMap.put("dealflag",replyDataDo.getDealflag());
//        parmMap.put("fcettypecode",replyDataDo.getFcettypecode());
//        parmMap.put("cjstatus",replyDataDo.getCjstatus());
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
    public Object getNotReplyLists(ReplyDataDo replyDataDo) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo",replyDataDo.getUserNo());
        parmMap.put("alertKey",replyDataDo.getAlertKey());
        parmMap.put("endDt",replyDataDo.getEndDt());
        parmMap.put("startDt",replyDataDo.getStartDt());
        parmMap.put("risklev",replyDataDo.getRisklev());
        parmMap.put("dealflag",replyDataDo.getDealflag());
//        parmMap.put("fcettypecode",replyDataDo.getFcettypecode());
//        parmMap.put("cjstatus",replyDataDo.getCjstatus());
        return getIcopTagList(parmMap, "FXYJ10024", "未回复协查数据列表报错", "0", "执行失败");

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

    @Override
    public Object returnRoleLists(String userNo) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo",userNo);
        return getIcopTagList(parmMap, "FXYJ11032", "查看用户角色报错", "1", "执行失败");

    }

    @Override
    public Object getTaskByCode(String taskkey) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("taskkey",taskkey);
        Map report = null;
        try {
            report = SoapUtil.sendReport("FXYJ10029","812",parmMap);
        } catch (Exception e) {
            throw new BizException("查看用户角色报错！"+e.getMessage());
        }
        if(!"0".equals((String)report.get("status"))){
            throw new BizException("执行失败,状态码:"+(String)report.get("status"));
        }
        return report;
    }

    @Override
    public Object getUserByNo(String userNo) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo",userNo);
        Map report = null;
        try {
            report = SoapUtil.sendReport("FXYJ11030","812",parmMap);
        } catch (Exception e) {
            throw new BizException("查看用户角色报错！"+e.getMessage());
        }
        if(!"0".equals((String)report.get("status"))){
            throw new BizException("执行失败,状态码:"+(String)report.get("status"));
        }
        return report;
    }

    @Override
    public Object getProcessingRecords(ProcessingRecordsDo processingRecordsDo) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("beinvted",processingRecordsDo.getBeinvted());
        parmMap.put("creator",processingRecordsDo.getCreator());
        parmMap.put("invtkey",processingRecordsDo.getInvtkey());
        parmMap.put("peocessdes",processingRecordsDo.getPeocessdes());
        parmMap.put("taskkey",processingRecordsDo.getTaskkey());
        parmMap.put("topic",processingRecordsDo.getTopic());
        parmMap.put("verdict",processingRecordsDo.getVerdict());
        parmMap.put("CONTENTID",processingRecordsDo.getCONTENTID());
        parmMap.put("BUSISTARTDATE",processingRecordsDo.getBUSISTARTDATE());
        parmMap.put("BUSISERIALNO",processingRecordsDo.getBUSISERIALNO());

        Map report = null;
        try {
            report = SoapUtil.sendReport("FXYJ10025","812",parmMap);
        } catch (Exception e) {
            throw new BizException("查看用户角色报错！"+e.getMessage());
        }
        if(!"0".equals((String)report.get("status"))){
            throw new BizException("执行失败,状态码:"+(String)report.get("status"));
        }
        return report;
    }

    @Override
    public Object getRecordByKey(String taskkey) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("taskkey",taskkey);
        Map report = null;
        try {
            report = SoapUtil.sendReport("FXYJ10026","812",parmMap);
        } catch (Exception e) {
            throw new BizException("查看用户角色报错！"+e.getMessage());
        }
        if(!"0".equals((String)report.get("status"))){
            throw new BizException("执行失败,状态码:"+(String)report.get("status"));
        }
        return report;
    }

    @Override
    public Object getMyTaskByNo(String userNo) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo",userNo);
        Map report = null;
        try {
            report = SoapUtil.sendReport("FXYJ10028","812",parmMap);
        } catch (Exception e) {
            throw new BizException("查看用户角色报错！"+e.getMessage());
        }
        if(!"0".equals((String)report.get("status"))){
            throw new BizException("执行失败,状态码:"+(String)report.get("status"));
        }
        return report;
    }

    @Override
    public Object getUpdateByKey(String alertKey) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("alertKey",alertKey);
        Map report = null;
        try {
            report = SoapUtil.sendReport("FXYJ10031","812",parmMap);
        } catch (Exception e) {
            throw new BizException("查看用户角色报错！"+e.getMessage());
        }
        if(!"0".equals((String)report.get("status"))){
            throw new BizException("执行失败,状态码:"+(String)report.get("status"));
        }
        return report;
    }

    @Override
    public Object returnCounterNo(String userNo) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo",userNo);
        Map report = null;
        try {
            report = SoapUtil.sendReport("FXYJ10030","812",parmMap);
        } catch (Exception e) {
            throw new BizException("柜员号接口！"+e.getMessage());
        }
        if(!"0".equals((String)report.get("status"))){
            throw new BizException("执行失败,状态码:"+(String)report.get("status"));
        }
        return report;
    }

    @Override
    public Object getUserList(UserListDo userListDo) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo",userListDo.getUserNo());
        parmMap.put("limit",userListDo.getLimit());
        parmMap.put("offset",userListDo.getOffset());
        Map report = null;
        try {
            report = SoapUtil.sendReport("FXYJ10032","812",parmMap);
        } catch (Exception e) {
            throw new BizException("柜员号接口！"+e.getMessage());
        }
        if(!"0".equals((String)report.get("status"))){
            throw new BizException("执行失败,状态码:"+(String)report.get("status"));
        }
        return report;
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
