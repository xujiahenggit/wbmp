package com.bank.manage.service.impl;

import com.bank.manage.dao.TermStatusDao;
import com.bank.manage.dos.TermStatusDO;
import com.bank.manage.service.TermStatusService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 终端设备
 *
 * @author
 * @date 2020-7-4
 */
@Service
@Slf4j
public class TermStatusServiceImpl extends ServiceImpl<TermStatusDao, TermStatusDO> implements TermStatusService {

    @Autowired(required = false)
    private TermStatusDao termStatusDao;

    @Override
    public LocalDateTime getNow(TermStatusDO termStatusDO) {
        QueryWrapper<TermStatusDO> wrapper = new QueryWrapper<>();
        wrapper.eq("strtermnum", termStatusDO.getStrtermnum());
        TermStatusDO termDO1 = termStatusDao.selectOne(wrapper);
        Integer i1 = termStatusDao.selectCount(wrapper);
        wrapper.eq("svcstatus", termStatusDO.getSvcstatus());
        Integer i2 = termStatusDao.selectCount(wrapper);
        LocalDateTime now = LocalDateTime.now();
        termStatusDO.setIagentstatus("0");
        if (i1 == 0) {
            termStatusDO.setDtsvcstatusbegin(now);
            termStatusDao.insert(termStatusDO);
        } else if (i2 == 0) {
            termStatusDO.setId(termDO1.getId());
            termStatusDO.setDtsvcstatusbegin(now);
            termStatusDao.updateById(termStatusDO);
        } else {
            now = termDO1.getDtsvcstatusbegin();
        }
        return now;
    }

    @Override
    public void agent(LocalDateTime agentTime) {
        termStatusDao.updateByTime(agentTime);
    }
}
