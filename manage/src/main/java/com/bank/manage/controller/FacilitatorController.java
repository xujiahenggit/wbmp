package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.TokenUserInfo;
import com.bank.manage.dto.FacilitatorDto;
import com.bank.manage.service.WorkSuppleService;
import com.bank.manage.vo.FacilitatorVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/6/24 10:45
 */
@RestController
@RequestMapping("/facili")
@Api(tags = "引导员管理-待办事项")
public class FacilitatorController extends BaseController {

    @Autowired
    private WorkSuppleService workSuppleService;

    @ApiOperation("引导员待办列表")
    @PostMapping("/waitlist")
    public IPage<FacilitatorDto> getWaitList(@RequestBody FacilitatorVo facilitatorVo, HttpServletRequest request){
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        return workSuppleService.getAllWaitList(facilitatorVo,tokenUserInfo);
    }

    @ApiOperation(("引导员已办列表"))
    @PostMapping("/areadyList")
    public IPage<FacilitatorDto> getAreayList(@RequestBody FacilitatorVo facilitatorVo,HttpServletRequest request){
        TokenUserInfo tokenUserInfo=getCurrentUserInfo(request);
        return workSuppleService.getAllAredyList(facilitatorVo,tokenUserInfo);
    }
}
