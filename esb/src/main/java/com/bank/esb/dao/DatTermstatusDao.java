package com.bank.esb.dao;

import com.bank.esb.dos.DatTermstatusDO;
import com.bank.esb.vo.DatTermstatusVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import org.apache.ibatis.annotations.Param;
/**
 * 终端状态表 Mapper 接口
 *
 * @author 代码自动生成
 * @since 2020-07-10
 */
public interface DatTermstatusDao extends BaseMapper<DatTermstatusDO> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param datTermstatus
	 * @return
	 */
	List<DatTermstatusDO> listPage(IPage page,@Param("model") DatTermstatusDO datTermstatus);

}
