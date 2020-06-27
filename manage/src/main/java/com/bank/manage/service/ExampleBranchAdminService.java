package com.bank.manage.service;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.ExampleAdminDO;
import com.baomidou.mybatisplus.core.metadata.IPage;

public interface ExampleBranchAdminService {
    /**
     * 保存全国标杆网点统计数据(admin)
     * @param exampleAdminDO
     */
    void saveExampleAdmin(ExampleAdminDO exampleAdminDO);

    /**
     * 删除全国标杆网点统计数据(admin)
     * @param id
     * @param dataType
     */
    void delExampleData(String id, String dataType);

    /**
     * 分页全国标杆网点统计数据(admin)
     * @param pageQueryModel
     * @return
     */
    IPage<ExampleAdminDO> queryExampleBranchByAdmin(PageQueryModel pageQueryModel);
}
