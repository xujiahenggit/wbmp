package com.bank.esb.dao;

import com.bank.esb.dos.EsbLogDO;
import com.bank.esb.vo.EsbLogVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import org.apache.ibatis.annotations.Param;
/**
 *  Mapper 接口
 *
 * @author 代码自动生成
 * @since 2020-07-22
 */
public interface EsbLogDao extends BaseMapper<EsbLogDO> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param esbLog
	 * @return
	 */
	List<EsbLogDO> listPage(IPage page,@Param("model") EsbLogDO esbLog);

}
