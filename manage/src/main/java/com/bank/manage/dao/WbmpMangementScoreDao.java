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
     * 运营红榜top5
     * @param now
     * @return
     */
    List<OperateRankVO> findRedTop(@Param(value = "now") String now);

    /**
     * 运营灰榜top5
     * @param now
     * @return
     */
    List<OperateRankVO> findGreyTop(@Param(value = "now") String now);


    /**
     *查询最近三年的数据
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
