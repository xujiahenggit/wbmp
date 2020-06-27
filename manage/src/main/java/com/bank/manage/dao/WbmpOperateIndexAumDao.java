package com.bank.manage.dao;

import com.bank.manage.dos.WbmpOperateIndexAumDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 网点AUM表 Mapper 接口
 *
 * @author zhaozhongyuan
 * @since 2020-06-18
 */
public interface WbmpOperateIndexAumDao extends BaseMapper<WbmpOperateIndexAumDO> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param wbmpOperateIndexAum
	 * @return
	 */
	List<WbmpOperateIndexAumDO> listPage(IPage page, @Param("model") WbmpOperateIndexAumDO wbmpOperateIndexAum);


	/**
	 *获取机构某天的对私+对公+一般性存款的总和
	 * @param orgId
	 * @return
	 */
	String getOrgDaysAum(@Param(value = "orgId") String orgId,@Param(value = "date") String date);

}
