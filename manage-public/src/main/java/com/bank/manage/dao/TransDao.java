package com.bank.manage.dao;

import com.bank.manage.dos.TransDO;
import com.bank.manage.dto.HomePageTransHnumDTO;
import com.bank.manage.dto.SsbRankingDTO;
import com.bank.manage.dto.SsbTransRankBarDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 交易表
 *
 * @author
 * @date 2020-7-7
 */
public interface TransDao extends BaseMapper<TransDO> {
    List<TransDO> queryList(IPage page, @Param("model") TransDO transDO);

    /**
     * 首页月度业务量统计
     * @param deviceClass
     * @return
     */
    List<SsbTransRankBarDTO> listSsbTransRankBar(@Param("deviceClass") String deviceClass,@Param("branchnum") String branchnum);

    /**
     * 首页当月网点业务量排行榜
     * @param deviceClass
     * @return
     */
    List<SsbRankingDTO> listSsbRanking(@Param("deviceClass") String deviceClass,@Param("branchnum") String branchnum);

    /**
     * 首页今日交易总量
     * @return
     */
    List<HomePageTransHnumDTO> selectTransGross(@Param("branchnum") String branchnum);
}
