package com.bank.manage.service;

import com.bank.core.entity.PageQueryModel;
import com.bank.core.entity.TokenUserInfo;
import com.bank.manage.dto.ExcelDataDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;


public interface ExcelDataService {
    /**
     * 季度考核数据导入
     * @param excelDataDTO
     * @param tokenUserInfo
     * @return
     */
    Boolean importExamineTempData(ExcelDataDTO excelDataDTO,TokenUserInfo tokenUserInfo);

    /**
     * 全国标杆网点统计数据导入
     * @param excelDataDTO
     * @param tokenUserInfo
     * @return
     */
    Boolean importExampleBranch(ExcelDataDTO excelDataDTO, TokenUserInfo tokenUserInfo);

    /**
     * 星级标准化网点数据导入
     * @param excelDataDTO
     * @param tokenUserInfo
     * @return
     */
    Boolean importStarData(ExcelDataDTO excelDataDTO,TokenUserInfo tokenUserInfo);

    /**
     * 报表列表查询
     * @param pageQueryModel
     * @return
     */
    IPage<ExcelDataDTO> queryExcelData(PageQueryModel pageQueryModel);

    /**
     * 删除报表列表
     * @param id
     * @param dataType
     * @return
     */
    Boolean delExcelData(String id, String dataType);
}
