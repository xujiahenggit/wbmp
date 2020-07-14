package com.bank.esb.dao;

import com.bank.esb.dos.DatSelfsvcbankDO;
import com.bank.esb.vo.DatSelfsvcbankVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import org.apache.ibatis.annotations.Param;
/**
 * 自助银行信息表 Mapper 接口
 *
 * @author 代码自动生成
 * @since 2020-07-10
 */
public interface DatSelfsvcbankDao extends BaseMapper<DatSelfsvcbankDO> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param datSelfsvcbank
	 * @return
	 */
	List<DatSelfsvcbankDO> listPage(IPage page,@Param("model") DatSelfsvcbankDO datSelfsvcbank);

}
