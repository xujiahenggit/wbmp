package com.bank.manage.service.impl;

import cn.hutool.core.date.DateUtil;
import com.bank.core.entity.BizException;
import com.bank.core.utils.WbmpOperRateUtils;
import com.bank.manage.dao.WbmpAbsTransinfoDao;
import com.bank.manage.dao.WbmpAtmpTranInfoDao;
import com.bank.manage.dos.WbmpAbsTransinfoDO;
import com.bank.manage.dto.WbmpAbsAtmTranInfoDto;
import com.bank.manage.service.WbmpAbsTransinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: Andy
 * @Date: 2020/6/17 10:30
 */
@Service
public class WbmpAbsTransinfoServiceImpl extends ServiceImpl<WbmpAbsTransinfoDao, WbmpAbsTransinfoDO> implements WbmpAbsTransinfoService {

    @Resource
    private WbmpAbsTransinfoDao wbmpAbsTransinfoDao;


    @Resource
    private WbmpAtmpTranInfoDao wbmpAtmpTranInfoDao;

    /**
     * 查询柜面自助交易占比
     * @param orgId 机构编号
     * @return
     */
    @Override
    public WbmpAbsAtmTranInfoDto counterDealProportion(String orgId) {
        String currentDate= DateUtil.today();

        WbmpAbsAtmTranInfoDto wbmpAbsAtmTranInfoDto=new WbmpAbsAtmTranInfoDto();
        try{
            //自助交易量
            int atmTranNum=wbmpAtmpTranInfoDao.getTranNumByOrgId(orgId,currentDate);
            //柜面交易量
            int absTranNum=wbmpAbsTransinfoDao.getTranNumByOrgId(orgId,currentDate);

            //总交易
            float totalTranum=atmTranNum+absTranNum;
            //自助交易量占比
            float atmRate=0;
            //柜面交易占比
            float absRate=0;

            if(0!=totalTranum){
                //计算自助交易占比
                atmRate=WbmpOperRateUtils.Maht2digit((float) (atmTranNum/totalTranum)*100);
                absRate=WbmpOperRateUtils.Maht2digit((float) (absTranNum/totalTranum)*100);
            }
            wbmpAbsAtmTranInfoDto.setAbsTranNum(absTranNum);
            wbmpAbsAtmTranInfoDto.setAbsTranRate(absRate);

            wbmpAbsAtmTranInfoDto.setAtmTranNum(atmTranNum);
            wbmpAbsAtmTranInfoDto.setAtmTranRate(atmRate);
        }catch (Exception e){
            throw new BizException("查询失败");
        }
        return wbmpAbsAtmTranInfoDto;
    }

    /**
     * 查询 当月 柜面交易量
     * @param orgId 机构号
     * @param date 日期 yyyy-MM
     * @return
     */
    @Override
    public int getCurrentMonthTrandNum(String orgId, String date) {
        int tranNum=wbmpAbsTransinfoDao.getTranNumByOrgId(orgId,date);
        return tranNum;
    }
}
