package com.bank.manage.service.impl;

import cn.hutool.core.date.DateUtil;
import com.bank.core.utils.DateUtils;
import com.bank.core.utils.WbmpOperRateUtils;
import com.bank.manage.dao.WbmpOperateBqmsQueueAvgDao;
import com.bank.manage.dos.WbmpOperateBqmsQueueAvgDO;
import com.bank.manage.dto.WbmpOperateBqmsQueueAvgDto;
import com.bank.manage.service.WbmpOperateBqmsQueueAvgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: Andy
 * @Date: 2020/6/12 16:57
 */
@Service
public class WbmpOperateBqmsQueueAvgServiceImpl extends ServiceImpl<WbmpOperateBqmsQueueAvgDao, WbmpOperateBqmsQueueAvgDO> implements WbmpOperateBqmsQueueAvgService {

    @Resource
    private WbmpOperateBqmsQueueAvgDao wbmpOperateBqmsQueueAvgDao;
    /**
     * 查询客户满意度
     * @param orgId 机构号
     * @return
     */
    @Override
    public WbmpOperateBqmsQueueAvgDto getOperraInfo(String orgId) {
        String date= DateUtil.today();
        WbmpOperateBqmsQueueAvgDto wbmpOperateBqmsQueueAvgDto=wbmpOperateBqmsQueueAvgDao.getOperraInfo(orgId,date);
        if(wbmpOperateBqmsQueueAvgDto!=null){
            wbmpOperateBqmsQueueAvgDto.setCunstomerAvg(WbmpOperRateUtils.ComputeCustomerAvg(wbmpOperateBqmsQueueAvgDto.getAvgAbandonedLv(),wbmpOperateBqmsQueueAvgDto.getIndexCnt()));
        }else{
            wbmpOperateBqmsQueueAvgDto=new WbmpOperateBqmsQueueAvgDto();
            wbmpOperateBqmsQueueAvgDto.setCunstomerAvg(0);
            wbmpOperateBqmsQueueAvgDto.setAbandonedLv(new BigDecimal(0));
            wbmpOperateBqmsQueueAvgDto.setAvgAbandonedLv(new BigDecimal(0));
            wbmpOperateBqmsQueueAvgDto.setIndexCnt(new BigDecimal(0));
        }
        return wbmpOperateBqmsQueueAvgDto;
    }

    @Override
    public String getAvgAbondVe(String orgId) {
        String date= DateUtil.today();
        String avgAbandve = wbmpOperateBqmsQueueAvgDao.getOrgAvgAbandVe(orgId,date);
        return avgAbandve;
    }

    /**
     * 客户平均等待时长
     * @param orgId 机构号
     * @param date 时间
     * @return
     */
    @Override
    public float getCustmerWaitTime(String orgId, String date) {
        return wbmpOperateBqmsQueueAvgDao.getCustmerWaitTime(orgId,date);
    }
}
