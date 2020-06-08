package com.bank.quartz.service;

import com.bank.quartz.dos.TaskEntityDO;
import com.bank.quartz.dto.TaskEntityDTO;
import com.bank.quartz.vo.TaskEntityVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;

/**
 * @Author: Andy
 * @Date: 2020/4/2 9:34
 * 定时任务列表 服务类
 */
public interface TaskEntityService extends IService<TaskEntityDO> {

    /**
     * 添加定时任务
     * @param taskEntityDTO 定时任务参数
     * @return 成功 true 失败false
     */
    Boolean addJob(TaskEntityDTO taskEntityDTO);

    /**
     * 修改定时任务
     * @param taskEntityDTO
     * @return 成功 true 失败 抛出异常
     */
    Boolean updateJob(TaskEntityDTO taskEntityDTO);

    /**
     * 获取Job列表
     * @param taskEntityVo 查询参数
     * @return
     */
    IPage<TaskEntityDO> selectJobList(TaskEntityVo taskEntityVo);

    /**
     * 立即执行任务
     * @param jobId 任务ID
     * @return 成功返回 true
     */
    boolean runJob(Integer jobId);

    /**
     * 重启所有的任务
     * @return 成功返回 true
     */
    boolean reStartAllJob();

    /**
     * 暂停任务
     * @param jobId 任务ID
     * @return 成功返回 true
     */
    boolean stopJob(Integer jobId);

    /**
     * 恢复任务
     * @param jobId 任务ID
     * @return 成功返回 true
     */
    boolean restartJob(Integer jobId);

    /**
     * 删除任务
     * @param jobId 任务ID
     * @return
     */
    boolean deleteJob(Integer jobId);
}
