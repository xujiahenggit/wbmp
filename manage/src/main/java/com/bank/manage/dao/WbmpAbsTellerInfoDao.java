package com.bank.manage.dao;

import com.bank.manage.dos.WbmpAbsTellerInfoDO;
import com.bank.manage.vo.WbmpAbsTellerInfoVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import org.apache.ibatis.annotations.Param;
/**
 * 柜员信息表 Mapper 接口
 *
 * @author 代码自动生成
 * @since 2020-07-03
 */
public interface WbmpAbsTellerInfoDao extends BaseMapper<WbmpAbsTellerInfoDO> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param wbmpAbsTellerInfo
	 * @return
	 */
	List<WbmpAbsTellerInfoDO> listPage(IPage page,@Param("model") WbmpAbsTellerInfoDO wbmpAbsTellerInfo);

}
