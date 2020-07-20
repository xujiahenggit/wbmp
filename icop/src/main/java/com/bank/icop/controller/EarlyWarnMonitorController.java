package com.bank.icop.controller;

import com.bank.icop.dos.*;
import com.bank.icop.dto.TaskListsDto;
import com.bank.icop.service.EarlyWarnMonitorService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;

/**
 * 预警监测接口Controller
 * ClassName: EarlyWarnMonitorController
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/06/22 17:20:16
 */
@RestController
@RequestMapping("/earlyWarnMonitor")
@Api(tags = "预警监测接口")
public class EarlyWarnMonitorController {

    @Autowired
    private EarlyWarnMonitorService earlyWarnMonitorService;

    @GetMapping("/getdealDatas/{userNo}")
    @ApiOperation("代办、未办结、已办数据列表查询接口10001")
    @ApiImplicitParam(name = "userNo",value = "用户编号",required = true,paramType = "path")
    public Object getdealDatas(@PathVariable("userNo") String userNo){
        return earlyWarnMonitorService.getdealDatas(userNo);
    }

    @ApiOperation("预警任务基本信息查询接口10002")
    @PostMapping("/getT69Alerts")
    public Object getT69Alerts(@RequestBody AlertsDo alertsDo){
        return earlyWarnMonitorService.getT69Alerts(alertsDo);
    }

    @ApiOperation("协查组任务查看详情数据查询接口（有、无调查记录)10003")
    @GetMapping("/getTaskDetails/{alertKey}")
    @ApiImplicitParam(name = "alertKey",value = "任务编号",required = true,paramType = "path")
    public Object getTaskDetails(@PathVariable("alertKey") String alertKey){
        return earlyWarnMonitorService.getTaskDetails(alertKey);
    }

    @ApiOperation("协查组任务数据修改接口10004")
    @PostMapping("/modifyTasks")
    @ApiImplicitParam(name = "modifyTasksDo",value = "协查组任务数据修改模型",required = true,paramType = "body", dataType = "ModifyTasksDo")
    public Object modifyTasks(@RequestBody ModifyTasksDo modifyTasksDo){
        return earlyWarnMonitorService.modifyTasks(modifyTasksDo);
    }

    @ApiOperation("协查组任务数据删除接口10005")
    @DeleteMapping("/deleteTasks/{taskkey}")
    @ApiImplicitParam(name = "taskkey",value = "任务编号",required = true,paramType = "path")
    public Object deleteTasks(@PathVariable("taskkey") String taskkey){
        return earlyWarnMonitorService.deleteTasks(taskkey);
    }

    @ApiOperation("协查组任务退回接口（需输入退回意见）10006")
    @PostMapping("/returnTasks")
    @ApiImplicitParam(name = "returnTasksDo",value = "协查组任务退回模型",required = true,paramType = "body", dataType = "ReturnTasksDo")
    public Object returnTasks(@RequestBody ReturnTasksDo returnTasksDo){
        return earlyWarnMonitorService.returnTasks(returnTasksDo);
    }

    @ApiOperation("协查组任务数据新增接口10007")
    @PostMapping("/addTaskDatas")
    @ApiImplicitParam(name = "addTaskDatasDo",value = "协查组任务数据新增接口模型",required = true,paramType = "body", dataType = "AddTaskDatasDo")
    public Object addTaskDatas(@RequestBody AddTaskDatasDo addTaskDatasDo){
        return earlyWarnMonitorService.addTaskDatas(addTaskDatasDo);
    }

    @ApiOperation("查看预警任务日志信息查询接口10008")
    @GetMapping("/queryT69alertlogs/{alertkey}")
    @ApiImplicitParam(name = "alertkey",value = "预警编号",required = true,paramType = "path")
    public Object queryT69alertlogs(@PathVariable("alertkey") String alertkey){
        return earlyWarnMonitorService.queryT69alertlogs(alertkey);
    }

    @ApiOperation("查看规则信息查询接口10009")
    @GetMapping("/queryRuleDatas/{alertKey}")
    @ApiImplicitParam(name = "alertKey",value = "预警编号",required = true,paramType = "path")
    public Object queryRuleDatas(@PathVariable("alertKey") String alertKey){
        return earlyWarnMonitorService.queryRuleDatas(alertKey);
    }

