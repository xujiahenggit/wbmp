package com.bank.manage.service;


import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.WbmpAbsTellerOnlineTimeDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * 柜员在线时长信息表 服务类
 *
 * @author 代码自动生成
 * @since 2020-07-03
 */
public interface WbmpAbsTellerOnlineTimeService extends IService<WbmpAbsTellerOnlineTimeDO> {

    /**
     * 自定义分页
     * @param page
     * @param wbmpAbsTellerOnlineTime
     * @return
     */
    IPage<WbmpAbsTellerOnlineTimeDO> listPage(PageQueryModel pageQueryModel);

    void  fillDataBeat();

    void  initData();

    void  fillDataBeatTest();
}
