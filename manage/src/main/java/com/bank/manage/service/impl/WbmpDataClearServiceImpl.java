package com.bank.manage.service.impl;

import com.bank.core.entity.BizException;
import com.bank.manage.dao.WbmpDataClearDao;
import com.bank.manage.service.WbmpDataClearService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class WbmpDataClearServiceImpl implements WbmpDataClearService {

    @Resource
    private WbmpDataClearDao wbmpDataClearDao;

    @Override
    public Boolean bakWbmpAbsTransinfo(String date) {
      Boolean flag = false;
      int result =  wbmpDataClearDao.bakWbmpAbsTransinfo(date);
      if(result >=0){
          flag = true;
          log.info("【备份日期为："+date+",wbmp_abs_transinfo 成功！】");
      }else {
            throw new BizException("备份日期为："+date+"wbmp_abs_transinfo 失败！");
        }
        return flag;
    }

    @Override
    public int deleteWbmpAbsTransinfo(String date) {
        log.info("【删除："+date+",柜面交易明细表[WbmpAbsTransinfo]】");
        return wbmpDataClearDao.deleteWbmpAbsTransinfo(date);
    }

    @Override
    public int deleteWbmpAbsTransinfoH(String date) {
        log.info("【删除："+date+",柜面交易明细历史表[WbmpAbsTransinfoH]】");
        return wbmpDataClearDao.deleteWbmpAbsTransinfoH(date);
    }

    @Override
    public int deleteWbmpAbsTellerOnlineTime(String date) {
        log.info("【删除："+date+",柜员在线时长明细表清理策略[WbmpAbsTellerOnlineTime]】");
        return wbmpDataClearDao.deleteWbmpAbsTellerOnlineTime(date);
    }

    @Override
    public int deleteWbmpAbsTradeTime(String date) {
        log.info("【删除："+date+",柜员交易耗时表[WbmpAbsTradeTime]】");
        return wbmpDataClearDao.deleteWbmpAbsTradeTime(date);
    }

    @Override
    public int bakWbmpAtmpTranInfo(String date) {
        log.info("【备份："+date+",自助设备交易表1[WbmpAtmpTranInfo]】1");
        return wbmpDataClearDao.bakWbmpAtmpTranInfo(date);
    }

    @Override
    public int deleteWbmpAtmpTranInfo(String date) {
        log.info("【删除："+date+",自助设备交易表[WbmpAtmpTranInfo]】2");
        return wbmpDataClearDao.deleteWbmpAtmpTranInfo(date);
    }

    @Override
    public int deleteWbmpAtmpTranInfoH(String date) {
        log.info("【删除："+date+",自助设备交易表[WbmpAtmpTranInfoH]】3");
        return wbmpDataClearDao.deleteWbmpAtmpTranInfoH(date);
    }

    @Override
    public int deleteWbmpBqmsQueue(String date) {
        log.info("【删除："+date+",排队机表数据");
        return wbmpDataClearDao.deleteWbmpBqmsQueue(date);
    }

    @Override
    public int deleteWbmpOperateBqmsQueueAvg(String date) {
        log.info("【删除："+date+",客户满意度参数表T+1");
        return wbmpDataClearDao.deleteWbmpOperateBqmsQueueAvg(date);
    }

    @Override
    public int deleteWbmpOperateIndexAum(String date) {
        log.info("【删除："+date+",网点AUM表T+1");
        return wbmpDataClearDao.deleteWbmpOperateIndexAum(date);
    }

    @Override
    public int deleteWbmpOperateRacingIndexM(String date) {
        log.info("【删除："+date+",赛马制表T+1");
        return wbmpDataClearDao.deleteWbmpOperateRacingIndexM(date);
    }

    @Override
    public int deleteWbmpOrgBalance(String date) {
        log.info("【删除："+date+",网点实时余额");
        return wbmpDataClearDao.deleteWbmpOrgBalance(date);
    }

    @Override
    public int deleteWbmpOrgBatchBalance(String date) {
        log.info("【删除："+date+",网点历史离线余额");
        return wbmpDataClearDao.deleteWbmpOrgBatchBalance(date);
    }

    @Override
    public int deleteWbmpOrgCurrency(String date) {
        log.info("【删除："+date+",汇率表");
        return wbmpDataClearDao.deleteWbmpOrgCurrency(date);
    }

    @Override
    public int bakWbmpOperateCust(String date) {
        log.info("【备份："+date+",客群指标表 T+1");
        return wbmpDataClearDao.bakWbmpOperateCust(date);
    }

    @Override
    public int deleteWbmpOperateCust(String date) {
        log.info("【删除："+date+",客群指标表 T+1");
        return wbmpDataClearDao.deleteWbmpOperateCust(date);
    }

    @Override
    public int deleteWbmpOperateCustH(String date) {
        log.info("【删除："+date+",客群指标历史表 T+1");
        return wbmpDataClearDao.deleteWbmpOperateCustH(date);
    }


}