    @ApiOperation("处理方式为排除的归档接口10010")
    @PostMapping("/fileremoveRisk")
    @ApiImplicitParam(name = "filereMoveRiskDo",value = "处理方式为排除的归档模型",required = true,paramType = "body", dataType = "FilereMoveRiskDo")
    public Object fileremoveRisk(@RequestBody FilereMoveRiskDo filereMoveRiskDo){
        return earlyWarnMonitorService.fileremoveRisk(filereMoveRiskDo);
    }

    @ApiOperation("处理方式为登记风险事件的归档接口10011")
    @PostMapping("/fileRiskEvent")
    @ApiImplicitParam(name = "fileRiskEventDo",value = "处理方式为登记风险事件的归档模型",required = true,paramType = "body", dataType = "FileRiskEventDo")
    public Object fileRiskEvent(@RequestBody FileRiskEventDo fileRiskEventDo){
        return earlyWarnMonitorService.fileRiskEvent(fileRiskEventDo);
    }

    @ApiOperation("协查任务基本信息查询接口10018")
    @PostMapping("/queryTaskLists")
    @ApiImplicitParam(name = "taskListsDto",value = "协查任务基本信息查询",required = true,paramType = "body", dataType = "TaskListsDto")
    public Object queryTaskLists(@RequestBody TaskListsDto taskListsDto){
        return earlyWarnMonitorService.queryTaskLists(taskListsDto);
    }

    @ApiOperation("处理记录修改接口10019")
    @PostMapping("/updateDealRecords")
    @ApiImplicitParam(name = "updateDealRecordsDo",value = "处理记录修改模型",required = true,paramType = "body", dataType = "UpdateDealRecordsDo")
    public Object updateDealRecords(@RequestBody UpdateDealRecordsDo updateDealRecordsDo){
        return earlyWarnMonitorService.updateDealRecords(updateDealRecordsDo);
    }

    @ApiOperation("处理记录删除接口10020")
    @DeleteMapping("/deleteDealRecords/{invtkey}")
    @ApiImplicitParam(name = "invtkey",value = "调查编码",required = true,paramType = "path")
    public Object deleteDealRecords(@PathVariable("invtkey") String invtkey){
        return earlyWarnMonitorService.deleteDealRecords(invtkey);
    }

    @ApiOperation("协查组任务调查提交接口10021")
    @PostMapping("/submitTasks")
    @ApiImplicitParam(name = "submitTasksDo",value = "协查组任务调查提交模型",required = true,paramType = "body", dataType = "SubmitTasksDo")
    public Object submitTasks(@RequestBody SubmitTasksDo submitTasksDo){
        return earlyWarnMonitorService.submitTasks(submitTasksDo);
    }

    @ApiOperation("预警发起与识别数据列表接口10022")
    @PostMapping("/getT69AlertList")
    @ApiImplicitParam(name = "alertListDo",value = "预警发起与识别数据列表接口10022",required = true,paramType = "body", dataType = "AlertListDo")
    public Object getT69AlertList(@RequestBody AlertListDo alertListDo){
        return earlyWarnMonitorService.getT69AlertList(alertListDo);
    }

    @ApiOperation("已回复协查数据列表接口10023")
    @PostMapping("/getReplyDataList")
    public Object getReplyDataList(@RequestBody ReplyDataDo replyDataDo){
        return earlyWarnMonitorService.getReplyDataList(replyDataDo);
    }

    @ApiOperation("未回复协查数据列表接口10024")
    @PostMapping("/getNotReplyLists")
    public Object getNotReplyLists(@RequestBody ReplyDataDo replyDataDo){
        return earlyWarnMonitorService.getNotReplyLists(replyDataDo);
    }


    @ApiOperation("已回复协查任务预警任务基本信息查询接口10012")
    @GetMapping("/returnAlerts/{alertkey}")
    @ApiImplicitParam(name = "alertkey",value = "预警编号",required = true,paramType = "path")
    public Object returnAlerts(@PathVariable("alertkey") String alertkey){
        return earlyWarnMonitorService.returnAlerts(alertkey);
    }

    @ApiOperation("已回复协查组任务查看详情数据查询接口（有、无调查记录）10013")
    @GetMapping("/returnReplyLists/{taskkey}")
    @ApiImplicitParam(name = "taskkey",value = "任务编号",required = true,paramType = "path")
    public Object returnReplyLists(@PathVariable("taskkey") String taskkey){
        return earlyWarnMonitorService.returnReplyLists(taskkey);
    }

