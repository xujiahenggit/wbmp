package com.bank.manage.controller;

import com.bank.auth.base.BaseController;
import com.bank.manage.service.HappyService;
import com.bank.manage.vo.HappyParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 快乐服务报表统计
 *
 * @author
 * @date 2020-05-19
 */
@Api(tags = "快乐服务---报表统计")
@RestController
@RequestMapping("/happy")
@Slf4j
public class HappyController extends BaseController {
    @Resource
    private HttpServletRequest httpServletRequest;

    @Resource
    private HappyService happyService;

    @ApiOperation(value = "服务水平情况统计", notes = "可选参数：year，quarter，orgids，networks；没有值，前端传{}对象")
    @PostMapping("/service")
    public Object serviceLevelStatus(@RequestBody HappyParam param) {
        param.setUserId(getCurrentUserInfo(httpServletRequest).getUserId());
        return happyService.serviceLevelStatus(param);
    }


    @ApiOperation(value = "星级情况统计", notes = "可选参数：year，quarter，networks")
    @PostMapping("/star")
    public Object starStatus(@RequestBody HappyParam param) {
        param.setUserId(getCurrentUserInfo(httpServletRequest).getUserId());
        return happyService.starStatus(param);
    }


    @ApiOperation(value = "考核情况统计", notes = "可选参数：year，quarter，orgids；没有值，前端传{}对象")
    @PostMapping("/check")
    public Object checkStatus(@RequestBody HappyParam param) {
        param.setUserId(getCurrentUserInfo(httpServletRequest).getUserId());
        return happyService.checkStatus(param);
    }


    @ApiOperation(value = "扣分情况统计", notes = "可选参数：year，quarter，orgids；没有值，前端传{}对象")
    @PostMapping("/deduct")
    public Object deductStatus(@RequestBody HappyParam param) {
        param.setUserId(getCurrentUserInfo(httpServletRequest).getUserId());
        return happyService.deductStatus(param);
    }

    @ApiOperation(value = "全国标杆网点创建情况", notes = "可选参数：year；没有值，前端传{}对象")
    @PostMapping
    public Object HeadStatus(@RequestBody HappyParam param) {
        param.setUserId(getCurrentUserInfo(httpServletRequest).getUserId());
        return happyService.HeadStatus(param);
    }

}