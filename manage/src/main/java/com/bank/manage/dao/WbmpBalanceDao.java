package com.bank.manage.dao;

import com.bank.manage.dos.WbmpBalanceDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 存款统计表 Mapper 接口
 *
 * @author zhaozhongyuan
 * @since 2020-06-16
 */
public interface WbmpBalanceDao extends BaseMapper<WbmpBalanceDO> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param wbmpBalance
	 * @return
	 */
	List<WbmpBalanceDO> listPage(IPage page,@Param("model") WbmpBalanceDO wbmpBalance);

	/**
	 * 获取机构平均弃号率
	 * @param orgId
	 * @param date
	 * @return
	 */
	String getOrgAvgAbandVe(@Param(value = "orgId") String orgId,@Param(value = "date") String date);

}
