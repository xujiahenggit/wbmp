package com.bank.manage.dao;

import com.bank.manage.dos.VersionsDO;
import com.bank.manage.vo.VersionsVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import org.apache.ibatis.annotations.Param;
/**
 * 应用版本维护 Mapper 接口
 *
 * @author zhaozhongyuan
 * @since 2020-06-01
 */
public interface VersionsDao extends BaseMapper<VersionsDO> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param versions
	 * @return
	 */
	List<VersionsDO> listPage(IPage page,@Param("model") VersionsDO versions);

}
