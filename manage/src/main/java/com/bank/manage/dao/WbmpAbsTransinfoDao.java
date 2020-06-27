package com.bank.manage.dao;

import com.bank.manage.dos.WbmpAbsTransinfoDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: Andy
 * @Date: 2020/6/17 10:27
 */
public interface WbmpAbsTransinfoDao extends BaseMapper<WbmpAbsTransinfoDO> {
    /**
     * 查询柜面交易量
     * @param orgId 机构号
     * @param date 日期
     * @return
     */
    int getTranNumByOrgId(@Param(value = "orgId") String orgId,@Param(value = "date") String date);

    /**
     * 查询 当月 柜面交易量
     * @param orgId 机构编号
     * @param date 日期
     * @return
     */
    int getCurrentMonthTrandNum(@Param(value = "orgId") String orgId,@Param(value = "date") String date);
}
