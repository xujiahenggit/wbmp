package com.bank.core.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.core.utils.SerialNumberUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 获取流水号API
 * ClassName: ImagePlatformController
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/07/13 18:48:13
 */
@RestController
@RequestMapping("/SerialNumber")
@Api(tags = "获取流水号接口")
public class SerialNumberController {

    @Resource
    SerialNumberUtil serialNumberUtil;

    @GetMapping("/getICOPSerialNumber")
    @ApiOperation("获取ICOP请求流水码")
    public String getICOPSerialNumber() {
        return serialNumberUtil.getICOPSerialNumber();
    }

}
