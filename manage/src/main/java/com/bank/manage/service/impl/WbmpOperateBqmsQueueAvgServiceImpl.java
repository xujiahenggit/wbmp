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
            wbmpOperateBqmsQueueAvgDto.setCunstomerAvg(WbmpOperRateUtils.ComputeCustomerAvg(wbmpOperateBqmsQueueAvgDto.getAbandonedLv(),wbmpOperateBqmsQueueAvgDto.getIndexCnt()));
        }
        return wbmpOperateBqmsQueueAvgDto;
    }
}
