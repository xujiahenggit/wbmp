package com.bank.manage.service;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.CassalertDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface CassalertService extends IService<CassalertDO> {
    IPage<CassalertDO> queryList(PageQueryModel pageQueryModel, String orgcode);

    CassalertDO queryBank(String strBankNum, String orgcode);

    CassalertDO queryBranch(String strBranchNum, String orgcode);

    boolean save(CassalertDO cassalertDO);

    boolean saveBank(CassalertDO cassalertDO);

    boolean saveBranch(CassalertDO cassalertDO);
}
