package com.bank.quartz.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.bank.core.entity.BizException;
import com.bank.core.sysConst.SysStatus;
import com.bank.quartz.dao.TaskEntityDao;
import com.bank.quartz.dos.TaskEntityDO;
import com.bank.quartz.dto.TaskEntityDTO;
import com.bank.quartz.service.TaskEntityService;
import com.bank.quartz.vo.TaskEntityVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Struct;
import java.time.LocalDateTime;
import java.util.Set;

import static com.bank.core.sysConst.SysStatus.TASK_STATUS_FALSE;

/**
 * @Author: Andy
 * @Date: 2020/4/2 9:36
 */
@Service
@Slf4j
public class TaskEntityServiceImpl extends ServiceImpl<TaskEntityDao, TaskEntityDO> implements TaskEntityService {

    @Resource
    private TaskEntityDao taskEntityDao;

    @Resource
    private Scheduler scheduler;

    /**
     * 获取任务的执行 规则
     *
     * @param job 当前任务
     * @return 任务的执行规则
     */
    private Trigger getTrigger(TaskEntityDO job) {
        return TriggerBuilder.newTrigger()
                .withIdentity(job.getTaskName(), job.getTaskGroup())
                .withSchedule(CronScheduleBuilder.cronSchedule(job.getTaskCorn()))
                .build();
    }

    /**
     * 获取 jobkey
     *
     * @param job 任务
     * @return
     */
    private JobKey getJobKey(TaskEntityDO job) {
        return JobKey.jobKey(job.getTaskName(), job.getTaskGroup());
    }

    /**
     * 添加定时任务
     *
     * @param taskEntityDTO 定时任务参数
     *                      1.添加到 定时任务自定义表中
     *                      2.添加到 定时任务
     * @return 成功返回true 失败 直接抛出异常
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addJob(TaskEntityDTO taskEntityDTO) {
        try {
            //添加定时任务到 自定义表中
            TaskEntityDO taskEntityDO = new TaskEntityDO();
            BeanUtils.copyProperties(taskEntityDTO, taskEntityDO);
            taskEntityDO.setCreateTime(LocalDateTime.now());
            taskEntityDO.setUpdateTime(LocalDateTime.now());
            taskEntityDao.insert(taskEntityDO);
            if(StrUtil.isNotBlank(taskEntityDTO.getTaskStatus()) && SysStatus.TASK_STATUS_TRUE.equals(taskEntityDTO.getTaskStatus())){
                // 添加定时任务到 quartz
                this.addQuartzJob(taskEntityDO);
                log.info("添加任务：" + JSONUtil.toJsonPrettyStr(taskEntityDO));
            }
            return true;
        } catch (SchedulerException | ClassNotFoundException e) {
            throw new BizException("添加定时任务失败");
        }
    }

    /**
     * 更新定时任务
     * 1.先删除 quartz 中
     * 1.更新 自定义表中的数据
     * 2.更新 quartz 中的数据
     *
     * @param taskEntityDTO 参数
     * @return 成功 返回true 失败 抛出异常
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateJob(TaskEntityDTO taskEntityDTO) {
        try {
            //1.先拿出 原先的Job数据
            TaskEntityDO taskEntityDOOld = taskEntityDao.selectById(taskEntityDTO.getTaskId());
            //2.删除 quartz 中的任务
            this.deleteQuartzJob(taskEntityDOOld);
            //1.更新自定义表中的数据
            TaskEntityDO taskEntityDO = new TaskEntityDO();
            BeanUtils.copyProperties(taskEntityDTO, taskEntityDO);
            //设置更新时间
            taskEntityDO.setUpdateTime(LocalDateTime.now());
            taskEntityDao.updateById(taskEntityDO);
            //3.重新添加 到 quartz
            if(StrUtil.isNotBlank(taskEntityDTO.getTaskStatus()) && SysStatus.TASK_STATUS_TRUE.equals(taskEntityDTO.getTaskStatus())) {
                this.addQuartzJob(taskEntityDO);
                log.info("任务更新前：" + JSONUtil.toJsonPrettyStr(taskEntityDOOld));
                log.info("任务更新后：" + JSONUtil.toJsonPrettyStr(taskEntityDO));
            }
            return true;
        } catch (Exception e) {
            throw new BizException("更新定时任务失败");
        }
    }

    /**
     * 获取定时任务列表
     *
     * @param taskEntityVo 查询参数
     * @return
     */
    @Override
    public IPage<TaskEntityDO> selectJobList(TaskEntityVo taskEntityVo) {
        try {
            QueryWrapper<TaskEntityDO> taskEntityDOQueryWrapper = new QueryWrapper<>();
            if (StrUtil.isNotBlank(taskEntityVo.getTaskName())) {
                taskEntityDOQueryWrapper.eq("TASK_NAME", taskEntityVo.getTaskName());
            }
            if (StrUtil.isNotBlank(taskEntityVo.getTaskGroup())) {
                taskEntityDOQueryWrapper.eq("TASK_GROUP", taskEntityVo.getTaskName());
            }
            Page<TaskEntityDO> taskEntityDOPage = new Page<>(taskEntityVo.getPageIndex(), taskEntityVo.getPageSize());
            IPage<TaskEntityDO> pageTask = taskEntityDao.selectPage(taskEntityDOPage, taskEntityDOQueryWrapper);
            for (TaskEntityDO item : pageTask.getRecords()) {
                TriggerKey triggerKey = TriggerKey.triggerKey(item.getTaskName(), item.getTaskGroup());
                Trigger trigger = scheduler.getTrigger(triggerKey);
                if (trigger != null) {
                    //上次执行时间
                    item.setTaskLastRuntime((trigger.getPreviousFireTime() == null) ? 0 : trigger.getPreviousFireTime().getTime());
                    //下次执行时间
                    item.setTaskNextRuntime((trigger.getNextFireTime() == null) ? 0 : trigger.getNextFireTime().getTime());
                }
            }
            return pageTask;
        } catch (Exception e) {
            throw new BizException("获取定时任务列表失败");
        }
    }

