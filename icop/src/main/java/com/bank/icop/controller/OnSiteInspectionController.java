package com.bank.icop.controller;

import com.bank.core.entity.TokenUserInfo;
import com.bank.icop.dto.CheckProblemDTO;
import com.bank.icop.dto.CheckProcessDTO;
import com.bank.icop.dto.TaskCheckAuditDTO;
import com.bank.icop.service.OnSiteInspectionService;
import com.bank.icop.vo.CheckProblemVO;
import com.bank.icop.vo.OnSiteInspectionTaskCheckVO;
import com.bank.icop.vo.OnSiteInspectionTaskItemVO;
import com.bank.icop.vo.OnSiteInspectionTaskVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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


    @ApiOperation("获取现场检查任务列表")
    @GetMapping("/inspectionTaskList")
    public List<Map> inspectionTaskList() {
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        return onSiteInspectionService.inspectionTaskList(tokenUserInfo.getUserId());
    }

    @ApiOperation("获取运营检查大项展示列表")
    @GetMapping("/taskItemList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务ID", required = false, defaultValue = "", dataType = "String"),
            @ApiImplicitParam(name = "createOrgId", value = "创建机构", required = false, defaultValue = "", dataType = "String"),
            @ApiImplicitParam(name = "executeOrgId", value = "执行机构", required = false, defaultValue = "", dataType = "String"),
            @ApiImplicitParam(name = "taskName", value = "任务名称", required = false, defaultValue = "", dataType = "String"),
            @ApiImplicitParam(name = "taskStartDate", value = "任务开始时间", required = false, defaultValue = "", dataType = "String"),
            @ApiImplicitParam(name = "taskEndDate", value = "任务结束时间", required = false, defaultValue = "", dataType = "String")
    })
    public List<Map> taskItemList(@RequestParam(value = "taskId", required = false) String taskId, @RequestParam(value = "createOrgId", required = false) String createOrgId, @RequestParam(value = "executeOrgId",
            required = false) String executeOrgId, @RequestParam(value = "taskName", required = false) String taskName, @RequestParam(value = "taskStartDate", required = false) String taskStartDate, @RequestParam(value = "taskEndDate", required = false) String taskEndDate) {
        String userId = getCurrentUserId(request);
        return onSiteInspectionService.taskItemList(userId,taskId,createOrgId,executeOrgId,taskName,taskStartDate,taskEndDate);
    }
    @ApiOperation("检查任务执行展示")
    @GetMapping("/registerCheck")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskItemId", value = "检查任务项ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "inspectionInfoId", value = "执行检查信息ID", required = false, defaultValue = "", dataType = "String")
    })
    public Object registerCheck(@RequestParam(value = "taskItemId") String taskItemId, @RequestParam(value = "inspectionInfoId") String inspectionInfoId) {
        return onSiteInspectionService.registerCheck(getCurrentUserId(request),taskItemId,inspectionInfoId);
    }

    @ApiOperation("检查要点查看")
    @GetMapping("/check/{sunpointkey}/{taskpk}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sunpointkey", value = "检查key", required = true, dataType = "String"),
            @ApiImplicitParam(name = "taskpk", value = "任务编号", required = true, defaultValue = "", dataType = "String")
    })
    public boolean check(@PathVariable("sunpointkey") String taskItemId, @PathVariable("taskpk") String inspectionInfoId) {
        return onSiteInspectionService.check(taskItemId,inspectionInfoId);
    }


    @ApiOperation("检查问题编辑")
    @GetMapping("/problemEdit/{pk}/{taskpk}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pk", value = "检查key", required = true, dataType = "String"),
            @ApiImplicitParam(name = "taskpk", value = "任务编号", required = true, defaultValue = "", dataType = "String")
    })
    public Object problemEdit(@PathVariable("pk") String pk,@PathVariable("taskpk") String taskpk) {
        return onSiteInspectionService.problemEdit(pk,taskpk);
    }


    @ApiOperation("检查问题保存")
    @GetMapping("/problemSave")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsonstr", value = "任务编号", required = true, defaultValue = "", dataType = "String")
    })
    public Object problemSave(String jsonstr) {
        return onSiteInspectionService.problemSave(getCurrentUserId(request),jsonstr);
    }


    @ApiOperation("检查任务的提交")
    @GetMapping("/checkTaskSubmit/{pk}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pk", value = "检查key", required = true, dataType = "String")
    })
    public Object checkTaskSubmit(@PathVariable("pk") String pk) {
        return onSiteInspectionService.checkTaskSubmit(getCurrentUserId(request),pk);
    }

    @ApiOperation("检查任务查看列表")
    @PostMapping("/taskList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "runorgankey", value = "执行机构", required = false, defaultValue = "", dataType = "String"),
            @ApiImplicitParam(name = "taskName", value = "任务名称", required = false, defaultValue = "", dataType = "String"),
            @ApiImplicitParam(name = "taskStartDate", value = "任务开始时间", required = false, defaultValue = "", dataType = "String"),
            @ApiImplicitParam(name = "taskEndDate", value = "任务结束时间", required = false, defaultValue = "", dataType = "String")
    })
    public List<Map> taskList(@RequestParam(value = "runorgankey", required = false) String runorgankey, @RequestParam(value = "taskName", required = false) String taskName, @RequestParam(value = "taskStartDate", required = false) String taskStartDate, @RequestParam(value = "taskEndDate", required = false) String taskEndDate) {
        String userId = getCurrentUserId(request);
        return onSiteInspectionService.taskList(userId,runorgankey,taskName,taskStartDate,taskEndDate);
    }

    @ApiOperation("检查内容详细查看")
    @GetMapping("/checkDetail/{taskpk}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskpk", value = "任务编号", required = true, dataType = "String")
    })
    public Object checkDetail(@PathVariable("taskpk") String taskpk) {
        String userId = getCurrentUserId(request);
        return onSiteInspectionService.checkDetail(userId,taskpk);
    }

    @ApiOperation("检查问题查看列表")
    @GetMapping("/problemList/{taskpk}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskpk", value = "任务编号", required = true, dataType = "String")
    })
    public Object problemList(@PathVariable("taskpk") String taskpk) {
        String userId = getCurrentUserId(request);
        return onSiteInspectionService.problemList(userId,taskpk);
    }

    @ApiOperation("检查问题审核列表")
    @GetMapping("/checkList")
    public Object checkList() {
        String userId = getCurrentUserId(request);
        return onSiteInspectionService.checkList(userId);
    }
    @ApiOperation("检查内容审核列表")
    @GetMapping("/contentList/{taskpk}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskpk", value = "任务编号", required = true, dataType = "String")
    })
    public Object contentList(@PathVariable("taskpk") String taskpk) {
        String userId = getCurrentUserId(request);
        return onSiteInspectionService.contentList(userId,taskpk);
    }

    @ApiOperation("检查内容详细审核")
    @GetMapping("/contentCheck/{pk}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pk", value = "检查key", required = true, dataType = "String")
    })
    public Object contentCheck(@PathVariable("pk") String pk) {
        return onSiteInspectionService.contentCheck(pk);
    }
    @ApiOperation("问题查看")
    @GetMapping("/problemView/{sunpointkey}/{qpk}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sunpointkey", value = "要点KEY", required = true, dataType = "String"),
            @ApiImplicitParam(name = "qpk", value = "问题PK", required = true, dataType = "String")
    })
    public Object problemView(@PathVariable("sunpointkey") String sunpointkey,@PathVariable("qpk") String qpk) {
        return onSiteInspectionService.problemView(sunpointkey,qpk);
    }

    @ApiOperation("要点查看")
    @GetMapping("/coreCheck/{key}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "要点key", required = true, dataType = "String")
    })
    public Object coreCheck(@PathVariable("key") String key) {
        return onSiteInspectionService.coreCheck(key);
    }

    @ApiOperation("检查子项审核提交")
    @PostMapping("/childCheck")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskpk", value = "任务ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "feedback", value = "反馈，反馈 0不同意 1同意", required = true, dataType = "String"),
            @ApiImplicitParam(name = "feedbackdt", value = "反馈期限", required = true, dataType = "String"),
            @ApiImplicitParam(name = "feedbackdes", value = "反馈结果", required = true, dataType = "String"),
            @ApiImplicitParam(name = "epk", value = "要点key", required = true, dataType = "String")
    })
    public Object childCheck(String taskpk,String feedback,String feedbackdt,String feedbackdes,String epk) {
        return onSiteInspectionService.childCheck(getCurrentUserId(request),taskpk,feedback,feedbackdt,feedbackdes,epk);
    }

    @ApiOperation("问题待整改列表")
    @GetMapping("/problemUpdateList")
    public Object problemUpdateList() {
        String userId = getCurrentUserId(request);
        return onSiteInspectionService.problemUpdateList(userId);
    }


    @ApiOperation("整改待审核列表")
    @GetMapping("/updateCheckList")
    public Object updateCheckList() {
        String userId = getCurrentUserId(request);
        return onSiteInspectionService.updateCheckList(userId);
    }

    @ApiOperation("问题整改展示界面")
    @GetMapping("/problemUI/{key}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "整改PK", required = true, dataType = "String")
    })
    public Object problemUI(@PathVariable("key") String key) {
        return onSiteInspectionService.problemUI(getCurrentUserId(request),key);
    }


    @ApiOperation("整改审批展示")
    @GetMapping("/feedbackView/{key}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cpk", value = "整改PK", required = false, dataType = "String")
    })
    public Object feedbackView(@PathVariable("cpk") String cpk) {
        return onSiteInspectionService.feedbackView(getCurrentUserId(request),cpk);
    }


    @ApiOperation("整改反馈说明保存")
    @PostMapping("/feedbackSave")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "整改PK", required = true, dataType = "String"),
            @ApiImplicitParam(name = "feedbackdes", value = "整改反馈说明", required = true, dataType = "String")
    })
    public Object feedbackSave(String key,String feedbackdes) {
        return onSiteInspectionService.feedbackSave(getCurrentUserId(request),key,feedbackdes);
    }

    @ApiOperation("整改提交")
    @PostMapping("/feedbackSubmit")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cpk", value = "整改PK", required = true, dataType = "String")
    })
    public Object feedbackSubmit(String cpk) {
        return onSiteInspectionService.feedbackSubmit(getCurrentUserId(request),cpk);
    }

    @ApiOperation("整改审批提交")
    @PostMapping("/feedbackCheckSubmit")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cpk", value = "整改PK", required = true, dataType = "String"),
            @ApiImplicitParam(name = "decision", value = "决策方式", required = true, dataType = "String"),
            @ApiImplicitParam(name = "approvelog", value = "审批意见", required = true, dataType = "String")
    })
    public Object feedbackCheckSubmit(String cpk,String decision,String approvelog) {
        return onSiteInspectionService.feedbackCheckSubmit(getCurrentUserId(request),cpk,decision,approvelog);
    }

    @ApiOperation("检查任务详细查看")
    @GetMapping("/checkTaskDetail")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskpk", value = "任务编号", required = true, dataType = "String")
    })
    public Object checkTaskDetail(String taskpk) {
        return onSiteInspectionService.checkTaskDetail(getCurrentUserId(request),taskpk);
    }


    @ApiOperation("问题详细查看")
    @GetMapping("/problemDetail")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pk", value = "问题主键", required = true, dataType = "String")
    })
    public Object problemDetail(String pk) {
        return onSiteInspectionService.problemDetail(pk);
    }