    @ApiOperation("查看预警任务日志信息查询接口10014")
    @GetMapping("/getAlertLogLists/{alertkey}")
    @ApiImplicitParam(name = "alertkey",value = "预警编号",required = true,paramType = "path")
    public Object getAlertLogLists(@PathVariable("alertkey") String alertkey){
        return earlyWarnMonitorService.getAlertLogLists(alertkey);
    }

    @ApiOperation("查看规则信息查询接口10015")
    @GetMapping("/getTplakeyLists/{tplakey}")
    @ApiImplicitParam(name = "tplakey",value = "主键",required = true,paramType = "path")
    public Object getTplakeyLists(@PathVariable("tplakey") String tplakey){
        return earlyWarnMonitorService.getTplakeyLists(tplakey);
    }

    @ApiOperation("未回复协查组任务查看详情数据查询接口（有、无调查记录）10016")
    @GetMapping("/returnNotReplyLists/{taskkey}")
    @ApiImplicitParam(name = "taskkey",value = "主键",required = true,paramType = "path")
    public Object returnNotReplyLists(@PathVariable("taskkey") String taskkey){
        return earlyWarnMonitorService.returnNotReplyLists(taskkey);
    }

    @ApiOperation("获取角色 11032")
    @GetMapping("/returnRoleLists/{userNo}")
    @ApiImplicitParam(name = "userNo",value = "主键",required = true,paramType = "path")
    public Object returnRoleLists(@PathVariable("userNo") String userNo){
        return earlyWarnMonitorService.returnRoleLists(userNo);
    }

    @ApiOperation("柜员号 10030")
    @GetMapping("/returnCounterNo/{userNo}")
    @ApiImplicitParam(name = "userNo",value = "主键",required = true,paramType = "path")
    public Object returnCounterNo(@PathVariable("userNo") String userNo){
        return earlyWarnMonitorService.returnCounterNo(userNo);
    }

    @ApiOperation("调查任务详情接口029")
    @GetMapping("/getTaskByCode/{taskkey}")
    @ApiImplicitParam(name = "taskkey",value = "主键",required = true,paramType = "path")
    public Object getTaskByCode(@PathVariable("taskkey") String taskkey){
        return earlyWarnMonitorService.getTaskByCode(taskkey);
    }

    @ApiOperation("用户所属机构11030")
    @GetMapping("/getUserByNo/{userNo}")
    @ApiImplicitParam(name = "userNo",value = "用户编号",required = true,paramType = "path")
    public Object getUserByNo(@PathVariable("userNo") String userNo){
        return earlyWarnMonitorService.getUserByNo(userNo);
    }

    @ApiOperation("处理记录保存接口10025")
    @PostMapping("/getProcessingRecords")
    public Object getProcessingRecords(@RequestBody ProcessingRecordsDo processingRecordsDo){
        return earlyWarnMonitorService.getProcessingRecords(processingRecordsDo);
    }

    @ApiOperation("处理记录列表接口10026")
    @GetMapping("/getRecordByKey/{taskkey}")
    @ApiImplicitParam(name = "taskkey",value = "",required = true,paramType = "path")
    public Object getRecordByKey(@PathVariable("taskkey") String taskkey){
        return earlyWarnMonitorService.getRecordByKey(taskkey);
    }

    @ApiOperation("我的任务接口10028")
    @GetMapping("/getMyTaskByNo/{userNo}")
    @ApiImplicitParam(name = "userNo",value = "用户编号",required = true,paramType = "path")
    public Object getMyTaskByNo(@PathVariable("userNo") String userNo){
        return earlyWarnMonitorService.getMyTaskByNo(userNo);
    }


    @ApiOperation("点修改查看接口031")
    @GetMapping("/getUpdateByKey/{alertKey}")
    @ApiImplicitParam(name = "alertKey",value = "预警编号",required = true,paramType = "path")
    public Object getUpdateByKey(@PathVariable("alertKey") String alertKey){
        return earlyWarnMonitorService.getUpdateByKey(alertKey);
    }

    @ApiOperation("用户所属机构032")
    @PostMapping("/getUserList")
    public Object getUserList(@RequestBody UserListDo userListDo){
        return earlyWarnMonitorService.getUserList(userListDo);
    }
}
