package com.bank.log.controller;

import com.bank.core.entity.PageQueryModel;
import com.bank.log.dos.ErrorLogDO;
import com.bank.log.service.ErrotLogService;
import com.bank.log.vo.ErrLogVo;
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
@Api(tags = "异常日志接口")
@RestController
@RequestMapping("/errlog")
public class ErrorLogController {

    @Autowired
    private ErrotLogService errotLogService;

    @ApiOperation(value = "获取错误日志列表", notes = "获取错误日志列表")
    @PostMapping("/list")
    public IPage<ErrorLogDO> getList(@RequestBody ErrLogVo errLogVo){
        IPage<ErrorLogDO> pageLog=errotLogService.SelectErrLogPage(errLogVo);
        return pageLog;
    }

    @ApiOperation(value = "获取错误日志版本列表")
    @GetMapping("/errversion")
    public List<String> getErrVersion(){
        return errotLogService.getErrVersion();
    }
}
