package com.bank.manage.dao;

import com.bank.manage.dos.WorkWaterDO;
import com.bank.manage.vo.WorkWaterVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import org.apache.ibatis.annotations.Param;
/**
 * 工单流水表 Mapper 接口
 *
 * @author 代码自动生成
 * @since 2020-07-13
 */
public interface WorkWaterDao extends BaseMapper<WorkWaterDO> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param workWater
	 * @return
	 */
	List<WorkWaterDO> listPage(IPage page,@Param("model") WorkWaterDO workWater);

}
