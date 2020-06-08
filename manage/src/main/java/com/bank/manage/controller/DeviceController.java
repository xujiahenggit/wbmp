package com.bank.manage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.auth.base.BaseController;
import com.bank.core.entity.BizException;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.entity.TokenUserInfo;
import com.bank.log.annotation.SystemLog;
import com.bank.manage.dos.DeviceErrorLogDO;
import com.bank.manage.dos.DevicePlayDO;
import com.bank.manage.dos.StatusLogDO;
import com.bank.manage.dto.DeviceDTO;
import com.bank.manage.service.DeviceErrorLogService;
import com.bank.manage.service.DevicePlayService;
import com.bank.manage.service.DeviceService;
import com.bank.manage.service.StatusLogService;
import com.baomidou.mybatisplus.core.metadata.IPage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 设备信息
 *
 * @author
 * @date 2020-4-1
 */
@Api(tags = "设备信息接口")
@RestController
@RequestMapping("/device")
public class DeviceController extends BaseController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private DevicePlayService devicePlayService;

    @Autowired
    private DeviceErrorLogService deviceErrorLogService;

    @Autowired
    private StatusLogService statusLogService;

    @PostMapping("/save")
    @ApiOperation(value = "新增设备信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceDTO", value = "设备信息", required = true, paramType = "body", dataType = "DeviceDTO"),
            @ApiImplicitParam(name = "groupId", value = "设备分组", required = false, dataType = "int")
    })
    @SystemLog(logModul = "设备信息管理", logType = "新增", logDesc = "新增设备信息")
    public Boolean save(@RequestBody DeviceDTO deviceDTO, @RequestParam(value = "groupId", required = false) Integer groupId, HttpServletRequest request) {
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        return this.deviceService.save(deviceDTO, groupId, tokenUserInfo);
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改设备信息")
    @ApiImplicitParam(name = "deviceDTO", value = "设备信息", required = true, paramType = "body", dataType = "DeviceDTO")
    @SystemLog(logModul = "设备信息管理", logType = "修改", logDesc = "修改设备信息")
    public Boolean update(@RequestBody DeviceDTO deviceDTO, HttpServletRequest request) {
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        return this.deviceService.update(deviceDTO, tokenUserInfo);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除设备信息")
    @ApiImplicitParam(name = "id", value = "设备唯一标识", required = true, paramType = "path")
    @SystemLog(logModul = "设备信息管理", logType = "删除", logDesc = "删除设备信息")
    public Boolean delete(@PathVariable Integer id) {
        return this.deviceService.delete(id);
    }

    @PutMapping("/updateStatus")
    @ApiOperation(value = "修改设备状态")
    @ApiImplicitParam(name = "deviceDTO", value = "设备信息", required = true, paramType = "body", dataType = "DeviceDTO")
    @SystemLog(logModul = "设备信息管理", logType = "修改", logDesc = "修改设备状态")
    public Integer status(@RequestBody DeviceDTO deviceDTO) {
        return this.deviceService.updateStatus(deviceDTO);
    }

    @PostMapping("/queryListForNet")
    @ApiOperation(value = "查询设备列表")
    @ApiImplicitParam(name = "pageQueryModel", value = "设备信息分页查询", required = true, paramType = "body", dataType = "PageQueryModel")
    public IPage<DeviceDTO> queryList(@RequestBody PageQueryModel pageQueryModel) {
        return this.deviceService.queryList(pageQueryModel);
    }

    @GetMapping("/{terminalNum}")
    @ApiOperation(value = "设备编号校验")
    @ApiImplicitParam(name = "terminalNum", value = "设备编号", required = true, paramType = "path")
    public Integer checkTerminalNum(@PathVariable String terminalNum) {
        int i = this.deviceService.checkTerminalNum(terminalNum);
        if (i > 0) {
            throw new BizException("设备编号已存在，请重新输入");
        }
        return i;
    }

    @PostMapping("/queryDevicePlay")
    @ApiOperation(value = "设备播放流水分页查询")
    @ApiImplicitParam(name = "pageQueryModel", value = "设备播放流水分页查询", required = true, paramType = "body", dataType = "PageQueryModel")
    public IPage<DevicePlayDO> queryDevicePlay(@RequestBody PageQueryModel pageQueryModel) {
        return this.devicePlayService.queryDevicePlay(pageQueryModel);
    }

    @PostMapping("/queryDeviceErrorLog")
    @ApiOperation(value = "设备错误日志分页查询")
    @ApiImplicitParam(name = "pageQueryModel", value = "设备错误日志分页查询", required = true, paramType = "body", dataType = "PageQueryModel")
    public IPage<DeviceErrorLogDO> queryDeviceErrorLog(@RequestBody PageQueryModel pageQueryModel) {
        return this.deviceErrorLogService.queryDeviceErrorLog(pageQueryModel);
    }

    @PostMapping("/queryStatusLog")
    @ApiOperation(value = "设备状态日志分页查询")
    @ApiImplicitParam(name = "pageQueryModel", value = "设备状态日志分页查询", required = true, paramType = "body", dataType = "PageQueryModel")
    public IPage<StatusLogDO> queryStatusLog(@RequestBody PageQueryModel pageQueryModel) {
        return this.statusLogService.queryStatusLog(pageQueryModel);
    }
}
