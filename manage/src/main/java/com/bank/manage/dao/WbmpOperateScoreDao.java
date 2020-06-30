package com.bank.manage.dao;

import com.bank.manage.dos.WbmpOperateScoreDO;
import com.bank.manage.vo.OrgScoreVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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


    /**
     *查询最近三年的数据[wbmp_mangement_score]
     */
    List<OrgScoreVo> queryManageByYear(@Param(value = "orgId")String orgId);

    /**
     *查询最近12个月的数据【不包含当月记录】
     */
    List<OrgScoreVo> queryManageByMonth(@Param(value = "orgId")String orgId);

    /**
     *查询最近五个季度的数据【包含当季度的数据】
     */
    List<OrgScoreVo> queryManageByQuart(@Param(value = "orgId")String orgId);

}
