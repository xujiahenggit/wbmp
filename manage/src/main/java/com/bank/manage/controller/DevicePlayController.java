package com.bank.manage.controller;

import com.bank.manage.dto.DevicePlayDTO;
import com.bank.manage.service.DevicePlayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/devicePlay")
@Api(tags = "设备播放信息接口")
public class DevicePlayController {

    @Autowired
    private DevicePlayService devicePlayService;

    @PostMapping("/saveDevicePlay")
    @ApiOperation(value = "新增设备播放信息")
    @ApiImplicitParam(name = "devicePlayDTO", value = "设备播放信息", required = true, paramType = "body", dataType = "DevicePlayDTO")
    public Boolean saveDevicePlay(@RequestBody DevicePlayDTO devicePlayDTO){
        return devicePlayService.saveDevicePlay(devicePlayDTO);
    }

}
