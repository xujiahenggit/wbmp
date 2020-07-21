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
import com.bank.manage.vo.OrgScoreVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    public void saveScore(List<WbmpMangementScoreDO> listManagement, List<WbmpOperateScoreDO> listOperate,String date) {
        try {

            //保存之前清空经营数据
            QueryWrapper<WbmpOperateScoreDO> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("OPERATE_DATE",date);
            this.remove(queryWrapper);

            //保存之前 清空 运营数据
            QueryWrapper<WbmpMangementScoreDO> queryWrapper1=new QueryWrapper<>();

            queryWrapper1.eq("MANAGEMENT_DATE",date);
            wbmpMangementScoreService.remove(queryWrapper1);

            //保存经营分数
            saveBatch(listOperate);
            //保存运营分数
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

        //日均业务量
        //全行业务标准量
        float qhbzywl=wbmpCommonCalcMethodDO.getStandDayilTrafficNum().floatValue();
        float rjywl=WbmpOperRateUtils.getTranNumAvg(total,new BigDecimal(qhbzywl));

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


        // 无纸化占比
        float wzhper=(wbmpCommonCalcMethodDO.getWzhPer().floatValue())/100;
        //银企对账率占比
        float yqper=wbmpCommonCalcMethodDO.getYqdzPer().floatValue()/100;
        //电子对账率占比
        float dzper=(wbmpCommonCalcMethodDO.getDzdzPer().floatValue())/100;
        //对公账户开通率占比
        float dgper=(wbmpCommonCalcMethodDO.getDgzhxskhPer().floatValue())/100;
        //企业客户线上开通率
        float qykhper=(wbmpCommonCalcMethodDO.getQykhxsktPer().floatValue())/100;
        //现金业务分流率占比
        float xjper=(wbmpCommonCalcMethodDO.getXjywflPer().floatValue())/100;
        //自助设备分摊率
        float zzsbftl=(wbmpCommonCalcMethodDO.getZzsbywfdPer().floatValue())/100;
        //自助设备可用率
        float zzsbkyPer=(wbmpCommonCalcMethodDO.getZzsbkyPer().floatValue())/100;
        //赛马制
        float smz=0;
        //赛马制列表

        //由于赛马制的数据是月度数据，所以时间要取 上个月的
        String dateSmz= LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")).minusMonths(1).toString().substring(0,7);
        List<WbmpOperateRacingIndexMDO> listRacing = wbmpOperateRacingIndexMService.getRacingList(orgId,dateSmz);
        for (WbmpOperateRacingIndexMDO item : listRacing) {
            //无纸化业务取消率(12.5%)
            if(WbmpConstFile.SMZ_RACING_001.equals(item.getIndexNo())){
                wzh= WbmpOperRateUtils.Maht2digit(100-(item.getIndexVal().floatValue()*wzhper*100));
            }
            //S21212=银企对账率(12.5%)
            if(WbmpConstFile.SMZ_RACING_003.equals(item.getIndexNo())){
                yq=WbmpOperRateUtils.Maht2digit(item.getIndexVal().floatValue()*yqper*100);
            }
            //电子对账率(12.5%)[源数据电子对账率已经*100]
            if(WbmpConstFile.SMZ_RACING_004.equals(item.getIndexNo())){
                dz=WbmpOperRateUtils.Maht2digit(item.getIndexVal().floatValue()*dzper);
            }
            //对公账户线上开户率(12.5%)
            if(WbmpConstFile.SMZ_RACING_007.equals(item.getIndexNo())){
                dg=WbmpOperRateUtils.Maht2digit(item.getIndexVal().floatValue()*dgper*100);
            }
            //企业客户线上开通率(12.5%)
            if(WbmpConstFile.SMZ_RACING_008.equals(item.getIndexNo())){
                qy=WbmpOperRateUtils.Maht2digit(item.getIndexVal().floatValue()*qykhper*100);
            }
            //现金业务分流率(12.5%)
            if(WbmpConstFile.SMZ_RACING_005.equals(item.getIndexNo())){
                xj=WbmpOperRateUtils.Maht2digit(item.getIndexVal().floatValue()*xjper*100);
            }
            // S21217=自助设备业务分担率(12.5%)
            if(WbmpConstFile.SMZ_RACING_006.equals(item.getIndexNo())){
                zzft=WbmpOperRateUtils.Maht2digit(item.getIndexVal().floatValue()*zzsbftl*100);
            }
            //S21218=自助设备可用率(12.5%)
            if(WbmpConstFile.SMZ_RACING_002.equals(item.getIndexNo())){
                zzky=WbmpOperRateUtils.Maht2digit(item.getIndexVal().floatValue()*zzsbkyPer*100);
            }
            smz=WbmpOperRateUtils.smz(wzh,yq,dz,dg,qy,xj,zzft,zzky);
        }
        //S2211对外服务水平（面向客户）
        float dwfwsp=0;
        //客户平均等待时长
        float custmerWaitTime=wbmpOperateBqmsQueueAvgService.getCustmerWaitTime(orgId,date);
        dwfwsp=WbmpOperRateUtils.getCustomerWaitTimeAvgScore(new BigDecimal(custmerWaitTime),wbmpCommonCalcMethodDO.getStandAvgWaitTime());

        //运营效能  日均业务量*占比
        //运营效能占比
        float yyxnPer=wbmpCommonCalcMethodDO.getOperatEffectPer().floatValue()/100;

        float yyxn=WbmpOperRateUtils.Maht2digit(rjywl*yyxnPer);
        //赛马制
        //赛马制占比
        float smzPer=wbmpCommonCalcMethodDO.getSaimazhiPer().floatValue()/100;
        float smz_s=WbmpOperRateUtils.Maht2digit(smz*smzPer);
        //网点服务水平
        //网点服务水平占比
        float wdfwspPer=wbmpCommonCalcMethodDO.getServiceLevelPer().floatValue()/100;
        float wdfwsp_s=WbmpOperRateUtils.Maht2digit(dwfwsp*wdfwspPer);


        //网点运营视图
        //占比
        float wdyystPer=wbmpCommonCalcMethodDO.getOperatViewPer().floatValue()/100;
        float wdyyst=WbmpOperRateUtils.Maht2digit(((yyxn+smz_s))*wdyystPer);
        //网点服务视图
        float wdfwstPer=wbmpCommonCalcMethodDO.getServiceViewPer().floatValue()/100;
        float wdfwst=WbmpOperRateUtils.Maht2digit(wdfwsp_s*wdfwstPer);

        //数据不全暂时 只返回0
        OperScore=WbmpOperRateUtils.Maht2digit((wdyyst+wdfwst));
        return OperScore;
    }


    @Override
    public List<String> calcOperateScore(String orgId, List<String> times, String queryType) {
        List<String> scores = new ArrayList<String>();
        List<OrgScoreVo> queryResult = new ArrayList<OrgScoreVo>();
        if(WbmpConstFile.DATE_TYPE_YEAR.equals(queryType)){

            queryResult = wbmpOperateScoreDao.queryByYear(orgId);

        }else if(WbmpConstFile.DATE_TYPE_JIDU.equals(queryType)){

            queryResult = wbmpOperateScoreDao.queryByQuart(orgId);

        }else if(WbmpConstFile.DATE_TYPE_MONTH.equals(queryType)){

            queryResult =wbmpOperateScoreDao.queryByMonth(orgId);
        }
        for (String str:times){
            String scoreStr = "0";
            for(OrgScoreVo scoreVo:queryResult){
                if(str.equals(scoreVo.getDateDt())){
                    scoreStr = scoreVo.getScore();
                }
            }
            scores.add(scoreStr);
        }
        return scores;
    }
}
