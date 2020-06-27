package com.bank.manage.service;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.vo.ExamineAnalyzeParmVo;
import com.bank.manage.vo.ExamineAnalyzeVo;
import com.bank.manage.vo.ExamineDataRankVo;
import com.bank.manage.vo.ExamineDeduVo;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
import java.util.Map;

public interface ExamineDataBranchService {
    void saveBranchData(Map<String, Object> mapData);

    IPage<ExamineDataRankVo> queryExamineDataRank(PageQueryModel pageQueryModel);

    IPage<ExamineDeduVo> queryExamineDataDeduByBranch(PageQueryModel pageQueryModel);

    List<ExamineAnalyzeVo> queryExamineDataAnalyzeByBranch(ExamineAnalyzeParmVo examineAnalyzeParmVo);
}
