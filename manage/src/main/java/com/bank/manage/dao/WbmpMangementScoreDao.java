package com.bank.manage.dao;

import com.bank.manage.dos.WbmpMangementScoreDO;
import com.bank.manage.vo.OperateRankVO;
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

}
