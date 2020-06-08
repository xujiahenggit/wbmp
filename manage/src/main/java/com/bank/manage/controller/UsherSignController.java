package com.bank.manage.controller;

import com.bank.core.entity.BizException;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;

import com.bank.manage.dto.UsherSignDTO;
import com.bank.manage.service.UsherSignService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * ClassName: UsherSignController
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/04/23 16:09:09
 */
@Api(tags = "网点引导员签到")
@RestController
@RequestMapping("/usherSign")
public class UsherSignController {

    private final UsherSignService usherSignService;

    public UsherSignController(UsherSignService usherSignService) {
        this.usherSignService = usherSignService;
    }

    @ApiOperation(value = "引导员上班签")
    @ApiImplicitParam(name = "usherSignDTO", value = "网点引导员签到", required = true, paramType = "body", dataType = "UsherSignDTO")
    @PostMapping("/onDutySign")
    public Boolean onDutySign(@RequestBody UsherSignDTO usherSignDTO) {
       return this.usherSignService.onDutySign(usherSignDTO);
    }

    @ApiOperation(value = "引导员下班签")
    @ApiImplicitParam(name = "usherSignDTO", value = "网点引导员签到", required = true, paramType = "body", dataType = "UsherSignDTO")
    @PostMapping("/offDutySign")
    public Boolean offDutySign(@RequestBody UsherSignDTO usherSignDTO) {
       return this.usherSignService.offDutySign(usherSignDTO);
    }



    @GetMapping("/queryInformation/{usherId}/{month}")
    @ApiOperation(value = "引导员考勤打卡详细信息查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "usherId",value = "引导员唯一标识",required = true,paramType = "path"),
            @ApiImplicitParam(name = "month",value = "考勤月份",required = true,paramType = "path")}
    )
    public Map<String,Object> queryInformation(@PathVariable("usherId") String usherId, @PathVariable("month") String month){
        Pattern compile = Pattern.compile("^\\d{4}-((0([1-9]))|(1(0|1|2)))$");
        Matcher matcher =  compile.matcher(month);
        if(!matcher.find()){
            throw new BizException("日期格式错误");
        }
        return usherSignService.queryInformation(usherId,month);
    }

    @GetMapping("checkDate/{date}")
    @ApiOperation(value = "前端时间与服务器时间检验")
    public Date checkDate(@PathVariable("date")String date) throws Exception {
        long second = 300000;//5分钟
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = s.parse(date);
        Date nowDate = new Date();
        long time = nowDate.getTime() - parse.getTime();
        if(time >= second){
            throw new BizException("前端时间与服务器时间检验失败，检验时间超过5分钟");
        }
        return nowDate;
    }


}
