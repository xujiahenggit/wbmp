package com.bank.manage.controller;

import com.bank.manage.dto.DeviceErrorLogDTO;
import com.bank.manage.service.DeviceErrorLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deviceErrorLog")
@Api(tags = "设备错误信息接口")
public class DeviceErrorLogController {

    @Autowired
    private DeviceErrorLogService deviceErrorLogService;

    @PostMapping("/saveErrorLog")
    @ApiOperation(value = "新增设备错误信息")
    @ApiImplicitParam(name = "deviceErrorLogDTO", value = "设备错误信息", required = true, paramType = "body", dataType = "DeviceErrorLogDTO")
    public Boolean saveErrorLog(@RequestBody DeviceErrorLogDTO deviceErrorLogDTO){
        return deviceErrorLogService.saveErrorLog(deviceErrorLogDTO);
    }

}
