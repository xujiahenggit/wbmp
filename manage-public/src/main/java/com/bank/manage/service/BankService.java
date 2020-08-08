package com.bank.manage.service;

import com.bank.core.config.DataSource;
import com.bank.core.config.DynamicDataSourceSwitcher;
import com.bank.manage.dos.BankDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface BankService extends IService<BankDO> {
    String selectByOrgcode(String orgcode);

    List<BankDO> queryList(String powerNum);

    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    String getBankName(String bankNum);

    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    String getSsbName(String ssbNum);

    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    String getBranchName(String branchNum);

    @DataSource(DynamicDataSourceSwitcher.esb_mgt)
    String getSubBranchName(String subBranchNum);
}
