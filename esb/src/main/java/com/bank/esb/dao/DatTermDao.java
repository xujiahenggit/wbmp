package com.bank.esb.dao;

import com.bank.esb.dos.DatTermDO;
import com.bank.esb.vo.DatTermVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import org.apache.ibatis.annotations.Param;
/**
 * 终端信息表 Mapper 接口
 *
 * @author 代码自动生成
 * @since 2020-07-10
 */
public interface DatTermDao extends BaseMapper<DatTermDO> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param datTerm
	 * @return
	 */
	List<DatTermDO> listPage(IPage page,@Param("model") DatTermDO datTerm);

}
