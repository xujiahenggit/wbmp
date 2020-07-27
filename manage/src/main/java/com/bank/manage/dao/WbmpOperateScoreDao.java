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
     *查询最近三年的数据[wbmp_mangement_score]
     */
    List<OrgScoreVo> queryByYear(@Param(value = "orgId")String orgId);

    /**
     *查询最近12个月的数据【不包含当月记录】
     */
    List<OrgScoreVo> queryByMonth(@Param(value = "orgId")String orgId);

    /**
     *查询最近五个季度的数据【包含当季度的数据】
     */
    List<OrgScoreVo> queryByQuart(@Param(value = "orgId")String orgId);

}
