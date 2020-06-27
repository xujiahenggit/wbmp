package com.bank.quartz.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.core.sysConst.ConstFile;
import com.bank.log.annotation.SystemLog;
import com.bank.quartz.dos.TaskEntityDO;
import com.bank.quartz.dto.TaskEntityDTO;
import com.bank.quartz.service.TaskEntityService;
import com.bank.quartz.vo.TaskEntityVo;
import com.baomidou.mybatisplus.core.metadata.IPage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @Author: Andy
 * @Date: 2020/4/2 9:40
 */
@Api(tags = "定时任务接口")
@RestController
@RequestMapping("/job")
public class TaskEntityController {

    @Autowired
    private TaskEntityService taskEntityService;

    /**
     * 此处为 容器启动初始化 所有的任务列表
     */
    //@PostConstruct
    public void initialize() {
        reStartAllJob();
    }

    @ApiOperation(value = "添加定时任务")
    @PostMapping("/add")
    @SystemLog(logModul = ConstFile.MODULE_TASK, logType = ConstFile.TYPE_ADD, logDesc = "添加定时任务")
    public Boolean addJob(@RequestBody TaskEntityDTO taskEntityDTO) {
        return this.taskEntityService.addJob(taskEntityDTO);
    }

    @SystemLog(logModul = ConstFile.MODULE_TASK, logType = ConstFile.TYPE_UPDATE, logDesc = "修改定时任务")
    @ApiOperation(value = "修改定时任务")
    @PostMapping("/update")
    public Boolean modifyJb(@RequestBody TaskEntityDTO taskEntityDTO) {
        return this.taskEntityService.updateJob(taskEntityDTO);
    }

    @ApiOperation(value = "获取定时任务列表")
    @PostMapping("/list")
    public IPage<TaskEntityDO> getList(@RequestBody TaskEntityVo taskEntityVo) {
        IPage<TaskEntityDO> list = this.taskEntityService.selectJobList(taskEntityVo);
        return list;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "jobId",
                    value = "任务编号(jobId)",
                    example = "1",
                    dataType = "int",
                    required = true),
    })
    @SystemLog(logModul = ConstFile.MODULE_TASK, logType = ConstFile.TYPE_RUNNOW, logDesc = "立即执行任务")
    @ApiOperation(value = "立即执行任务")
    @PostMapping("/runnow")
    public boolean runJob(@RequestParam Integer jobId) {
        return this.taskEntityService.runJob(jobId);
    }

    @SystemLog(logModul = ConstFile.MODULE_TASK, logType = ConstFile.TYPE_RESTART_ALL, logDesc = "重启所有任务")
    @ApiOperation(value = "重启所有的任务")
    @PostMapping("/restartall")
    public boolean reStartAllJob() {
        return this.taskEntityService.reStartAllJob();
    }

    @SystemLog(logModul = ConstFile.MODULE_TASK, logType = ConstFile.TYPE_STOP, logDesc = "暂停任务")
    @ApiOperation(value = "暂停任务")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "jobId",
                    value = "任务编号(jobId)",
                    example = "1",
                    dataType = "int",
                    required = true),
    })
    @PostMapping("/stopjob")
    public boolean stopJob(@RequestParam Integer jobId) {
        return this.taskEntityService.stopJob(jobId);
    }

    @SystemLog(logModul = ConstFile.MODULE_TASK, logType = ConstFile.TYPE_RESUME, logDesc = "恢复任务")
    @ApiOperation(value = "恢复任务")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "jobId",
                    value = "任务编号(jobId)",
                    example = "1",
                    dataType = "int",
                    required = true),
    })
    @PostMapping("/resumjob")
    public boolean restartJob(@RequestParam Integer jobId) {
        return this.taskEntityService.restartJob(jobId);
    }

    @ApiOperation(value = "根据ID 获取任务详细信息")
    @ApiImplicitParam(name = "taskId", value = "任务编号", required = true, paramType = "path")
    @SystemLog(logModul = ConstFile.MODULE_TASK, logType = ConstFile.TYPE_DELETE, logDesc = "删除定时任务")
    @GetMapping("/info/{taskId}")
    public TaskEntityDO getTaskInfo(@PathVariable Integer taskId) {
        return this.taskEntityService.getById(taskId);
    }

    @ApiOperation(value = "根据ID 删除任务")
    @ApiImplicitParam(name = "taskId", value = "任务编号", required = true, paramType = "path")
    @DeleteMapping("/{taskId}")
    public boolean deleteJob(@PathVariable Integer taskId) {
        return this.taskEntityService.deleteJob(taskId);
    }
}
