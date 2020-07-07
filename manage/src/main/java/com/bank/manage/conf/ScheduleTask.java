package com.bank.manage.conf;

import com.bank.manage.service.WbmpAbsTellerOnlineTimeService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

@Configuration
@EnableScheduling
public class ScheduleTask {

    @Resource
    WbmpAbsTellerOnlineTimeService wbmpAbsTellerOnlineTimeService;

    @Scheduled(cron = "0 0/3 6-20 * * ?")
    private void getTellerOnLineTime(){
//        wbmpAbsTellerOnlineTimeService.fillDataBeat();
        wbmpAbsTellerOnlineTimeService.fillDataBeatTest();
    }

}
