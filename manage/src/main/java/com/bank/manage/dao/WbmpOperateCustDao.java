package com.bank.manage.dao;

import com.bank.manage.dos.WbmpOperateCustDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

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
    String getOrgCustTypeNum(@Param(value = "orgId") String orgId,@Param(value = "customerTypeCode") String customerTypeCode,@Param(value = "date") String date);
}
