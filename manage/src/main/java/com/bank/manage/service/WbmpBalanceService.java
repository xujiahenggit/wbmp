package com.bank.manage.service;


import com.bank.core.entity.PageQueryModel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bank.manage.dos.WbmpBalanceDO;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * 存款统计表 服务类
 *
 * @author zhaozhongyuan
 * @since 2020-06-16
 */
public interface WbmpBalanceService extends IService<WbmpBalanceDO> {

    /**
     * 自定义分页
     * @param pageQueryModel
     * @return
     */
    IPage<WbmpBalanceDO> listPage(PageQueryModel pageQueryModel);

}
