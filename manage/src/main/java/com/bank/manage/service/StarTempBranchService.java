package com.bank.manage.service;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.StarDataAdminDO;
import com.bank.manage.dos.StarDataTempBranchDO;
import com.baomidou.mybatisplus.core.metadata.IPage;

public interface StarTempBranchService {
    void saveStarData(StarDataAdminDO starDataAdminDO, StarDataTempBranchDO starDataTempBranchDO);

    IPage<StarDataAdminDO> queryExampleBranch(PageQueryModel pageQueryModel);
}
