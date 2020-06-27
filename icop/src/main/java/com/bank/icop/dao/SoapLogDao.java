package com.bank.icop.dao;

import com.bank.icop.dos.SoapLogDO;
import com.bank.icop.vo.SoapLogVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import org.apache.ibatis.annotations.Param;
/**
 * SOAP调用第三方接口日志 Mapper 接口
 *
 * @author zhaozhongyuan
 * @since 2020-06-09
 */
public interface SoapLogDao extends BaseMapper<SoapLogDO> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param soapLog
	 * @return
	 */
	List<SoapLogDO> listPage(IPage page,@Param("model") SoapLogDO soapLog);

}
