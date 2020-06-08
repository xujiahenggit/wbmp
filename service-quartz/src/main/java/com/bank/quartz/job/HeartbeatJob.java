package com.bank.quartz.job;

import com.bank.core.entity.BizException;
import com.bank.manage.dos.DeviceDO;
import com.bank.manage.dos.StatusLogDO;
import com.bank.manage.service.DeviceService;
import com.bank.manage.service.StatusLogService;
import com.bank.message.dos.DeviceOnline;
import com.bank.message.service.DeviceOnlineService;
import com.bank.quartz.dos.TaskLogDO;
import com.bank.quartz.service.TaskLogService;
import com.bank.quartz.utils.GetTaskLogModel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/4/20 12:54
 * 检测设备是否在线
 */
@Slf4j
@Component
public class HeartbeatJob implements Job {

    @Resource
    private TaskLogService taskLogService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        LocalDateTime localDateTime = LocalDateTime.now();
        long start = System.currentTimeMillis();
        try {
            handle();//定时检测设备是否还在线
            TaskLogDO taskLogDO = GetTaskLogModel.getModel(jobExecutionContext, "1", localDateTime, System.currentTimeMillis() - start, null);
            taskLogService.save(taskLogDO);
        } catch (Exception e) {
            log.info("任务执行失败");
            TaskLogDO taskLogDO = GetTaskLogModel.getModel(jobExecutionContext, "2", localDateTime, System.currentTimeMillis() - start, e.fillInStackTrace());
            taskLogService.save(taskLogDO);
        }
    }


    @Resource
    private DeviceOnlineService deviceOnlineService;


    @Resource
    private DeviceService deviceService;


    @Resource
    private StatusLogService statusLogService;

    private void handle() {
        List<DeviceOnline> list = deviceOnlineService.list();
        for (DeviceOnline deviceOnline : list) {
            String mac = deviceOnline.getMac();
            Date now = new Date();
            Date updateTime = deviceOnline.getUpdateTime();
            if (now.getTime() - updateTime.getTime() > 60000) {
                boolean remove = deviceOnlineService.removeById(mac);
                if (remove) {
                    //更改设备在线状态
                    QueryWrapper<DeviceDO> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("MAC", mac);
                    DeviceDO deviceDO = deviceService.getOne(queryWrapper);
                    if (deviceDO == null) {
                        throw new BizException("设备表中未查到mac地址为：" + mac + " 的记录。");
                    }

                    //下线设备
                    deviceDO.setUpdateTime(new Date());
                    deviceDO.setOnline("01");

                    deviceService.updateById(deviceDO);

                    //记录日志
                    LocalDateTime nowTime = LocalDateTime.now();
                    StatusLogDO entity=StatusLogDO.builder()
                            .mac(mac)
                            .terminalNum(deviceDO.getTerminalNum())
                            .messageLog("心跳定时检测任务，检测到设备已经下线")
                            .createTime(nowTime).updateTime(nowTime).build();
                    statusLogService.save(entity);


                } else {
                    throw new BizException("删除设备在线表中mac地址为：" + mac + " 的记录失败。");
                }
            }
        }

        log.info("定时检测心跳任务执行完成，时间：{}",new Date());
    }

}
