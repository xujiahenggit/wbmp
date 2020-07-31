package com.bank.manage.service;


import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.WorkWaterDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 工单流水表 服务类
 *
 * @author 代码自动生成
 * @since 2020-07-13
 */
public interface ManageWorkWaterService extends IService<WorkWaterDO> {

    /**
     * 自定义分页
     * @param pageQueryModel
     * @return
     */
    IPage<WorkWaterDO> listPage(PageQueryModel pageQueryModel);

    List<Map<String, String>> getwater(String orderId);
}
