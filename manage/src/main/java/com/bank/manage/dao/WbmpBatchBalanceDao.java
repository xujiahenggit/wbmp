package com.bank.manage.dao;

import com.bank.manage.dos.WbmpBatchBalanceDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 离线存款统计表 Mapper 接口
 *
 * @author zhaozhongyuan
 * @since 2020-06-16
 */
public interface WbmpBatchBalanceDao extends BaseMapper<WbmpBatchBalanceDO> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param wbmpBatchBalance
	 * @return
	 */
	List<WbmpBatchBalanceDO> listPage(IPage page,@Param("model") WbmpBatchBalanceDO wbmpBatchBalance);


	/**
	 *获取机构某天的对私+对公+一般性存款的总和
	 * @param orgId
	 * @return
	 */
	String getOrgLastDayBal(@Param(value = "orgId") String orgId,@Param(value = "date") String date);

}
