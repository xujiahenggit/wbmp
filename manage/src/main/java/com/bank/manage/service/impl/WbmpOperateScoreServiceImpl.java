package com.bank.manage.service.impl;

import com.bank.core.entity.BizException;
import com.bank.core.sysConst.WbmpConstFile;
import com.bank.manage.dao.WbmpOperateScoreDao;
import com.bank.manage.dos.WbmpCommonCalcMethodDO;
import com.bank.manage.dos.WbmpMangementScoreDO;
import com.bank.manage.dos.WbmpOperateRacingIndexMDO;
import com.bank.manage.dos.WbmpOperateScoreDO;
import com.bank.manage.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
        //数据占比配置
        //S2111日均业务量 =网点日均业务量（柜面+自助）/流水总数
        int absNum = wbmpAbsTransinfoService.getCurrentMonthTrandNum(orgId, date);
        int atmNum = wbmpAtmpTranInfoService.getCurrentMonthAtmTranNum(orgId, date);
        int total = absNum + atmNum;
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

        float smz=0;
        List<WbmpOperateRacingIndexMDO> listRacing = wbmpOperateRacingIndexMService.getRacingList(orgId, date);


        for (WbmpOperateRacingIndexMDO item : listRacing) {


        }
        //S2211对外服务水平（面向客户）

        return 0;
    }

    @Resource
    private WbmpOperateScoreDao wbmpOperateScoreDao;

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
