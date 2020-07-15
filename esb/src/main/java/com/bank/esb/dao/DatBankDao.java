package com.bank.esb.dao;

import com.bank.esb.dos.DatBankDO;
import com.bank.esb.vo.DatBankVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import org.apache.ibatis.annotations.Param;
/**
 * 银行信息表 Mapper 接口
 *
 * @author 代码自动生成
 * @since 2020-07-10
 */
public interface DatBankDao extends BaseMapper<DatBankDO> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param datBank
	 * @return
	 */
	List<DatBankDO> listPage(IPage page,@Param("model") DatBankDO datBank);

}
