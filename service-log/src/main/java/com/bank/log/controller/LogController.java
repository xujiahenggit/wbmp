package com.bank.log.controller;

import com.bank.core.entity.PageQueryModel;
import com.bank.log.annotation.SystemLog;
import com.bank.log.dos.LogDO;
import com.bank.log.service.LogService;
import com.bank.log.vo.LogQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/4/1 15:43
 */
@Api(tags = "正常日志接口")
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    @ApiOperation(value = "获取正常日志列表", notes = "获取正常日志列表")
    @PostMapping("/list")
    public IPage<LogDO> getList(@RequestBody LogQueryVo logQueryVo){
        IPage<LogDO> pageLog=logService.SelectLogPage(logQueryVo);
        return pageLog;
    }

    @ApiOperation(value = "获取操作模块列表", notes = "获取操作模块列表")
    @GetMapping("/moduls")
    public List<String> getOptModuls(){
        return logService.selectOptModuls();
    }

    @ApiOperation(value = "获取操作类型列表", notes = "获取操作类型列表")
    @GetMapping("/types")
    public List<String> getOptType(){
        return logService.selectOptTypes();
    }

    @ApiOperation(value = "获取日志版本列表", notes = "获取日志版本列表")
    @GetMapping("/vsersions")
    public List<String> getOptVersion(){
        return logService.selectLogVersions();
    }
}
