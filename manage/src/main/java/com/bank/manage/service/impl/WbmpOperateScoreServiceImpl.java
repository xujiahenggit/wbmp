package com.bank.manage.service.impl;

import com.bank.core.entity.BizException;
import com.bank.core.sysConst.WbmpConstFile;
import com.bank.core.utils.WbmpOperRateUtils;
import com.bank.manage.dao.WbmpOperateScoreDao;
import com.bank.manage.dos.WbmpCommonCalcMethodDO;
import com.bank.manage.dos.WbmpMangementScoreDO;
import com.bank.manage.dos.WbmpOperateRacingIndexMDO;
import com.bank.manage.dos.WbmpOperateScoreDO;
import com.bank.manage.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/6/19 9:37
 */
@Service
public class WbmpOperateScoreServiceImpl extends ServiceImpl<WbmpOperateScoreDao, WbmpOperateScoreDO> implements WbmpOperateScoreService {

    @Resource
    private WbmpMangementScoreService wbmpMangementScoreService;

    @Resource
    private WbmpAbsTransinfoService wbmpAbsTransinfoService;

    @Resource
    private WbmpAtmpTranInfoService wbmpAtmpTranInfoService;

    @Resource
    private WbmpOperateRacingIndexMService wbmpOperateRacingIndexMService;

    @Resource
    private WbmpCommonCalcMethodService wbmpCommonCalcMethodService;

    @Resource
    private WbmpOperateBqmsQueueAvgService wbmpOperateBqmsQueueAvgService;


    @Resource
    private WbmpOperateScoreDao wbmpOperateScoreDao;

