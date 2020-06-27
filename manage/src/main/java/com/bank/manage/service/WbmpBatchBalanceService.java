package com.bank.manage.service;


import com.bank.core.entity.PageQueryModel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bank.manage.dos.WbmpBatchBalanceDO;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * 离线存款统计表 服务类
 *
 * @author zhaozhongyuan
 * @since 2020-06-16
 */
public interface WbmpBatchBalanceService extends IService<WbmpBatchBalanceDO> {

    /**
     * 自定义分页
     * @param pageQueryModel
     * @return
     */
    IPage<WbmpBatchBalanceDO> listPage(PageQueryModel pageQueryModel);

}
