package com.bank.manage.service.impl;

import com.bank.manage.dao.WbmpAtmpTranInfoDao;
import com.bank.manage.dos.WbmpAtmpTranInfoDO;
import com.bank.manage.service.WbmpAtmpTranInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: Andy
 * @Date: 2020/6/17 10:30
 */
@Service
public class WbmpAtmpTranInfoServiceImpl extends ServiceImpl<WbmpAtmpTranInfoDao, WbmpAtmpTranInfoDO> implements WbmpAtmpTranInfoService {

    @Resource
    private WbmpAtmpTranInfoDao wbmpAtmpTranInfoDao;

    /**
     * 查询自助交易量
     * @param orgId 机构号
     * @param date 日期
     * @return
     */
    @Override
    public int getCurrentMonthAtmTranNum(String orgId, String date) {
        return wbmpAtmpTranInfoDao.getTranNumByOrgId(orgId,date);
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

    @Override
    public int getAllTranNumByOrgId(String orgId, String date) {
        return wbmpAtmpTranInfoDao.getAllTranNumByOrgId(orgId,date);
    }


}
