package com.bank.manage.service;

import com.bank.manage.dos.WbmpOperateBqmsQueueAvgDO;
import com.bank.manage.dto.WbmpOperateBqmsQueueAvgDto;
import com.baomidou.mybatisplus.extension.service.IService;

import java.text.DecimalFormat;

/**
 * @Author: Andy
 * @Date: 2020/6/12 16:54
 */
public interface WbmpOperateBqmsQueueAvgService extends IService<WbmpOperateBqmsQueueAvgDO> {
    /**
     * 查询客户满意度
     * @param orgId 机构号
     * @return
     */
    WbmpOperateBqmsQueueAvgDto getOperraInfo(String orgId);

    /**
     * 获取月度平均弃号率
     */
    String getAvgAbondVe(String orgId);
}
