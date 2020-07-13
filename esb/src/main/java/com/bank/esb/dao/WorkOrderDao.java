package com.bank.esb.dao;

import com.bank.esb.dos.WorkOrderDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import org.apache.ibatis.annotations.Param;
/**
 * 工单表 Mapper 接口
 *
 * @author 代码自动生成
 * @since 2020-07-13
 */
public interface WorkOrderDao extends BaseMapper<WorkOrderDO> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param workOrder
	 * @return
	 */
	List<WorkOrderDO> listPage(IPage page,@Param("model") WorkOrderDO workOrder);

}
