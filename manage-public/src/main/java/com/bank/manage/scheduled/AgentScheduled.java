package com.bank.manage.scheduled;

import com.bank.manage.service.TermStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Configuration
@EnableScheduling
public class AgentScheduled {
    @Autowired
    TermStatusService termStatusService;

    @Scheduled(cron = "0 * * * * *")
    private void isAgent() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime agentTime = now.minus(20, ChronoUnit.MINUTES);
        System.out.println(agentTime);
        termStatusService.agent(agentTime);
    }
}
