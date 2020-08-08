package com.bank.manage.dao;

import com.bank.manage.dos.WbmpAtmpTranInfoDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: Andy
 * @Date: 2020/6/17 10:28
 */
public interface WbmpAtmpTranInfoDao extends BaseMapper<WbmpAtmpTranInfoDO> {
    /**
     * 查询 交易量
     * @param orgId 机构号
     * @param date 日期
     * @return
     */
    int getTranNumByOrgId(@Param(value = "orgId") String orgId,@Param(value = "date") String date);


    /**
     * 查询 交易量【历史和当前表】
     * @param orgId 机构号
     * @param date 日期
     * @return
     */
    int getAllTranNumByOrgId(@Param(value = "orgId") String orgId,@Param(value = "date") String date);
}
