package com.bank.manage.service;

import com.bank.manage.dos.TermStatusDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDateTime;

public interface TermStatusService extends IService<TermStatusDO> {

    LocalDateTime getNow(TermStatusDO termStatusDO);

    void agent(LocalDateTime agentTime);
}
