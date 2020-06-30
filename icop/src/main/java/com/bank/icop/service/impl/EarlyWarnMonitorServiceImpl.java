package com.bank.icop.service.impl;

import com.bank.icop.dos.*;
import com.bank.icop.service.EarlyWarnMonitorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EarlyWarnMonitorServiceImpl implements EarlyWarnMonitorService {
    @Override
    public Object getdealDatas(String userNo) {
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("typeName","未办结");
        map.put("number","50");
        list.add(map);
        return list;
    }

    @Override
    public Object getT69Alerts(String alertkey) {
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("ALERTKEY","3rgry53eeg5ehyt543");
        map.put("RULEKEY","56g45yGDHHCFy7gctugvcdvghy46e3");
        map.put("RISKLEV_DISP","高风险");
        map.put("ALERTDT","2020-11-22 12:23:00");
        map.put("ORGANKEY","990101");
        map.put("ORGANKEY_DISP","测试机构");
        map.put("DEPT_DISP","ALTER_KEY");
        map.put("CURRDISPOSER","1567900342");
        map.put("LINESTATE","测试处理人");
        map.put("FCETTYPECODE_DISP","某某莫");
        map.put("IS_TS_BIZ","0");
        map.put("DEFLAG_DISP","01");
        map.put("LINESTATE_DISP","1");
        map.put("CUST_CT","12");
        map.put("CUST_CT1","35");
        map.put("CERT_NO","630651965412350213");
        map.put("CJSTATUS_DISP","1");
        map.put("ALERTDESC","隔热分为氛围分为");
        list.add(map);
        return list;
    }

    @Override
    public Object getTaskDetails(String taskkey) {
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("TASKKEY","56GO79FHhuogFTN89GG");
        map.put("TASKNAME","某某某某");
        map.put("FEEDBACKDT","2020-06-29 12:18:20");
        map.put("DEALGROUP","特别组");
        map.put("DEALLEV","1");
        map.put("TASKDESC","测试描述1111");
        list.add(map);
        return list;
    }

    @Override
    public Object modifyTasks(ModifyTasksDo modifyTasksDo) {
        String status = "0";
        return status;
    }

    @Override
    public Object deleteTasks(String taskkey) {
        String status = "0";
        return status;
    }

    @Override
    public Object returnTasks(ReturnTasksDo returnTasksDo) {
        String status = "0";
        return status;
    }

    @Override
    public Object addTaskDatas(AddTaskDatasDo addTaskDatasDo) {
        String status = "0";
        return status;
    }

    @Override
    public Object queryT69alertlogs(String alertkey) {
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("ALERTKEY","48HoGj80hgguGF90");
        map.put("DISPOSER","某某某");
        map.put("ENDDT","2020-06-29 16:35:20");
        map.put("COMMENTS","测试11111");
        list.add(map);
        return list;
    }

    @Override
    public Object queryRuleDatas(String tplakey) {
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("RULENAME","djwoidjqwodjq");
        map.put("RULEDES","测试");
        map.put("RULEKEY","ITM8765");
        map.put("RULETYPE_DISP","11");
        map.put("IMPLWAY_DISP","测试111");
        map.put("GRANULAIRTY_DISP","222");
        map.put("APPLYLEVL_DISP","1");
        map.put("FLAG_DISP","1");
        map.put("CREATETIME","2020-06-30 09:09:09");
        map.put("CREATOR_DISP","某某某");
        map.put("MODIFYTIME","2020-06-30 09:09:09");
        map.put("MODIFYER_DISP","某某某1");
        map.put("RULEDES","1111");
        list.add(map);
        return list;
    }

    @Override
    public Object fileremoveRisk(FilereMoveRiskDo filereMoveRiskDo) {
        String status = "0";
        return status;
    }

    @Override
    public Object fileRiskEvent(FileRiskEventDo fileRiskEventDo) {
        String status = "0";
        return status;
    }

    @Override
    public Object queryTaskLists(String alertkey) {
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("ALERTKEY","8Usk089Kda");
        map.put("CURRSETPNAME","测试111");
        map.put("DUTYCREATE_DT","2020-06-30");
        map.put("NOTICE_FEEDBACK_DT","2020-09-26");
        map.put("DEPT_DISP","测试部1");
        map.put("HANDLEROLE_DISP","测试");
        map.put("REDFLAG","1");
        list.add(map);
        return list;
    }

    @Override
    public Object updateDealRecords(UpdateDealRecordsDo updateDealRecordsDo) {
        String status = "0";
        return status;
    }

    @Override
    public Object deleteDealRecords(String invtkey) {
        String status = "0";
        return status;
    }

    @Override
    public Object submitTasks(SubmitTasksDo submitTasksDo) {
        String status = "0";
        return status;
    }

    @Override
    public Object getT69AlertList(String userNo) {
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("ALERTKEY","8U9jsjai9dha8w");
        map.put("RULEKEY","w344re55");
        map.put("ORGANKEY","测试");
        map.put("ALERTDESC","2020-06-30");
        list.add(map);
        return list;
    }

    @Override
    public Object getReplyDataList(String userNo) {
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("ALERTKEY","Hydwq867Gd");
        map.put("RULEKEY","121wdew1");
        map.put("RISKLEV_DISP","1111");
        map.put("DATEDT","2020-06-30 10:50:20");
        map.put("ALERTDT","2020-06-30 10:50:20");
        map.put("ORGANKEY_DISP","ceshi");
        list.add(map);
        return list;
    }

    @Override
    public Object getNotReplyLists(String userNo) {
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("ALERTKEY","GJ990Hd");
        map.put("RULEKEY","12etrtSDF");
        map.put("RISKLEV_DISP","1");
        map.put("DATEDT","2020-06-30");
        map.put("ALERTDT","2020-06-30");
        map.put("ORGANKEY_DISP","测试");
        list.add(map);
        return list;
    }
}
