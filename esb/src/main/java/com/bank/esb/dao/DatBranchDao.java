package com.bank.esb.dao;

import com.bank.esb.dos.DatBranchDO;
import com.bank.esb.vo.DatBranchVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import org.apache.ibatis.annotations.Param;
/**
 * 分行信息表 Mapper 接口
 *
 * @author 代码自动生成
 * @since 2020-07-10
 */
public interface DatBranchDao extends BaseMapper<DatBranchDO> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param datBranch
	 * @return
	 */
	List<DatBranchDO> listPage(IPage page,@Param("model") DatBranchDO datBranch);

}
