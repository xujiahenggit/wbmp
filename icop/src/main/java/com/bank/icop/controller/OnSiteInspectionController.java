package com.bank.icop.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.core.entity.TokenUserInfo;
import com.bank.icop.dto.CheckItemCheckSubmitDTO;
import com.bank.icop.dto.CheckProblemDTO;
import com.bank.icop.service.OnSiteInspectionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 现场检查接口Controller
 * ClassName: OnSiteInspectionController
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/06/22 17:17:23
 */
@RestController
@RequestMapping("/onSiteInspection")
@Api(tags = "现场检查接口")
public class OnSiteInspectionController extends BaseIcopController {

    @Resource
    HttpServletRequest request;

    @Resource
    OnSiteInspectionService onSiteInspectionService;

    @ApiOperation("获取现场检查任务列表-代办【FXYJ11001】")
    @GetMapping("/inspectionTaskList")
    public List inspectionTaskList() {
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        return onSiteInspectionService.inspectionTaskList(tokenUserInfo.getUserId());
    }

    @ApiOperation("获取运营检查大项展示列表【FXYJ11002】")
    @GetMapping("/taskItemList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务ID", required = false, defaultValue = "", dataType = "String"),
            @ApiImplicitParam(name = "createOrgId", value = "创建机构", required = false, defaultValue = "", dataType = "String"),
            @ApiImplicitParam(name = "executeOrgId", value = "执行机构", required = false, defaultValue = "", dataType = "String"),
            @ApiImplicitParam(name = "taskName", value = "任务名称", required = false, defaultValue = "", dataType = "String"),
            @ApiImplicitParam(name = "taskStartDate", value = "任务开始时间", required = false, defaultValue = "", dataType = "String"),
            @ApiImplicitParam(name = "taskEndDate", value = "任务结束时间", required = false, defaultValue = "", dataType = "String")
    })
    public List taskItemList(@RequestParam(value = "taskId", required = false) String taskId, @RequestParam(value = "createOrgId", required = false) String createOrgId, @RequestParam(value = "executeOrgId",
            required = false) String executeOrgId, @RequestParam(value = "taskName", required = false) String taskName, @RequestParam(value = "taskStartDate", required = false) String taskStartDate, @RequestParam(value = "taskEndDate", required = false) String taskEndDate) {
        String userId = getCurrentUserId(request);
        return onSiteInspectionService.taskItemList(userId, taskId, createOrgId, executeOrgId, taskName, taskStartDate, taskEndDate);
    }

    @ApiOperation("检查任务执行展示【FXYJ11003】")
    @GetMapping("/registerCheck")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskItemId", value = "检查任务项ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "inspectionInfoId", value = "执行检查信息ID", required = false, defaultValue = "", dataType = "String")
    })
    public Object registerCheck(@RequestParam(value = "taskItemId") String taskItemId, @RequestParam(value = "inspectionInfoId") String inspectionInfoId) {
        return onSiteInspectionService.registerCheck(getCurrentUserId(request), taskItemId, inspectionInfoId);
    }

    @ApiOperation("检查要点查看【FXYJ11004】")
    @GetMapping("/check/{sunpointkey}/{taskpk}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sunpointkey", value = "检查key", required = true, dataType = "String"),
            @ApiImplicitParam(name = "taskpk", value = "任务编号", required = true, defaultValue = "", dataType = "String")
    })
    public String check(@PathVariable("sunpointkey") String taskItemId, @PathVariable("taskpk") String inspectionInfoId) {
        return onSiteInspectionService.check(taskItemId, inspectionInfoId);
    }

    @ApiOperation("检查问题编辑-查询【FXYJ11005】")
    @GetMapping("/problemEdit/{pk}/{taskpk}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pk", value = "检查key", required = true, dataType = "String"),
            @ApiImplicitParam(name = "taskpk", value = "任务编号", required = true, defaultValue = "", dataType = "String")
    })
    public Object problemEdit(@PathVariable("pk") String pk, @PathVariable("taskpk") String taskpk) {
        return onSiteInspectionService.problemEdit(pk, taskpk);
    }

    @ApiOperation("检查任务保存【FXYJ11006】")
    @PostMapping("/checkTaskSave")
    @ApiImplicitParam(name = "jsonstr", value = "任务编号", required = true, defaultValue = "", dataType = "String")
    public Object checkTaskSave(String jsonstr) {
        return onSiteInspectionService.checkTaskSave(getCurrentUserId(request), jsonstr);
    }

    @ApiOperation("检查任务的提交【FXYJ11007】")
    @GetMapping("/checkTaskSubmit/{pk}")
    @ApiImplicitParam(name = "pk", value = "检查key", required = true, dataType = "String")
    public Object checkTaskSubmit(@PathVariable("pk") String pk) {
        return onSiteInspectionService.checkTaskSubmit(getCurrentUserId(request), pk);
    }

    @ApiOperation("检查任务查看列表【FXYJ11008】")
    @GetMapping("/taskList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "runorgankey", value = "执行机构", required = false, defaultValue = "", dataType = "String"),
            @ApiImplicitParam(name = "taskName", value = "任务名称", required = false, defaultValue = "", dataType = "String"),
            @ApiImplicitParam(name = "taskStartDate", value = "任务开始时间", required = false, defaultValue = "", dataType = "String"),
            @ApiImplicitParam(name = "taskEndDate", value = "任务结束时间", required = false, defaultValue = "", dataType = "String")
    })
    public Object taskList(@RequestParam(value = "runorgankey", required = false) String runorgankey, @RequestParam(value = "taskName", required = false) String taskName, @RequestParam(value = "taskStartDate", required = false) String taskStartDate, @RequestParam(value = "taskEndDate",
            required = false) String taskEndDate) {
        String userId = getCurrentUserId(request);
        return onSiteInspectionService.taskList(userId, runorgankey, taskName, taskStartDate, taskEndDate);
    }

    @ApiOperation("检查内容详细查看【FXYJ11009】")
    @GetMapping("/checkDetail/{taskpk}")
    @ApiImplicitParam(name = "taskpk", value = "任务编号", required = true, dataType = "String")
    public Object checkDetail(@PathVariable("taskpk") String taskpk) {
        String userId = getCurrentUserId(request);
        return onSiteInspectionService.checkDetail(userId, taskpk);
    }

    @ApiOperation("检查问题查看列表【FXYJ11010】")
    @GetMapping("/problemList/{taskpk}")
    @ApiImplicitParam(name = "taskpk", value = "任务编号", required = true, dataType = "String")
    public Object problemList(@PathVariable("taskpk") String taskpk) {
        String userId = getCurrentUserId(request);
        return onSiteInspectionService.problemList(userId, taskpk);
    }

    @ApiOperation("检查问题审核列表【FXYJ11011】")
    @GetMapping("/checkList")
    public Object checkList() {
        String userId = getCurrentUserId(request);
        return onSiteInspectionService.checkList(userId);
    }

    @ApiOperation("检查内容审核列表【FXYJ11012】")
    @GetMapping("/contentList/{taskpk}")
    @ApiImplicitParam(name = "taskpk", value = "任务编号", required = true, dataType = "String")
    public Object contentList(@PathVariable("taskpk") String taskpk) {
        String userId = getCurrentUserId(request);
        return onSiteInspectionService.contentList(userId, taskpk);
    }

    @ApiOperation("检查内容详细审核【FXYJ11013】")
    @GetMapping("/contentCheck/{pk}")
    @ApiImplicitParam(name = "pk", value = "检查key", required = true, dataType = "String")
    public Object contentCheck(@PathVariable("pk") String pk) {
        return onSiteInspectionService.contentCheck(pk);
    }

    @ApiOperation("问题查看【FXYJ11014】")
    @GetMapping("/problemView/{sunpointkey}/{qpk}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sunpointkey", value = "要点KEY", required = true, dataType = "String"),
            @ApiImplicitParam(name = "qpk", value = "问题PK", required = true, dataType = "String")
    })
    public Object problemView(@PathVariable("sunpointkey") String sunpointkey, @PathVariable("qpk") String qpk) {
        return onSiteInspectionService.problemView(sunpointkey, qpk);
    }

    @ApiOperation("要点查看【FXYJ11015】")
    @GetMapping("/coreCheck/{key}")
    @ApiImplicitParam(name = "key", value = "要点key", required = true, dataType = "String")
    public Object coreCheck(@PathVariable("key") String key) {
        return onSiteInspectionService.coreCheck(key);
    }

    @ApiOperation("检查子项审核提交【FXYJ11016】")
    @PostMapping("/childCheck")
    @ApiImplicitParam(name = "checkItemCheckSubmitDTO", value = "检查子项审核提交对象", required = true, dataType = "CheckItemCheckSubmitDTO", paramType = "body")
    public Object childCheck(@RequestBody CheckItemCheckSubmitDTO checkItemCheckSubmitDTO) {
        return onSiteInspectionService.childCheck(getCurrentUserId(request), checkItemCheckSubmitDTO);
    }

    @ApiOperation("问题待整改列表【FXYJ11017】")
    @GetMapping("/problemUpdateList")
    public Object problemUpdateList() {
        String userId = getCurrentUserId(request);
        return onSiteInspectionService.problemUpdateList(userId);
    }

    @ApiOperation("整改待审核列表【FXYJ11018】")
    @GetMapping("/updateCheckList")
    public Object updateCheckList() {
        String userId = getCurrentUserId(request);
        return onSiteInspectionService.updateCheckList(userId);
    }

    @ApiOperation("问题整改展示界面【FXYJ11019】")
    @GetMapping("/problemRectifyInfo/{key}")
    @ApiImplicitParam(name = "key", value = "整改PK", required = true, dataType = "String")
    public Object problemRectifyInfo(@PathVariable("key") String key) {
        return onSiteInspectionService.problemRectifyInfo(getCurrentUserId(request), key);
    }

    @ApiOperation("整改审批展示【FXYJ11020")
    @GetMapping("/feedbackView/{key}")
    @ApiImplicitParam(name = "cpk", value = "整改PK", required = false, dataType = "String")
    public Object feedbackView(@PathVariable("cpk") String cpk) {
        return onSiteInspectionService.feedbackView(getCurrentUserId(request), cpk);
    }

    @ApiOperation("整改反馈说明保存【FXYJ11021】")
    @PostMapping("/feedbackSave")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "整改PK", required = true, dataType = "String"),
            @ApiImplicitParam(name = "feedbackdes", value = "整改反馈说明", required = true, dataType = "String")
    })
    public Object feedbackSave(String key, String feedbackdes) {
        return onSiteInspectionService.feedbackSave(getCurrentUserId(request), key, feedbackdes);
    }

    @ApiOperation("整改提交【FXYJ11022】")
    @PostMapping("/feedbackSubmit")
    @ApiImplicitParam(name = "cpk", value = "整改PK", required = true, dataType = "String")
    public Object feedbackSubmit(String cpk) {
        return onSiteInspectionService.feedbackSubmit(getCurrentUserId(request), cpk);
    }

    @ApiOperation("整改审批提交【FXYJ11023】")
    @PostMapping("/feedbackCheckSubmit")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cpk", value = "整改PK", required = true, dataType = "String"),
            @ApiImplicitParam(name = "decision", value = "决策方式", required = true, dataType = "String"),
            @ApiImplicitParam(name = "approvelog", value = "审批意见", required = true, dataType = "String")
    })
    public Object feedbackCheckSubmit(String cpk, String decision, String approvelog) {
        return onSiteInspectionService.feedbackCheckSubmit(getCurrentUserId(request), cpk, decision, approvelog);
    }

    @ApiOperation("检查任务详细查看【FXYJ11024】")
    @GetMapping("/checkTaskDetail")
    @ApiImplicitParam(name = "taskpk", value = "任务编号", required = true, dataType = "String")
    public Object checkTaskDetail(String taskpk) {
        return onSiteInspectionService.checkTaskDetail(getCurrentUserId(request), taskpk);
    }

    @ApiOperation("问题详细查看【FXYJ11025】")
    @GetMapping("/problemDetail")
    @ApiImplicitParam(name = "pk", value = "问题主键", required = true, dataType = "String")
    public Object problemDetail(String pk) {
        return onSiteInspectionService.problemDetail(pk);
    }

    @ApiOperation("任务审核-登记问题反馈【FXYJ11026】")
    @PostMapping("/problemFeedback")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "problemId", value = "问题ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "auditFeedback", value = "问题审核反馈", required = true, dataType = "String")
    })
    public boolean problemFeedback(@RequestParam(value = "problemId") String problemId, @RequestParam(value = "auditFeedback") String auditFeedback) {
        return onSiteInspectionService.problemFeedback(problemId, auditFeedback);
    }

    @ApiOperation("问题编辑保存【FXYJ11027】")
    @PostMapping("/problemEditSave")
    @ApiImplicitParam(name = "checkProblemDTO", value = "检查问题对象", required = true, dataType = "CheckProblemDTO", paramType = "body")
    public boolean problemEditSave(@RequestBody CheckProblemDTO checkProblemDTO) {
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        return onSiteInspectionService.problemEditSave(tokenUserInfo.getUserId(), checkProblemDTO);
    }

    @ApiOperation("获取已处理整改列表【FXYJ11028】")
    @GetMapping("/handledRectifyList")
    public List handledRectifyList() {
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        return onSiteInspectionService.handledRectifyList(tokenUserInfo.getUserId());
    }

    @ApiOperation("已处理整改详细信息查看【FXYJ11029】")
    @GetMapping("/handledRectifyInfo/{rectifyId}")
    @ApiImplicitParam(name = "rectifyId", value = "已处理整改ID", required = true, dataType = "String", paramType = "path")
    public Object handledRectifyInfo(@PathVariable("rectifyId") String rectifyId) {
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        return onSiteInspectionService.handledRectifyInfo(tokenUserInfo.getUserId(), rectifyId);
    }

    @ApiOperation("获取用户所属机构信息【FXYJ11030】")
    @GetMapping("/getUserOfOrgInfo")
    public List getUserOfOrgInfo() {
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        return onSiteInspectionService.getUserOfOrgInfo(tokenUserInfo.getUserId());
    }

    @ApiOperation("获取整改柜员信息【FXYJ11031】")
    @GetMapping("/getRectifyTellerInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tellerId", value = "柜员编号", required = false, defaultValue = "", dataType = "String"),
            @ApiImplicitParam(name = "tellerName", value = "柜员名称", required = false, defaultValue = "", dataType = "String")
    })
    public List getRectifyTellerInfo(@RequestParam(value = "tellerId") String tellerId, @RequestParam(value = "tellerName") String tellerName) {
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        return onSiteInspectionService.getRectifyTellerInfo(tokenUserInfo.getUserId(), tellerId, tellerName);
    }

    @ApiOperation("获取用户所属角色信息【FXYJ11032】")
    @GetMapping("/getUserOfRoleInfo")
    public List getUserOfRoleInfo() {
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        return onSiteInspectionService.getUserOfRoleInfo(tokenUserInfo.getUserId());
    }

    @ApiOperation("检查问题添加-查询【FXYJ11033】")
    @GetMapping("/problemAddQuery/{sunpointkey}/{taskpk}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sunpointkey", value = "检查key", required = true, dataType = "String"),
            @ApiImplicitParam(name = "taskpk", value = "任务Key", required = true, defaultValue = "", dataType = "String")
    })
    public Object problemAddQuery(@PathVariable("sunpointkey") String sunpointkey, @PathVariable("taskpk") String taskpk) {
        return onSiteInspectionService.problemAddQuery(sunpointkey, taskpk);
    }
}
