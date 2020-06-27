package com.bank.manage.service;

import com.bank.manage.dos.WbmpOperateCustDO;
import com.bank.manage.dto.CustomerAvgDto;
import com.bank.manage.vo.CustomerAvgVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author: Andy
 * @Date: 2020/6/12 16:55
 */
public interface WbmpOperateCustService extends IService<WbmpOperateCustDO> {
    /**
     * 获取客群增长
     * @param customerAvgVo 查询参数
     * @return
     */
    CustomerAvgDto getCustomerImg(CustomerAvgVo customerAvgVo);
}
