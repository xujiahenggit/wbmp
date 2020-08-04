package com.bank.manage.service;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.CassalertDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface CassalertService extends IService<CassalertDO> {
    IPage<CassalertDO> queryList(PageQueryModel pageQueryModel);

    CassalertDO queryBank(String strBankNum);

    CassalertDO queryBranch(String strBranchNum);

    boolean save(CassalertDO cassalertDO);

    boolean saveBank(CassalertDO cassalertDO);

    boolean saveBranch(CassalertDO cassalertDO);
}