    /**
     * 立即执行任务
     *
     * @param jobId 任务ID
     * @return
     */
    @Override
    public boolean runJob(Integer jobId) {
        try {
            //获取任务信息
            TaskEntityDO taskEntityDO = taskEntityDao.selectById(jobId);
            if(TASK_STATUS_FALSE.equals(taskEntityDO.getTaskStatus())){
                throw new BizException("执行失败！任务为暂停状态！");
            }
            JobKey jobKey = JobKey.jobKey(taskEntityDO.getTaskName(), taskEntityDO.getTaskGroup());
            scheduler.triggerJob(jobKey);
            return true;
        } catch (Exception e) {
            if(e instanceof BizException){
                throw new BizException(((BizException) e).getErrorMsg());
            }
            throw new BizException("立即执行任务失败");
        }
    }

    /**
     * 重启所有的任务
     *
     * @return
     */
    @Override
    public boolean reStartAllJob() {
        try {
            Set<JobKey> set = scheduler.getJobKeys(GroupMatcher.anyGroup());
            scheduler.pauseJobs(GroupMatcher.anyGroup());
            //暂停所有JOB
            for (JobKey jobKey : set) {
                //删除从数据库中注册的所有JOB
                scheduler.unscheduleJob(TriggerKey.triggerKey(jobKey.getName(), jobKey.getGroup()));
                scheduler.deleteJob(jobKey);
            }
            //从数据库中注册的所有JOB
            for (TaskEntityDO job : taskEntityDao.selectList(new QueryWrapper<>())) {
                log.info("注册任务：", job.getTaskName(), job.getTaskGroup(), job.getTaskCorn());
                //只有 已发布的任务 才进行注册
                if (SysStatus.TASK_STATUS_TRUE.equals(job.getTaskStatus())) {
                    //注册到任务
                    addQuartzJob(job);
                }
            }
            return true;
        } catch (Exception e) {
            throw new BizException("重启所有任务失败");
        }
    }

