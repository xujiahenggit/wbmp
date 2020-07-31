package com.bank.manage.dao;

import com.bank.manage.dos.WbmpOperateCustDO;
import com.bank.manage.vo.WbmpOperateCustVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/6/12 16:53
 */
public interface WbmpOperateCustDao extends BaseMapper<WbmpOperateCustDO> {
    /**
     * 按日统计
     * @param orgId  机构号
     * @param day 查询日期
     * @param customerType 客户类型
     */
    Integer getCustomerImgByDay(@Param(value = "orgId") String orgId,@Param(value = "day") String day,@Param(value = "customerType") String customerType);

    /**
     * 按年统计
     * @param orgId 机构号
     * @param month 月份
     * @param customerTypeCode 客户类型
     * @return
     */
    Integer getCustomerImgByMonth(@Param(value = "orgId") String orgId,@Param(value = "month") String month,@Param(value = "customerType") String customerTypeCode);

    /**
     * 按年统计
     * @param orgId 机构编号
     * @param year 年份
     * @param customerTypeCode 客户类型
     * @return
     */
    Integer getCustomerImgByYear(@Param(value = "orgId") String orgId,@Param(value = "year") String year,@Param(value = "customerType") String customerTypeCode);


    /**
     *获取机构和客户类型查询客户量
     */
    Float getOrgCustTypeNum(@Param(value = "orgId") String orgId,@Param(value = "customerTypeCode") String customerTypeCode,@Param(value = "date") String date);


    /**
     * 获取当前日期前15天的客群指标表
     * @param orgId
     * @param customerTypeCode
     * @return
     */
    List<WbmpOperateCustVo> findDaysCust(@Param(value = "orgId") String orgId,@Param(value = "customerTypeCode") String customerTypeCode);

    /**
     * 获取最近12个月的客群指标数据
     * @param orgId
     * @param customerTypeCode
     * @return
     */
    List<WbmpOperateCustVo> findMouthCust(@Param(value = "orgId") String orgId,@Param(value = "customerTypeCode") String customerTypeCode);

    /**
     * 获取最近3年的客群指标数据
     * @param orgId
     * @param customerTypeCode
     * @return
     */
    List<WbmpOperateCustVo> findYearCust(@Param(value = "orgId") String orgId,@Param(value = "customerTypeCode") String customerTypeCode);


    /**
     * 按日统计 所有客户数
     * @param orgId 机构编号
     * @return
     */
    int findDaysCustAll(@Param(value = "orgId") String orgId,@Param(value = "date") String date);

    /**
     * 按月统计 所有客户数
     * @param orgId 机构编号
     * @param date 日期
     * @return
     */
    int findMouthsCustAll(@Param(value = "orgId") String orgId,@Param(value = "date") String date);

    /**
     * 按年统计 所有客户数
     * @param orgId 机构编号
     * @param date 日期
     * @return
     */
    int findYearCustAll(@Param(value = "orgId") String orgId,@Param(value = "date") String date);
}
