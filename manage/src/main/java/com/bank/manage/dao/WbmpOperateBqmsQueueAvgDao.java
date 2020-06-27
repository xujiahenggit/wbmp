package com.bank.manage.dao;

import com.bank.manage.dos.WbmpOperateBqmsQueueAvgDO;
import com.bank.manage.dto.WbmpOperateBqmsQueueAvgDto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: Andy
 * @Date: 2020/6/12 16:54
 */
public interface WbmpOperateBqmsQueueAvgDao extends BaseMapper<WbmpOperateBqmsQueueAvgDO> {
    /**
     * 查询客户满意度
     * @param orgId 机构号
     * @param date 查询日期
     * @return
     */
    WbmpOperateBqmsQueueAvgDto getOperraInfo(@Param(value = "orgId") String orgId,@Param(value = "date") String date);

    /**
     *
     * @param orgId
     * @return
     */
    String getOrgAvgAbandVe(@Param(value = "orgId") String orgId,@Param(value = "date") String date);
}