/*===============================================================================================================================*/


    @ApiOperation("现场检查任务-撤回检查")
    @PutMapping("/recall/{taskItemId}/{inspectionInfoId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskItemId", value = "检查任务项ID", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "inspectionInfoId", value = "执行检查信息ID", required = true, dataType = "String", paramType = "path")
    })
    public boolean recall(@PathVariable("taskItemId") String taskItemId, @PathVariable("inspectionInfoId") String inspectionInfoId) {
        return true;
    }

    @ApiOperation("现场检查任务-检查过程提交")
    @PostMapping("/processSubmit")
    @ApiImplicitParam(name = "checkProcessDTO", value = "检查过程数据", required = true, dataType = "CheckProcessDTO", paramType = "body")
    public boolean processSubmit(@RequestBody CheckProcessDTO checkProcessDTO) {
        return true;
    }

    @ApiOperation("现场检查任务-问题提交")
    @PostMapping("/problemSubmit")
    @ApiImplicitParam(name = "checkProblemDTO", value = "检查问题数据", required = true, dataType = "CheckProblemDTO", paramType = "body")
    public boolean problemSubmit(@RequestBody CheckProblemDTO checkProblemDTO) {
        return true;
    }

    @ApiOperation("现场检查任务-问题删除")
    @DeleteMapping("/problemDelete/{problemId}")
    @ApiImplicitParam(name = "problemId", value = "检查问题ID", required = true, dataType = "String", paramType = "path")
    public boolean problemDelete(@PathVariable("problemId") String problemId) {
        return true;
    }

    @ApiOperation("现场检查任务查看列表")
    @GetMapping("/taskExaminList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "createOrgId", value = "创建机构", required = false, defaultValue = "", dataType = "String"),
            @ApiImplicitParam(name = "executeOrgId", value = "执行机构", required = false, defaultValue = "", dataType = "String"),
            @ApiImplicitParam(name = "taskName", value = "任务名称", required = false, defaultValue = "", dataType = "String"),
            @ApiImplicitParam(name = "taskStartDate", value = "任务开始时间", required = false, defaultValue = "", dataType = "String"),
            @ApiImplicitParam(name = "taskEndDate", value = "任务结束时间", required = false, defaultValue = "", dataType = "String")
    })
    public List<OnSiteInspectionTaskVO> taskExaminList(@RequestParam(value = "createOrgId", required = false) String createOrgId, @RequestParam(value = "executeOrgId", required = false) String executeOrgId, @RequestParam(value = "taskName", required = false) String taskName, @RequestParam(
            value = "taskStartDate", required = false) String taskStartDate, @RequestParam(value = "taskEndDate",
                    required = false) String taskEndDate) {
        List<OnSiteInspectionTaskVO> data = new ArrayList<>();

        return data;
    }

    @ApiOperation("现场检查任务查看-检查内容")
    @GetMapping("/examinContent/{taskId}")
    @ApiImplicitParam(name = "taskId", value = "检查任务ID", required = true, dataType = "String", paramType = "path")
    public List<OnSiteInspectionTaskItemVO> examinContent(@PathVariable("taskId") String taskId) {
        return null;
    }

    @ApiOperation("现场检查任务查看-问题查看")
    @GetMapping("/examinProblem/{taskId}")
    @ApiImplicitParam(name = "taskId", value = "检查任务ID", required = true, dataType = "String", paramType = "path")
    public List<CheckProblemVO> examinProblem(@PathVariable("taskId") String taskId) {
        return null;
    }

    @ApiOperation("现场检查任务-审核列表")
    @GetMapping("/inspectionTaskAuditList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "createOrgId", value = "创建机构", required = false, defaultValue = "", dataType = "String"),
            @ApiImplicitParam(name = "executeOrgId", value = "执行机构", required = false, defaultValue = "", dataType = "String"),
            @ApiImplicitParam(name = "taskName", value = "任务名称", required = false, defaultValue = "", dataType = "String"),
            @ApiImplicitParam(name = "taskStartDate", value = "任务开始时间", required = false, defaultValue = "", dataType = "String"),
            @ApiImplicitParam(name = "taskEndDate", value = "任务结束时间", required = false, defaultValue = "", dataType = "String")
    })
    public List<OnSiteInspectionTaskVO> inspectionTaskAuditList(@RequestParam(value = "createOrgId", required = false) String createOrgId, @RequestParam(value = "executeOrgId",
            required = false) String executeOrgId, @RequestParam(value = "taskName", required = false) String taskName, @RequestParam(value = "taskStartDate", required = false) String taskStartDate, @RequestParam(value = "taskEndDate", required = false) String taskEndDate) {
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);

        List<OnSiteInspectionTaskVO> data = new ArrayList<>();
