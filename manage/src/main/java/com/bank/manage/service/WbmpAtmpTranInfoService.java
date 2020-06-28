package com.bank.manage.service;

import com.bank.manage.dos.WbmpAtmpTranInfoDO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: Andy
 * @Date: 2020/6/17 10:28
 */
public interface WbmpAtmpTranInfoService extends IService<WbmpAtmpTranInfoDO> {

    /**
     * 查询 自助交易业务量
     * @param orgId 机构号
     * @param date 日期
     * @return
     */
    int getCurrentMonthAtmTranNum(@Param(value = "orgId") String orgId, @Param(value = "date") String date);

    /**
     * 查询 业务流水量
     * @param orgId 机构号
     * @param date 日期
     * @return
     */
    int getFlowNum(@Param(value = "orgId") String orgId, @Param(value = "date") String date);
}
