package com.bank.quartz.controller;

import com.bank.quartz.dos.TaskLogDO;
import com.bank.quartz.service.TaskLogService;
import com.bank.quartz.vo.TaskLogVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/4/10 18:19
 */
@Api(tags = "定时任务日志")
@RestController
@RequestMapping("/taslog")
public class TaskLogController {

    @Autowired
    private TaskLogService taskLogService;

    @ApiOperation(value = "对条件查询 定时任务日志")
    @PostMapping("/list")
    public IPage<TaskLogDO> getPage(@RequestBody TaskLogVo taskLogVo){
        return taskLogService.getLogPage(taskLogVo);
    }
}
