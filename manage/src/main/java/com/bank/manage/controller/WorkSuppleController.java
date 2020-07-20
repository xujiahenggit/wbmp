package com.bank.manage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.FileDo;
import com.bank.core.entity.TokenUserInfo;
import com.bank.log.annotation.SystemLog;
import com.bank.manage.dto.WorkSuppleDto;
import com.bank.manage.service.WorkSuppleService;
import com.bank.manage.vo.WorkSupplePassRejectVo;
import com.bank.manage.vo.WorkSuppleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author: Andy
 * @Date: 2020/5/28 16:30
 */
@RestController
@RequestMapping("/workSupple")
@Api(tags = "网点引导员——加班申请接口")
public class WorkSuppleController extends BaseController {

    @Autowired
    private WorkSuppleService workSuppleService;

    @SystemLog(logModul = "加班申请", logType = "待办", logDesc = "加班申请")
    @ApiOperation("待办列表")
    @PostMapping("/waitlist")
    public IPage<WorkSuppleDto> getWaitList(@RequestBody WorkSuppleQueryVo workSuppleQueryVo) {
        return workSuppleService.getWaitList(workSuppleQueryVo);
    }

    @SystemLog(logModul = "加班申请", logType = "已办", logDesc = "已办列表")
    @ApiOperation("已办列表")
    @PostMapping("/passlist")
    public IPage<WorkSuppleDto> getPassList(@RequestBody WorkSuppleQueryVo workSuppleQueryVo) {
        return workSuppleService.getPassList(workSuppleQueryVo);
    }

    @SystemLog(logModul = "加班申请", logType = "通过", logDesc = "审核通过")
    @ApiOperation("审核通过")
    @PostMapping("/pass")
    public Boolean passProcess(@RequestBody WorkSupplePassRejectVo workSupplePassRejectVo, HttpServletRequest request) {
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        return workSuppleService.passProcess(workSupplePassRejectVo, tokenUserInfo);
    }

    @SystemLog(logModul = "加班申请", logType = "驳回", logDesc = "驳回申请")
    @ApiOperation("驳回申请")
    @PostMapping("/reject")
    public Boolean rejectProcess(@RequestBody WorkSupplePassRejectVo workSupplePassRejectVo, HttpServletRequest request) {
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        return workSuppleService.rejectProcess(workSupplePassRejectVo, tokenUserInfo);
    }

    @SystemLog(logModul = "加班申请", logType = "详情", logDesc = "获取详情")
    @GetMapping("/info/{workSuppleId}")
    @ApiOperation("获取详情")
    public WorkSuppleDto getDetailInfo(@PathVariable Integer workSuppleId) {
        return workSuppleService.getDetailInfo(workSuppleId);
    }

    @SystemLog(logModul = "加班申请", logType = "上传", logDesc = "加班证明文件上传")
    @PostMapping("/uploadworkSupplefile")
    @ApiOperation(value = "加班证明文件上传")
    public FileDo upFileForShare(@RequestParam(value = "file") MultipartFile file) {
        FileDo fileDo = workSuppleService.uploadFile(file);
        return fileDo;
    }

    @SystemLog(logModul = "加班申请", logType = "新增", logDesc = "新增加班申请")
    @PostMapping("/save")
    @ApiOperation(value = "新增加班申请")
    public boolean saveWorkSupple(@Validated @RequestBody WorkSuppleDto workSuppleDto) {
        return workSuppleService.saveWorkSupple(workSuppleDto);
    }
}
