package com.bank.manage.dao;

import com.bank.manage.dos.WbmpAbsOnlineTimeDO;
import com.bank.manage.vo.WbmpAbsOnlineTimeVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import org.apache.ibatis.annotations.Param;
/**
 * 柜员在线时长明细表 Mapper 接口
 *
 * @author 代码自动生成
 * @since 2020-07-03
 */
public interface WbmpAbsOnlineTimeDao extends BaseMapper<WbmpAbsOnlineTimeDO> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param wbmpAbsOnlineTime
	 * @return
	 */
	List<WbmpAbsOnlineTimeDO> listPage(IPage page,@Param("model") WbmpAbsOnlineTimeDO wbmpAbsOnlineTime);

}
