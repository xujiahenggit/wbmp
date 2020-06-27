package com.bank.manage.controller;

import com.bank.manage.dto.WbmpOperateBqmsQueueAvgDto;
import com.bank.manage.service.WbmpOperateBqmsQueueAvgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Andy
 * @Date: 2020/6/12 17:00
 */
@Api(tags = "运营看板首页--客户满意度")
@RestController
@RequestMapping("/queueavg")
public class WbmpOperateBqmsQueueAvgController {

    @Autowired
    private WbmpOperateBqmsQueueAvgService wbmpOperateBqmsQueueAvgService;

    @ApiOperation("客户满意度信息")
    @GetMapping("/info/{orgId}/")
    public WbmpOperateBqmsQueueAvgDto getOperraInfo(@PathVariable String orgId){
        return wbmpOperateBqmsQueueAvgService.getOperraInfo(orgId);
    }


    @ApiOperation("机构月平均弃号率")
    @GetMapping("/orgAbandVe/{orgId}/")
    public String getAvgAbondVe(@PathVariable String orgId){
        return wbmpOperateBqmsQueueAvgService.getAvgAbondVe(orgId);
    }
}
