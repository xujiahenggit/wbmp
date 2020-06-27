package com.bank.manage.service;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.ExampleAdminDO;
import com.bank.manage.dos.ExampleBranchDO;
import com.baomidou.mybatisplus.core.metadata.IPage;

public interface ExampleBranchService {
    /**
     * 保存全国标杆网点统计数据
     * @param exampleAdminDO
     * @param exampleBranchDO
     */
    void saveExampleBranch(ExampleAdminDO exampleAdminDO, ExampleBranchDO exampleBranchDO);

    /**
     * 查询全国标杆网点统计数据
     * @param pageQueryModel
     * @return
     */
    IPage<ExampleAdminDO> queryExampleBranch(PageQueryModel pageQueryModel);
}
