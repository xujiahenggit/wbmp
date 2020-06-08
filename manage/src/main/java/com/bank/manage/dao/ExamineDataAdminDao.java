package com.bank.manage.dao;

import com.bank.manage.dos.ExamineDataAdminDO;
import com.bank.manage.vo.ExamineAnalyzeParmVo;
import com.bank.manage.vo.ExamineAnalyzeVo;
import com.bank.manage.vo.ExamineDataRankVo;
import com.bank.manage.vo.ExamineDeduVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamineDataAdminDao extends BaseMapper<ExamineDataAdminDO> {
    void delExamineDataAdminByExcelId(@Param("id") String id);

    IPage<ExamineDataRankVo> queryExamineDataRankByFH(Page<ExamineDataRankVo> page, @Param("startYear") String startYear,
         @Param("startQuarter") String startQuarter, @Param("endYear")String endYear, @Param("endQuarter")String endQuarter);

    IPage<ExamineDataRankVo> queryExamineDataRankByWD(Page<ExamineDataRankVo> page, @Param("startYear") String startYear,
         @Param("startQuarter") String startQuarter, @Param("endYear")String endYear, @Param("endQuarter")String endQuarter);


    IPage<ExamineDeduVo> queryExamineDataDeduByAdmin(Page<ExamineDeduVo> page, @Param("startYear") String startYear,
         @Param("startQuarter") String startQuarter, @Param("endYear") String endYear, @Param("endQuarter") String endQuarter,
         @Param("branchOrgId") String branchOrgId,@Param("outOrgId") String outOrgId);

    List<ExamineAnalyzeVo> queryExamineDataAnalyzeByAdmin(@Param("vo") ExamineAnalyzeParmVo vo);
}
