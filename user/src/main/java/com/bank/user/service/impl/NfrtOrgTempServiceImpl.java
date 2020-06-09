package com.bank.user.service.impl;

import com.bank.user.dao.NfrtOrgTempDao;
import com.bank.user.dos.NfrtOrgTempDO;
import com.bank.user.service.NfrtOrgTempService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: Andy
 * @Date: 2020/6/10 16:34
 */
@Service
public class NfrtOrgTempServiceImpl extends ServiceImpl<NfrtOrgTempDao, NfrtOrgTempDO> implements NfrtOrgTempService {

    @Resource
    private NfrtOrgTempDao nfrtOrgTempDao;

    /**
     * 清空 核心机构中间表
     */
    @Override
    public void ClearnNfrtOrgTemp() {
        nfrtOrgTempDao.ClearnNfrtOrgTemp();
    }
}
