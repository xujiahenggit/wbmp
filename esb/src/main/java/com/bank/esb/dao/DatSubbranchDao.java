package com.bank.esb.dao;

import com.bank.esb.dos.DatSubbranchDO;
import com.bank.esb.vo.DatSubbranchVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import org.apache.ibatis.annotations.Param;
/**
 * 支行信息表 Mapper 接口
 *
 * @author 代码自动生成
 * @since 2020-07-10
 */
public interface DatSubbranchDao extends BaseMapper<DatSubbranchDO> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param datSubbranch
	 * @return
	 */
	List<DatSubbranchDO> listPage(IPage page,@Param("model") DatSubbranchDO datSubbranch);

}
