package com.bank.quartz.service.impl;

import cn.hutool.core.util.StrUtil;
import com.bank.quartz.dao.TaskLogDao;
import com.bank.quartz.dos.TaskLogDO;
import com.bank.quartz.service.TaskLogService;
import com.bank.quartz.vo.TaskLogVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: Andy
 * @Date: 2020/4/10 11:00
 */
@Service
public class TaskLogServiceImpl extends ServiceImpl<TaskLogDao, TaskLogDO> implements TaskLogService {

    @Resource
    private TaskLogDao taskLogDao;

    /**
     * 多维度查询定时任务 列表
     *
     * @param taskLogVo
     * @return
     */
    @Override
    public IPage<TaskLogDO> getLogPage(TaskLogVo taskLogVo) {
        QueryWrapper<TaskLogDO> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(taskLogVo.getTasklogState())) {
            queryWrapper.eq("TASKLOG_STATE", taskLogVo.getTasklogState());
        }
        if (StrUtil.isNotBlank(taskLogVo.getStartTime()) && StrUtil.isNotBlank(taskLogVo.getEndTime())) {
            queryWrapper.between("TASKLOG_TIME", taskLogVo.getStartTime(), taskLogVo.getEndTime());
        }
        //按创建时间 倒序排序
        queryWrapper.orderByDesc("TASKLOG_TIME");
        Page<TaskLogDO> page=new Page<>(taskLogVo.getPageIndex(),taskLogVo.getPageSize());
        return taskLogDao.selectPage(page,queryWrapper);
    }
}
