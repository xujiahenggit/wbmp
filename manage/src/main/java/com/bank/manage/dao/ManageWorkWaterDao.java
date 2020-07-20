package com.bank.manage.dao;

import com.bank.manage.dos.WorkWaterDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 工单流水表 Mapper 接口
 *
 * @author 代码自动生成
 * @since 2020-07-13
 */
public interface ManageWorkWaterDao extends BaseMapper<WorkWaterDO> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param workWater
	 * @return
	 */
	List<WorkWaterDO> listPage(IPage page,@Param("model") WorkWaterDO workWater);

    List<Map<String, String>> getwater(@Param("id") String orderId);
}
