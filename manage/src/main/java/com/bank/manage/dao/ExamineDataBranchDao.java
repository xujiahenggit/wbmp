package com.bank.manage.dao;

import com.bank.manage.dos.ExamineDataBranchDO;
import com.bank.manage.vo.ExamineAnalyzeParmVo;
import com.bank.manage.vo.ExamineAnalyzeVo;
import com.bank.manage.vo.ExamineDataRankVo;
import com.bank.manage.vo.ExamineDeduVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamineDataBranchDao extends BaseMapper<ExamineDataBranchDO> {
    void delExamineDataBranchByExcelId(@Param("id") String id);

    IPage<ExamineDataRankVo> queryExamineDataRankFH(Page<ExamineDataRankVo> page, @Param("startYear") String startYear,
         @Param("startQuarter") String startQuarter,@Param("endYear") String endYear,@Param("endQuarter") String endQuarter);

    IPage<ExamineDataRankVo> queryExamineDataRankWD(Page<ExamineDataRankVo> page, @Param("startYear") String startYear,
        @Param("startQuarter") String startQuarter,@Param("endYear") String endYear,@Param("endQuarter") String endQuarter);

    IPage<ExamineDeduVo> queryExamineDataDeduByBranch(Page<ExamineDeduVo> page, @Param("startYear") String startYear,
         @Param("startQuarter") String startQuarter, @Param("endYear") String endYear, @Param("endQuarter") String endQuarter,
         @Param("branchOrgId") String branchOrgId,@Param("outOrgId") String outOrgId);

    List<ExamineAnalyzeVo> queryExamineDataAnalyzeByBranch(@Param("vo") ExamineAnalyzeParmVo vo);
}
