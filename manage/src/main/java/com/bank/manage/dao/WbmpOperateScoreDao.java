package com.bank.manage.dao;

import com.bank.manage.dos.WbmpOperateScoreDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: Andy
 * @Date: 2020/6/19 9:34
 */
public interface WbmpOperateScoreDao extends BaseMapper<WbmpOperateScoreDO> {

    /**
     * 查询经营月度日期最大一天的分数[经营]
     * @param orgId
     * @param mouth
     * @return
     */
    Float findOperateOrgMouthScore(@Param(value = "orgId")String orgId, @Param(value = "mouth")String mouth);



    /**
     * 查询经营年度日期最大一天的分数[经营]
     * @param orgId
     * @param year
     * @return
     */
    Float findOperateYearOrgScore(@Param(value = "orgId")String orgId,@Param(value = "year")String year);

}