//        data.add(vo1);
//        data.add(vo2);

        return data;
    }

    @ApiOperation("任务审核-审核已提交的检查项")
    @GetMapping("/auditTaskItemList")
    @ApiImplicitParam(name = "taskId", value = "检查任务ID", required = true, dataType = "String", paramType = "path")
    public List<OnSiteInspectionTaskItemVO> auditTaskItemList(@PathVariable("taskId") String taskId) {
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);

        return null;
    }

    @ApiOperation("任务审核-审核检查")
    @GetMapping("/auditCheckInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskItemId", value = "检查任务项ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "inspectionInfoId", value = "执行检查信息ID", required = true, defaultValue = "", dataType = "String")
    })
    public List<OnSiteInspectionTaskCheckVO> auditCheckInfo(@RequestParam(value = "taskItemId") String taskItemId, @RequestParam(value = "inspectionInfoId") String inspectionInfoId) {
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);

        return null;
    }

    @ApiOperation("任务审核-登记查看问题列表")
    @GetMapping("/examinProblemList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskItemId", value = "检查任务项ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "inspectionInfoId", value = "执行检查信息ID", required = true, defaultValue = "", dataType = "String")
    })
    public List<CheckProblemVO> examinProblemList(@RequestParam(value = "taskItemId") String taskItemId, @RequestParam(value = "inspectionInfoId") String inspectionInfoId) {
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);

        return null;
    }

    @ApiOperation("任务审核-登记问题反馈")
    @PostMapping("/problemFeedback")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "problemId", value = "问题ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "auditFeedback", value = "问题审核反馈", required = true, dataType = "String")
    })
    public boolean problemFeedback(@RequestParam(value = "problemId") String problemId, @RequestParam(value = "auditFeedback") String auditFeedback) {
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);

        return true;
    }

    @ApiOperation("任务审核-审核检查提交")
    @PostMapping("/auditCheckSubmit")
    @ApiImplicitParam(name = "taskCheckAuditDTO", value = "审核检查提交", required = true, dataType = "TaskCheckAuditDTO", paramType = "body")
    public boolean auditCheckSubmit(@RequestBody TaskCheckAuditDTO taskCheckAuditDTO) {
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);

        return true;
    }

}
