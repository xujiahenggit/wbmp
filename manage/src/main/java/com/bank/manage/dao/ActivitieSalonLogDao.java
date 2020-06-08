package com.bank.manage.dao;

import com.bank.manage.dos.ActivitieSalonLogDO;
import com.bank.manage.vo.ActivitieSalonLogVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import org.apache.ibatis.annotations.Param;
/**
 * 活动沙龙流水  Mapper 接口
 *
 * @author zhaozhongyuan
 * @since 2020-06-05
 */
public interface ActivitieSalonLogDao extends BaseMapper<ActivitieSalonLogDO> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param activitieSalonLog
	 * @return
	 */
	List<ActivitieSalonLogDO> listPage(IPage page,@Param("model") ActivitieSalonLogDO activitieSalonLog);

}
