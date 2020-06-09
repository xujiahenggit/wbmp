package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.TokenUserInfo;
import com.bank.manage.dos.PartorlProcessDO;
import com.bank.manage.service.PartorlProcessService;
import com.bank.manage.vo.PartorlProcessQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * @Author: Andy
 * @Date: 2020/5/27 21:07
 */
@Api(tags = "大堂经理巡查待办")
@RestController
@RequestMapping("/partorlprocess")
public class PartorlProcessController extends BaseController {

    @Autowired
    private PartorlProcessService partorlProcessService;

    @ApiOperation("待办列表")
    @PostMapping("/waitlist")
    public IPage<PartorlProcessDO> getWaitList(PartorlProcessQueryVo partorlProcessQueryVo, HttpServletRequest request){
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        IPage<PartorlProcessDO> waitList=partorlProcessService.getWaitList(partorlProcessQueryVo,tokenUserInfo);
        return waitList;
    }

    @ApiOperation("已办列表")
    @PostMapping("/aredylist")
    public IPage<PartorlProcessDO> getAreadyList(PartorlProcessQueryVo partorlProcessQueryVo, HttpServletRequest request){
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        IPage<PartorlProcessDO> areadyList=partorlProcessService.getAreadyList(partorlProcessQueryVo,tokenUserInfo);
        return areadyList;
    }

    @ApiOperation("获取待办数目")
    @GetMapping("/num")
    public int getWaitListNum(HttpServletRequest request){
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        int num=partorlProcessService.getWaitListNum(tokenUserInfo);
        return num;
    }
}
