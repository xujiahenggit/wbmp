package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.TokenUserInfo;
import com.bank.manage.dos.MonthAttendDO;
import com.bank.manage.dto.MonthAttendDto;
import com.bank.manage.service.MonthAttendService;
import com.bank.manage.vo.MonthAttendPassRejectVo;
import com.bank.manage.vo.MonthAttendQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Andy
 * @Date: 2020/5/29 16:36
 */
@RestController
@RequestMapping("/monthattend")
@Api(tags = "网点引导员——月度考勤管理")
public class MonthAttendController extends BaseController {

    @Autowired
    private MonthAttendService monthAttendService;

    @PostMapping("/wait")
    @ApiOperation("待办列表")
    public IPage<MonthAttendDO> getWaitList(@RequestBody MonthAttendQueryVo monthAttendQueryVo){
        return monthAttendService.getWaitList(monthAttendQueryVo);
    }

    @PostMapping("/aready")
    @ApiOperation("已办列表")
    public IPage<MonthAttendDO> getAreadyList(@RequestBody MonthAttendQueryVo monthAttendQueryVo){
        return monthAttendService.getAreadyList(monthAttendQueryVo);
    }

    @PostMapping("/pass")
    @ApiOperation("审核通过")
    public boolean passMonthAttend(@RequestBody MonthAttendPassRejectVo monthAttendPassRejectVo, HttpServletRequest request){
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        return monthAttendService.passMonthAttend(monthAttendPassRejectVo,tokenUserInfo);
    }

    @PostMapping("/reject")
    @ApiOperation("驳回审批")
    public boolean rejectMonthAttend(@RequestBody MonthAttendPassRejectVo monthAttendPassRejectVo, HttpServletRequest request){
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        return monthAttendService.rejectMonthAttend(monthAttendPassRejectVo,tokenUserInfo);
    }

    @GetMapping("/info/{monthAttendId}")
    @ApiOperation("获取详情")
    public MonthAttendDto getInfo(@PathVariable Integer monthAttendId){
        return monthAttendService.getInfo(monthAttendId);
    }
}
