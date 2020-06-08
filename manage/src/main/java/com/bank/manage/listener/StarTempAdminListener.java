package com.bank.manage.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.bank.core.entity.BizException;
import com.bank.manage.dos.StarDataAdminDO;
import com.bank.manage.excel.ImportExcelResponse;
import com.bank.manage.service.StarTempAdminService;
import com.bank.manage.vo.StarTempVo;

public class StarTempAdminListener extends AnalysisEventListener<StarTempVo> {

    private Integer excelDataId;
    private String excelDate;
    private ImportExcelResponse response;
    private StarTempAdminService starTempAdminService;


    public StarTempAdminListener(Integer excelDataId,String excelDate,ImportExcelResponse response,StarTempAdminService starTempAdminService){
        this.excelDataId = excelDataId;
        this.excelDate = excelDate;
        this.response = response;
        this.starTempAdminService = starTempAdminService;
    }

    @Override
    public void invoke(StarTempVo data, AnalysisContext context) {
        StarDataAdminDO starDataAdminDO = new StarDataAdminDO();
        starDataAdminDO.setExcelId(excelDataId);
        starDataAdminDO.setBranchOrgId(data.getBranchOrgId());
        starDataAdminDO.setBranchOrgName(data.getBranchOrgName());
        starDataAdminDO.setOutOrgId(data.getOutOrgId());
        starDataAdminDO.setOutOrgName(data.getOutOrgName());
        starDataAdminDO.setAssessStar(data.getAssessStar());
        starDataAdminDO.setStartYear(Integer.parseInt(excelDate));

        try {
            starTempAdminService.saveStarData(starDataAdminDO);
        } catch (Exception e) {
            throw new BizException("星级标准化网点数据保存失败！");
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