    /**
     * 保存得分数据
     *
     * @param listManagement 经营得分数据
     * @param listOperate    运营得分数据
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveScore(List<WbmpMangementScoreDO> listManagement, List<WbmpOperateScoreDO> listOperate) {
        try {
            this.saveBatch(listOperate);
            wbmpMangementScoreService.saveBatch(listManagement);
        } catch (Exception e) {
            throw new BizException("经营 运营 综合得分数据保存失败！");
        }
    }

    /**
     * 计算运营得分情况
     *
     * @param orgId 机构编号
     * @param date  时间
     * @return
     */
    @Override
    public float calOperScore(String orgId, String date) {
        float OperScore=0;

        //通用配置
        WbmpCommonCalcMethodDO wbmpCommonCalcMethodDO=wbmpCommonCalcMethodService.getOne(new QueryWrapper<>());

        //数据占比配置
        //S2111日均业务量 =网点日均业务量（柜面+自助）/流水总数
        int absNum = wbmpAbsTransinfoService.getCurrentMonthTrandNum(orgId, date);
        int atmNum = wbmpAtmpTranInfoService.getCurrentMonthAtmTranNum(orgId, date);
        int total = absNum + atmNum;

        float rjywl=WbmpOperRateUtils.getTranNumAvg(wbmpCommonCalcMethodDO.getStandDayilTrafficNum(),new BigDecimal(total));

        //S2121赛马制（100%）
        // S21211=无纸化业务取消率(12.5%)
        float wzh = 0;
        // S21212=银企对账率(12.5%)
        float yq = 0;
        // S21213=电子对账率(12.5%)
        float dz = 0;
        // S21214=对公账户线上开户率(12.5%)
        float dg = 0;
        // S21215=企业客户线上开通率(12.5%)
        float qy = 0;
        // S21216=现金业务分流率(12.5%)
        float xj = 0;
        // S21217=自助设备业务分担率(12.5%)
        float zzft = 0;
        // S21218=自助设备可用率(12.5%)
        float zzky = 0;
        //赛马制
        float smz=0;
        //赛马制列表
        List<WbmpOperateRacingIndexMDO> listRacing = wbmpOperateRacingIndexMService.getRacingList(orgId, date);

        for (WbmpOperateRacingIndexMDO item : listRacing) {
            //无纸化业务取消率(12.5%)
            if(WbmpConstFile.SMZ_RACING_001.equals(item.getIndexNo())){
                wzh= WbmpOperRateUtils.Maht2digit(item.getIndexVal().floatValue()*(wbmpCommonCalcMethodDO.getWzhPer().floatValue())/100);
            }
            //S21212=银企对账率(12.5%)
            if(WbmpConstFile.SMZ_RACING_002.equals(item.getIndexNo())){
                yq=WbmpOperRateUtils.Maht2digit(item.getIndexVal().floatValue()*(wbmpCommonCalcMethodDO.getYqdzPer().floatValue())/100);
            }
            //电子对账率(12.5%)
            if(WbmpConstFile.SMZ_RACING_004.equals(item.getIndexNo())){
                yq=WbmpOperRateUtils.Maht2digit(item.getIndexVal().floatValue()*(wbmpCommonCalcMethodDO.getDzdzPer().floatValue())/100);
            }
            //对公账户线上开户率(12.5%)
            if(WbmpConstFile.SMZ_RACING_007.equals(item.getIndexNo())){
                yq=WbmpOperRateUtils.Maht2digit(item.getIndexVal().floatValue()*(wbmpCommonCalcMethodDO.getDzdzPer().floatValue())/100);
            }
            //企业客户线上开通率(12.5%)
            if(WbmpConstFile.SMZ_RACING_008.equals(item.getIndexNo())){
                yq=WbmpOperRateUtils.Maht2digit(item.getIndexVal().floatValue()*(wbmpCommonCalcMethodDO.getQykhxsktPer().floatValue())/100);
            }
            //现金业务分流率(12.5%)
            if(WbmpConstFile.SMZ_RACING_005.equals(item.getIndexNo())){
                yq=WbmpOperRateUtils.Maht2digit(item.getIndexVal().floatValue()*(wbmpCommonCalcMethodDO.getXjywflPer().floatValue())/100);
            }
            // S21217=自助设备业务分担率(12.5%)
            if(WbmpConstFile.SMZ_RACING_006.equals(item.getIndexNo())){
                yq=WbmpOperRateUtils.Maht2digit(item.getIndexVal().floatValue()*(wbmpCommonCalcMethodDO.getZzsbywfdPer().floatValue())/100);
            }
            //S21218=自助设备可用率(12.5%)
            if(WbmpConstFile.SMZ_RACING_002.equals(item.getIndexNo())){
                yq=WbmpOperRateUtils.Maht2digit(item.getIndexVal().floatValue()*(wbmpCommonCalcMethodDO.getZzsbkyPer().floatValue())/100);
            }
            smz=WbmpOperRateUtils.smz(wzh,yq,dz,dg,qy,xj,zzft,zzky);
        }
        //S2211对外服务水平（面向客户）
        float dwfwsp=0;
        //客户平均等待时长
        float custmerWaitTime=wbmpOperateBqmsQueueAvgService.getCustmerWaitTime(orgId,date);
        dwfwsp=WbmpOperRateUtils.getCustomerWaitTimeAvgScore(new BigDecimal(custmerWaitTime),wbmpCommonCalcMethodDO.getStandAvgWaitTime());

        //运营效能  日均业务量*占比
        float yyxn=WbmpOperRateUtils.Maht2digit(rjywl*wbmpCommonCalcMethodDO.getOperatEffectPer().floatValue()/100);
        //赛马制
        float smz_s=WbmpOperRateUtils.Maht2digit(smz*wbmpCommonCalcMethodDO.getSaimazhiPer().floatValue()/100);
        //网点服务水平
        float wdfwsp_s=WbmpOperRateUtils.Maht2digit(dwfwsp*wbmpCommonCalcMethodDO.getServiceLevelPer().floatValue()/100);


        //网点运营视图
        float wdyyst=WbmpOperRateUtils.Maht2digit(((yyxn+smz_s))*wbmpCommonCalcMethodDO.getOperatViewPer().floatValue()/100);
        //网点服务视图
        float wdfwst=WbmpOperRateUtils.Maht2digit(wdfwsp_s*wbmpCommonCalcMethodDO.getServiceViewPer().floatValue()/100);

        //数据不全暂时 只返回0
        OperScore=WbmpOperRateUtils.Maht2digit((wdfwst+wdfwst));
        return OperScore;
    }


    @Override
    public List<Float> calcOperateScore(String orgId, List<String> times, String queryType) {
        List<Float> scores = new ArrayList<Float>();
        for (String str : times) {
            if (WbmpConstFile.DATE_TYPE_YEAR.equals(queryType)) {
                Float score = wbmpOperateScoreDao.findOperateYearOrgScore(orgId, str);
                scores.add(score);
            } else {
                Float score = wbmpOperateScoreDao.findOperateOrgMouthScore(orgId, str);
                scores.add(score);
            }
        }
        return scores;
    }
}
