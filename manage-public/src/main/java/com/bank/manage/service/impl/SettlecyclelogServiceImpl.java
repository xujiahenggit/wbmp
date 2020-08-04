package com.bank.manage.service.impl;

import com.bank.manage.dao.SettlecyclelogDao;
import com.bank.manage.dos.SettlecyclelogDO;
import com.bank.manage.service.SettlecyclelogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 未清机信息
 *
 * @author
 * @date 2020-7-4
 */
@Service
@Slf4j
public class SettlecyclelogServiceImpl extends ServiceImpl<SettlecyclelogDao, SettlecyclelogDO> implements SettlecyclelogService {

    @Autowired(required = false)
    private SettlecyclelogDao sd;


    @Override
    public SettlecyclelogDO getOne(String termNum) {
        SettlecyclelogDO s1 = sd.querySettlecyclelog(termNum);
        if (s1 == null) {
            return null;
        }
        SettlecyclelogDO s2 = sd.queryDeposit(termNum);
        SettlecyclelogDO s3 = sd.queryWithdraw(termNum);
        SettlecyclelogDO build = SettlecyclelogDO.builder()
                .id(s1.getId()).strTermNum(s1.getStrTermNum()).settleCycle(s1.getSettleCycle())
                .dtStart(s1.getDtStart()).cdmRefillCashAmt(s1.getCdmRefillCashAmt())
                .cwdAmount(s3.getCwdAmount()).cwdCount(s3.getCwdCount())
                .depAmount(s2.getDepAmount()).depCount(s2.getDepCount()).build();
        log.info("未清机信息：{}", build);
        return build;
    }
}
