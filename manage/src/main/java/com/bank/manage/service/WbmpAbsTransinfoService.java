package com.bank.manage.service;

import com.bank.manage.dos.WbmpAbsTransinfoDO;
import com.bank.manage.dto.WbmpAbsAtmTranInfoDto;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author: Andy
 * @Date: 2020/6/17 10:29
 */
public interface WbmpAbsTransinfoService extends IService<WbmpAbsTransinfoDO> {
    /**
     * 查询柜面自助交易占比
     * @param orgId 机构编号
     * @return
     */
    WbmpAbsAtmTranInfoDto counterDealProportion(String orgId);

    /**
     * 查询 当月 柜面交易量
     * @param orgId 机构号
     * @param date 日期 yyyy-MM
     * @return
     */
    int getCurrentMonthTrandNum(String orgId,String date);
}