    /**
     * 暂停任务
     *
     * @param jobId 任务ID
     *              1.修改 自定义表中的状态为 暂停
     *              2.暂停 quartz 中的任务
     * @return 成功 返回true 失败 抛出异常
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean stopJob(Integer jobId) {
        try {
            //1. 修改自定义任务状态
            TaskEntityDO taskEntityDO = new TaskEntityDO();
            taskEntityDO.setTaskId(jobId);
            taskEntityDO.setTaskStatus("1");
            taskEntityDao.updateById(taskEntityDO);
            //2.暂停 任务状态
            TaskEntityDO taskEntityDOQuartz = taskEntityDao.selectById(jobId);
            this.pauseQuartzJob(taskEntityDOQuartz);
            return true;
        } catch (Exception e) {
            throw new BizException("暂停任务失败");
        }
    }

    /**
     * 恢复任务
     *
     * @param jobId 任务ID
     * @return 成功返回true 失败 直接抛出异常
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean restartJob(Integer jobId) {
        try {
            //1. 修改自定义任务状态
            TaskEntityDO taskEntityDO = new TaskEntityDO();
            taskEntityDO.setTaskId(jobId);
            taskEntityDO.setTaskStatus("0");
            taskEntityDao.updateById(taskEntityDO);
            //2.恢复 任务状态
            TaskEntityDO taskEntityDOQuartz = taskEntityDao.selectById(jobId);
            this.resumeQuartzJob(taskEntityDOQuartz);
            return true;
        } catch (Exception e) {
            throw new BizException("暂停任务失败");
        }
    }

    /**
     * 删除定时任务
     *
     * 1.删除 quartz 中的任务
     * 2.删除自定义任务
     * @param jobId 任务ID
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteJob(Integer jobId) {
        try{
            //1.删除 quartz 中的任务
            TaskEntityDO taskEntityDO=taskEntityDao.selectById(jobId);
            this.deleteQuartzJob(taskEntityDO);
            //2. 删除 自定义任务
            taskEntityDao.deleteById(jobId);
            return true;
        }catch (Exception e){
            throw new BizException("删除定时任务失败");
        }
    }


    /**
     * 删除quartz 中的任务
     *
     * @param taskEntityDO
     */
    private void deleteQuartzJob(TaskEntityDO taskEntityDO) throws SchedulerException {
        JobKey jobKey = this.getJobKey(taskEntityDO);
        //先暂停任务
        scheduler.pauseJob(jobKey);
        //删除
        scheduler.deleteJob(jobKey);
        log.info("删除任务：" + JSONUtil.toJsonPrettyStr(taskEntityDO));
    }

    /**
     * 暂停quartz 中的任务
     *
     * @param taskEntityDO
     */
    private void pauseQuartzJob(TaskEntityDO taskEntityDO) throws SchedulerException {
        JobKey jobKey = this.getJobKey(taskEntityDO);
        scheduler.pauseJob(jobKey);
    }

    /**
     * 恢复任务
     * 1.如果任务存在 则恢复
     * 2.如果不存在则 重新添加
     *
     * @param taskEntityDO
     * @throws SecurityException
     */
    private void resumeQuartzJob(TaskEntityDO taskEntityDO) throws SchedulerException, ClassNotFoundException {
        TriggerKey triggerKey = TriggerKey.triggerKey(taskEntityDO.getTaskName(), taskEntityDO.getTaskGroup());
        Trigger trigger = scheduler.getTrigger(triggerKey);
        if (null == trigger) {
            this.addQuartzJob(taskEntityDO);
        } else {
            JobKey jobKey = this.getJobKey(taskEntityDO);
            scheduler.resumeJob(jobKey);
        }
    }

    /**
     * 添加quartz 任务
     *
     * @param taskEntityDO
     */
    private void addQuartzJob(TaskEntityDO taskEntityDO) throws SchedulerException, ClassNotFoundException {
        // 添加定时任务到 quartz
        TriggerKey triggerKey = TriggerKey.triggerKey(taskEntityDO.getTaskName(), taskEntityDO.getTaskGroup());
        Trigger trigger = scheduler.getTrigger(triggerKey);
        if (null == trigger) {
            JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) Class.forName(taskEntityDO.getTaskBean())).withIdentity(taskEntityDO.getTaskName(), taskEntityDO.getTaskGroup()).build();
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(taskEntityDO.getTaskCorn());
            //不立即触发 到cron 表达式指定时间才执行
            trigger = TriggerBuilder.newTrigger().withIdentity(taskEntityDO.getTaskName(), taskEntityDO.getTaskGroup()).withSchedule(cronScheduleBuilder.withMisfireHandlingInstructionDoNothing()).build();
            scheduler.scheduleJob(jobDetail, trigger);
        }
    }
}
