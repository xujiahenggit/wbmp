package com.bank.manage.service.impl;

import com.bank.manage.dao.WbmpAtmpTranInfoDao;
import com.bank.manage.dos.WbmpAtmpTranInfoDO;
import com.bank.manage.service.WbmpAtmpTranInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Author: Andy
 * @Date: 2020/6/17 10:30
 */
@Service
public class WbmpAtmpTranInfoServiceImpl extends ServiceImpl<WbmpAtmpTranInfoDao, WbmpAtmpTranInfoDO> implements WbmpAtmpTranInfoService {

    /**
     * 查询 当月 自助交易量
     * @param orgId 机构号
     * @param date 日期
     * @return
     */
    @Override
    public int getCurrentMonthAtmTranNum(String orgId, String date) {
        return 0;
    }

    /**
     * 查询 业务流水量
     *
     * @param orgId 机构号
     * @param date  日期
     * @return
     */
    @Override
    public int getFlowNum(String orgId, String date) {
        return 0;
    }
}
