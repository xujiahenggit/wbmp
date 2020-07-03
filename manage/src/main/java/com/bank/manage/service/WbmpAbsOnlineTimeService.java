package com.bank.manage.service;


import com.bank.core.entity.PageQueryModel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bank.manage.dos.WbmpAbsOnlineTimeDO;
import com.bank.manage.vo.WbmpAbsOnlineTimeVO;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * 柜员在线时长明细表 服务类
 *
 * @author 代码自动生成
 * @since 2020-07-03
 */
public interface WbmpAbsOnlineTimeService extends IService<WbmpAbsOnlineTimeDO> {

    /**
     * 自定义分页
     * @param page
     * @param wbmpAbsOnlineTime
     * @return
     */
    IPage<WbmpAbsOnlineTimeDO> listPage(PageQueryModel pageQueryModel);

}
