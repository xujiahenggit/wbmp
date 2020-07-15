package com.bank.esb.dao;

import com.bank.esb.dos.DatWorkOrderDO;
import com.bank.esb.dto.OrderDto;
import com.bank.esb.vo.OrderNumVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 故障工单表 Mapper 接口
 *
 * @author 代码自动生成
 * @since 2020-07-10
 */
public interface DatWorkOrderDao extends BaseMapper<DatWorkOrderDO> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param datWorkOrder
	 * @return
	 */
	List<DatWorkOrderDO> listPage(IPage page,@Param("model") DatWorkOrderDO datWorkOrder);

    List<OrderDto> queryOrders(@Param("m") OrderNumVo orderNumVo);

	Map<String, Object> getDeviceInfo(String toString);
}
