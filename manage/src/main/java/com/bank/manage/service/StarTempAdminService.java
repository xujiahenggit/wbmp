package com.bank.manage.service;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.StarDataAdminDO;
import com.baomidou.mybatisplus.core.metadata.IPage;

public interface StarTempAdminService {
    /**
     * 行内星级标准化网点统计数据
     * @param starDataAdminDO
     */
    void saveStarData(StarDataAdminDO starDataAdminDO);

    /**
     * 删除行内星级标准化网点统计数据
     * @param id
     * @param dataType
     */
    void delStartData(String id, String dataType);

    /**
     * 查询行内星级标准化网点统计数据(admin)
     * @param pageQueryModel
     * @return
     */
    IPage<StarDataAdminDO> queryExampleBranchByAdmin(PageQueryModel pageQueryModel);
}
