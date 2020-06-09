package com.bank.user.dao;

import com.bank.user.dos.NfrtOrgDO;
import com.bank.user.dos.NfrtOrgTempDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Author: Andy
 * @Date: 2020/6/10 16:31
 */
public interface NfrtOrgTempDao extends BaseMapper<NfrtOrgTempDO> {
    /**
     * 清空核心机构文件 中间表
     */
    void ClearnNfrtOrgTemp();
}
