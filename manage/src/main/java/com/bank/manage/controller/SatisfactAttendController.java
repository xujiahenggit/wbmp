package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.TokenUserInfo;
import com.bank.manage.dto.SatisfactAssessmentActDto;
import com.bank.manage.dto.SatisfactAttendDto;
import com.bank.manage.service.SatisfactAttendService;
import com.bank.manage.vo.SatisfactAttendQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Andy
 * @Date: 2020/5/30 11:33
 */
@RestController
@RequestMapping("/satisfactattend")
@Api(tags = "网点引导员——满意度审核接口")
public class SatisfactAttendController extends BaseController {

    @Autowired
    private SatisfactAttendService satisfactAttendService;

    @ApiOperation("待办列表")
    @PostMapping("/waitlist")
    public IPage<SatisfactAttendDto> getWaitList(@RequestBody SatisfactAttendQueryVo satisfactAttendQueryVo, HttpServletRequest request){
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        return satisfactAttendService.getWaitList(satisfactAttendQueryVo,tokenUserInfo);
    }

    @ApiOperation("已办列表")
    @PostMapping("/areadylist")
    public IPage<SatisfactAttendDto> getAreadyList(@RequestBody SatisfactAttendQueryVo satisfactAttendQueryVo,HttpServletRequest request){
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        return satisfactAttendService.getAreadyList(satisfactAttendQueryVo,tokenUserInfo);
    }

    @GetMapping("/info/{satisfactAttendId}")
    @ApiOperation("获取详细信息")
    public SatisfactAssessmentActDto getInfo(@PathVariable Integer satisfactAttendId){
        return satisfactAttendService.SelectSatisfactInfo(satisfactAttendId);
    }

    @PostMapping("/submit")
    @ApiOperation("提交")
    public boolean submit(@RequestBody SatisfactAssessmentActDto satisfactAssessmentActDto,HttpServletRequest request){
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        return satisfactAttendService.submit(satisfactAssessmentActDto,tokenUserInfo);
    }
}