package com.bank.user.service;

import com.bank.user.dao.NfrtOrgTempDao;
import com.bank.user.dos.NfrtOrgTempDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Author: Andy
 * @Date: 2020/6/10 16:32
 */
public interface NfrtOrgTempService extends IService<NfrtOrgTempDO> {

    /**
     * 清空 核心机构中间表
     */
    void ClearnNfrtOrgTemp();
}
