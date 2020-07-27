package com.bank.manage.service.impl;

import com.bank.core.entity.BizException;
import com.bank.core.sysConst.WbmpConstFile;
import com.bank.core.utils.WbmpOperRateUtils;
import com.bank.manage.dao.WbmpCommonCalcMethodDao;
import com.bank.manage.dao.WbmpOperateCustDao;
import com.bank.manage.dao.WbmpOperateIndexAumDao;
import com.bank.manage.dao.WbmpOrgBalanceDao;
import com.bank.manage.dos.WbmpCommonCalcMethodDO;
import com.bank.manage.service.OperateCurveService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 获取经营视图数据实现类
 *
 * @author zhangfuqiang
 * @since 2020-06-16
 */
@Service
public class OperaCurveServiceImpl implements OperateCurveService {
    @Resource
    private WbmpOrgBalanceDao wbmpOrgBalanceDao;

    @Resource
    private WbmpOperateCustDao wbmpOperateCustDao;

    @Resource
    private WbmpOperateIndexAumDao wbmpOperateIndexAumDao;

    @Resource
    private WbmpCommonCalcMethodDao wbmpCommonCalcMethodDao;


    @Override
    public float calcOrgMonthScore(String orgId, String date) {
        //获取参数表数据
        WbmpCommonCalcMethodDO wbmpCommonCalcMethodDO = wbmpCommonCalcMethodDao.selectOne(new QueryWrapper<>());
        if (wbmpCommonCalcMethodDO == null) {
            //TODO抛出错误
            throw new BizException("获取通用配置失败！");
        }
        //全行标准月日均存款值
        BigDecimal standAvgMouthBal = wbmpCommonCalcMethodDO.getStandAvgMouthBal();
        //全行标准aum值
        BigDecimal standAumNum = wbmpCommonCalcMethodDO.getStandAumNum();
        //月日均存款值百分比
        BigDecimal avgMouthBalPer = wbmpCommonCalcMethodDO.getAvgMouthBalPer();
        //aum百分比
        BigDecimal aumPer = wbmpCommonCalcMethodDO.getAumPer();
        //获取机构余额
        String orgBal = wbmpOrgBalanceDao.getOrgHistoryBal(orgId, date);

        BigDecimal orgBalNum = new BigDecimal(0);
        if (orgBal != null) {
            orgBalNum = new BigDecimal(orgBal);
        }
        //获取机构的AUM值
        String orgAum = wbmpOperateIndexAumDao.getOrgDaysAum(orgId, date);

        BigDecimal orgAumNum = new BigDecimal(0);
        if (orgAum != null) {
            orgAumNum = new BigDecimal(orgAum);
        }

        //计算月日均存款分数
        float mouthAvgBalScore = WbmpOperRateUtils.calcAvgMouthBal(orgBalNum, standAvgMouthBal);
        //计算AUM分数
        float aumScore = WbmpOperRateUtils.calcOrgAum(orgAumNum, standAumNum);
        //S11存款的分数
        float balSocre = WbmpOperRateUtils.calcOrgBal(mouthAvgBalScore, avgMouthBalPer, aumScore, aumPer);
        //全行普通客户标准客户量参数
        BigDecimal standNormalCusNum = wbmpCommonCalcMethodDO.getStandNormalCusNum();
        //全行金卡客户标准客户量参数
        BigDecimal standGoldCusNum = wbmpCommonCalcMethodDO.getStandGoldCusNum();
        //全行白金客户标准客户量参数
        BigDecimal standPlatinumCusNum = wbmpCommonCalcMethodDO.getStandPlatinumCusNum();
        //全行钻石客户标准客户量参数
        BigDecimal standDiamonCusNum = wbmpCommonCalcMethodDO.getStandDiamonCusNum();
        //客户所占分数百分比
        BigDecimal normal_cus_per = wbmpCommonCalcMethodDO.getNormalCusPer();
        BigDecimal gold_cus_per = wbmpCommonCalcMethodDO.getGoldCusPer();
        BigDecimal platinum_cus_per = wbmpCommonCalcMethodDO.getPlatinumCusPer();
        BigDecimal diamon_cus_per = wbmpCommonCalcMethodDO.getDiamonCusPer();

        //获取数据库中的不同类型的客户量
        float normalCust = wbmpOperateCustDao.getOrgCustTypeNum(orgId, WbmpConstFile.CUSTOMER_TYPE_CUST_001, date);
        float goldCust = wbmpOperateCustDao.getOrgCustTypeNum(orgId, WbmpConstFile.CUSTOMER_TYPE_CUST_002, date);
        float platinumCust = wbmpOperateCustDao.getOrgCustTypeNum(orgId, WbmpConstFile.CUSTOMER_TYPE_CUST_003, date);
        float diamonCust = wbmpOperateCustDao.getOrgCustTypeNum(orgId, WbmpConstFile.CUSTOMER_TYPE_CUST_004, date);
        //计算分数
        float normalScore = WbmpOperRateUtils.calcCustNumScore(normalCust, standNormalCusNum);
        float goldScore = WbmpOperRateUtils.calcCustNumScore(goldCust, standGoldCusNum);
        float platinumScore = WbmpOperRateUtils.calcCustNumScore(platinumCust, standPlatinumCusNum);
        float diamonScore = WbmpOperRateUtils.calcCustNumScore(diamonCust, standDiamonCusNum);
        //计算S12客群分数
        float custScore = WbmpOperRateUtils.calcCustScore(normalScore, normal_cus_per, goldScore, gold_cus_per, platinumScore, platinum_cus_per, diamonScore, diamon_cus_per);

        //存款所占百分比
        BigDecimal bal_per = wbmpCommonCalcMethodDO.getBalPer();
        //客群所在百分比
        BigDecimal cus_per = wbmpCommonCalcMethodDO.getCusPer();
        float openRateScore = WbmpOperRateUtils.calcOpenRateScore(balSocre, bal_per, custScore, cus_per);
        return openRateScore;
    }
}
