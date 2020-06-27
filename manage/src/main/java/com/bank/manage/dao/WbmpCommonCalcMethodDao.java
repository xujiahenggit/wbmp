package com.bank.manage.dao;

import com.bank.manage.dos.WbmpCommonCalcMethodDO;
import com.bank.manage.vo.WbmpCommonCalcMethodVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import org.apache.ibatis.annotations.Param;
/**
 * 网点视图计算公式参数表 Mapper 接口
 *
 * @author zhaozhongyuan
 * @since 2020-06-18
 */
public interface WbmpCommonCalcMethodDao extends BaseMapper<WbmpCommonCalcMethodDO> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param wbmpCommonCalcMethod
	 * @return
	 */
	List<WbmpCommonCalcMethodDO> listPage(IPage page,@Param("model") WbmpCommonCalcMethodDO wbmpCommonCalcMethod);

}
