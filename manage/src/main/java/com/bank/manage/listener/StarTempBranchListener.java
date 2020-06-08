package com.bank.manage.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.bank.core.entity.BizException;
import com.bank.core.utils.PropertyUtil;
import com.bank.manage.dos.StarDataAdminDO;
import com.bank.manage.dos.StarDataTempBranchDO;
import com.bank.manage.excel.ImportExcelResponse;
import com.bank.manage.service.StarTempBranchService;
import com.bank.manage.vo.StarTempVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StarTempBranchListener extends AnalysisEventListener<StarTempVo> {

    private Integer excelDataId;
    private String excelDate;
    private ImportExcelResponse response;
    private StarTempBranchService starTempBranchService;

    public StarTempBranchListener(Integer excelDataId, String excelDate, ImportExcelResponse response, StarTempBranchService starTempBranchService){
        this.excelDataId = excelDataId;
        this.excelDate = excelDate;
        this.response = response;
        this.starTempBranchService = starTempBranchService;
    }

    @Override
    public void invoke(StarTempVo data, AnalysisContext context) {
        StarDataTempBranchDO starDataTempBranchDO = new StarDataTempBranchDO();
        starDataTempBranchDO.setExcelId(excelDataId);
        starDataTempBranchDO.setBranchOrgId(data.getBranchOrgId());
        starDataTempBranchDO.setBranchOrgName(data.getBranchOrgName());
        starDataTempBranchDO.setOutOrgId(data.getOutOrgId());
        starDataTempBranchDO.setOutOrgName(data.getOutOrgName());
        starDataTempBranchDO.setAssessStar(data.getAssessStar());
        starDataTempBranchDO.setStartYear(Integer.parseInt(excelDate));

        StarDataAdminDO starDataAdminDO = new StarDataAdminDO();
        PropertyUtil.copyProperties(starDataTempBranchDO,starDataAdminDO);
        try {
            starTempBranchService.saveStarData(starDataAdminDO,starDataTempBranchDO);
        } catch (Exception e) {
            log.error("星级标准化网点数据保存失败！{}",e.getMessage());
            throw new BizException("星级标准化网点数据保存失败！");
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
