package com.bank.manage.service;


import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.WbmpOperateIndexAumDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * 网点AUM表 服务类
 *
 * @author zhaozhongyuan
 * @since 2020-06-18
 */
public interface WbmpOperateIndexAumService extends IService<WbmpOperateIndexAumDO> {

    /**
     * 自定义分页
     * @param pageQueryModel
     * @return
     */
    IPage<WbmpOperateIndexAumDO> listPage(PageQueryModel pageQueryModel);

}
