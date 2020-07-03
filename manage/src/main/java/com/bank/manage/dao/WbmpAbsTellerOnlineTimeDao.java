package com.bank.manage.dao;

import com.bank.manage.dos.WbmpAbsTellerOnlineTimeDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 柜员在线时长信息表 Mapper 接口
 *
 * @author 代码自动生成
 * @since 2020-07-03
 */
public interface WbmpAbsTellerOnlineTimeDao extends BaseMapper<WbmpAbsTellerOnlineTimeDO> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param wbmpAbsTellerOnlineTime
	 * @return
	 */
	List<WbmpAbsTellerOnlineTimeDO> listPage(IPage page,@Param("model") WbmpAbsTellerOnlineTimeDO wbmpAbsTellerOnlineTime);

    void clearAll();
}
