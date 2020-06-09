package com.bank.manage.dao;

import com.bank.manage.dos.ActivitieSalonDO;
import com.bank.manage.vo.ActivitieSalonVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
/**
 * 活动沙龙  Mapper 接口
 *
 */
public interface ActivitieSalonDao extends BaseMapper<ActivitieSalonDO> {


    IPage<ActivitieSalonDO> queryActivitiesPage(Page<ActivitieSalonDO> page,@Param("activitieName") String activitieName,
		@Param("activitieType") String activitieType, @Param("startTime") String startTime,@Param("endTime") String endTime);
}