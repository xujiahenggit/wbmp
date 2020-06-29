package com.bank.icop.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.core.entity.TokenUserInfo;
import com.bank.core.utils.PropertyUtil;
import com.bank.icop.dto.CheckProblemDTO;
import com.bank.icop.dto.CheckProcessDTO;
import com.bank.icop.dto.TaskCheckAuditDTO;
import com.bank.icop.vo.CheckProblemVO;
import com.bank.icop.vo.OnSiteInspectionTaskCheckVO;
import com.bank.icop.vo.OnSiteInspectionTaskItemVO;
import com.bank.icop.vo.OnSiteInspectionTaskVO;

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

    private OnSiteInspectionTaskVO vo1 = new OnSiteInspectionTaskVO("1", "2019", "20200202-3214", "常规任务（系统自动生成）", "现场检查任务", "社区支行行长日检查任务", LocalDateTime.now(), LocalDateTime.now(), 2, LocalDateTime.now(), "", "", "");

    private OnSiteInspectionTaskVO vo2 = new OnSiteInspectionTaskVO("2", "2020", "20200202-3215", "常规任务（系统自动生成）", "现场检查任务", "2020结算监督员周检查任务", LocalDateTime.now(), LocalDateTime.now(), 3, LocalDateTime.now(), "", "", "");

    @ApiOperation("获取现场检查任务")
    @GetMapping("/inspectionTaskList")
    public List<OnSiteInspectionTaskVO> inspectionTaskList() {
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);

        List<OnSiteInspectionTaskVO> data = new ArrayList<>();
        data.add(vo1);
        data.add(vo2);

        return data;
    }

    @ApiOperation("获取现场检查任务任务项列表")
    @GetMapping("/taskItemList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务ID", required = false, defaultValue = "", dataType = "String"),
            @ApiImplicitParam(name = "createOrgId", value = "创建机构", required = false, defaultValue = "", dataType = "String"),
            @ApiImplicitParam(name = "executeOrgId", value = "执行机构", required = false, defaultValue = "", dataType = "String"),
            @ApiImplicitParam(name = "taskName", value = "任务名称", required = false, defaultValue = "", dataType = "String"),
            @ApiImplicitParam(name = "taskStartDate", value = "任务开始时间", required = false, defaultValue = "", dataType = "String"),
            @ApiImplicitParam(name = "taskEndDate", value = "任务结束时间", required = false, defaultValue = "", dataType = "String")
    })
    public List<OnSiteInspectionTaskItemVO> taskItemList(@RequestParam(value = "taskId", required = false) String taskId, @RequestParam(value = "createOrgId", required = false) String createOrgId, @RequestParam(value = "executeOrgId",
            required = false) String executeOrgId, @RequestParam(value = "taskName", required = false) String taskName, @RequestParam(value = "taskStartDate", required = false) String taskStartDate, @RequestParam(value = "taskEndDate", required = false) String taskEndDate) {
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);

        List<OnSiteInspectionTaskItemVO> data = new ArrayList<>();
        OnSiteInspectionTaskItemVO itemvo1 = new OnSiteInspectionTaskItemVO();
        OnSiteInspectionTaskItemVO itemvo2 = new OnSiteInspectionTaskItemVO();
        OnSiteInspectionTaskItemVO itemvo3 = new OnSiteInspectionTaskItemVO();
        OnSiteInspectionTaskItemVO itemvo4 = new OnSiteInspectionTaskItemVO();
        PropertyUtil.copyProperties(vo1, itemvo1);
        PropertyUtil.copyProperties(vo1, itemvo2);
        PropertyUtil.copyProperties(vo2, itemvo3);
        PropertyUtil.copyProperties(vo2, itemvo4);

        itemvo1.setTaskItemId("1");
        itemvo1.setTaskItemName("同城清算管理");
        itemvo1.setInspectionInfoId("");
        itemvo1.setState("00");
        itemvo1.setStateName("新建");

        itemvo2.setTaskItemId("2");
        itemvo2.setTaskItemName("同城清算管理");
        itemvo2.setInspectionInfoId("2");
        itemvo2.setState("01");
        itemvo2.setStateName("执行中");

        itemvo3.setTaskItemId("3");
        itemvo3.setTaskItemName("同城清算管理");
        itemvo3.setInspectionInfoId("3");
        itemvo3.setState("02");
        itemvo3.setStateName("未审批");

        itemvo4.setTaskItemId("4");
        itemvo4.setTaskItemName("同城清算管理");
        itemvo4.setInspectionInfoId("4");
        itemvo4.setState("03");
        itemvo4.setStateName("已退回");

        if (StringUtils.isBlank(taskId)) {
            data.add(itemvo1);
            data.add(itemvo2);
            data.add(itemvo3);
            data.add(itemvo4);
        }
        else if (StringUtils.equals(taskId, "1")) {
            data.add(itemvo1);
            data.add(itemvo2);
        }
        else {
            data.add(itemvo3);
            data.add(itemvo4);
        }
        return data;
    }

    @ApiOperation("现场检查任务-登记检查")
    @GetMapping("/registerCheck")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskItemId", value = "检查任务项ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "inspectionInfoId", value = "执行检查信息ID", required = false, defaultValue = "", dataType = "String")
    })
    public List<OnSiteInspectionTaskCheckVO> registerCheck(@RequestParam(value = "taskItemId") String taskItemId, @RequestParam(value = "inspectionInfoId") String inspectionInfoId) {
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);

        return null;
    }

    @ApiOperation("现场检查任务-提交")
    @PutMapping("/submit/{taskItemId}/{inspectionInfoId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskItemId", value = "检查任务项ID", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "inspectionInfoId", value = "执行检查信息ID", required = true, dataType = "String", paramType = "path")
    })
    public boolean submit(@PathVariable("taskItemId") String taskItemId, @PathVariable("inspectionInfoId") String inspectionInfoId) {
        return true;
    }

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

    @ApiOperation("现场检查任务-问题编辑")
    @PutMapping("/problemEdit")
    @ApiImplicitParam(name = "checkProblemDTO", value = "检查问题数据", required = true, dataType = "CheckProblemDTO", paramType = "body")
    public boolean problemEdit(@RequestBody CheckProblemDTO checkProblemDTO) {
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
        data.add(vo1);

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
        data.add(vo1);
        data.add(vo2);

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
