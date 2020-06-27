package com.bank.manage.service;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.vo.ExamineAnalyzeParmVo;
import com.bank.manage.vo.ExamineAnalyzeVo;
import com.bank.manage.vo.ExamineDataRankVo;
import com.bank.manage.vo.ExamineDeduVo;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
import java.util.Map;

public interface ExamineDataAdminService {
    /**
     * 保存考核原始数据
     * @param mapData
     */
    void saveAdminData(Map<String, Object> mapData);

    /**
     * 删除考核原始数据
     * @param id
     * @param dataType
     */
    void delExcelData(String id, String dataType);

    /**
     * 分页查询季度考核排名表
     * @param pageQueryModel
     * @return
     */
    IPage<ExamineDataRankVo> queryExamineDataRankByAdmin(PageQueryModel pageQueryModel);

    /**
     * 查询季度考核扣分明细表
     * @param pageQueryModel
     * @return
     */
    IPage<ExamineDeduVo> queryExamineDataDeduByAdmin(PageQueryModel pageQueryModel);

    /**
     * 季度考核分析图
     * @param examineAnalyzeParmVo
     * @return
     */
    List<ExamineAnalyzeVo> queryExamineDataAnalyzeByAdmin(ExamineAnalyzeParmVo examineAnalyzeParmVo);
}
