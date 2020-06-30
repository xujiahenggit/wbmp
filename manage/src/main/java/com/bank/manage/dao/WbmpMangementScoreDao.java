package com.bank.manage.dao;

import com.bank.manage.dos.WbmpMangementScoreDO;
import com.bank.manage.vo.OperateRankVO;
import com.bank.manage.vo.OrgScoreVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/6/19 9:35
 */
public interface WbmpMangementScoreDao extends BaseMapper<WbmpMangementScoreDO> {

    /**
     * 红榜top5
     * @param now
     * @return
     */
    List<OperateRankVO> findRedTop(@Param(value = "now") String now);

    /**
     * 灰榜top5
     * @param now
     * @return
     */
    List<OperateRankVO> findGreyTop(@Param(value = "now") String now);


    /**
     * 查询月度日期最大一天的分数[运营]
     * @param orgId
     * @param mouth
     * @return
     */
    Float findOrgMouthScore(@Param(value = "orgId")String orgId,@Param(value = "mouth")String mouth);



    /**
     * 查询年度日期最大一天的分数[运营]
     * @param orgId
     * @param year
     * @return
     */
    Float findYearOrgScore(@Param(value = "orgId")String orgId,@Param(value = "year")String year);


    /**
     *查询最近三年的数据
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
