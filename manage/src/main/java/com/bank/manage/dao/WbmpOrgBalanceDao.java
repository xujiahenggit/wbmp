package com.bank.manage.dao;

import com.bank.manage.dos.WbmpOrgBalanceDO;
import com.bank.manage.vo.WbmpOrgBalanceVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import org.apache.ibatis.annotations.Param;
/**
 * 网点机构存款表 Mapper 接口
 *
 * @author zhaozhongyuan
 * @since 2020-06-22
 */
public interface WbmpOrgBalanceDao extends BaseMapper<WbmpOrgBalanceDO> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param wbmpOrgBalance
	 * @return
	 */
	List<WbmpOrgBalanceDO> listPage(IPage page,@Param("model") WbmpOrgBalanceDO wbmpOrgBalance);

	/**
	 *获取机构某天的对私+对公+一般性存款的总和
	 * @param orgId
	 * @return
	 */
	String getOrgHistoryBal(@Param(value = "orgId") String orgId,@Param(value = "date") String date);


}
