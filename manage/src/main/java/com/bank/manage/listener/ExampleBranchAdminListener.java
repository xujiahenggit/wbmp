package com.bank.manage.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.bank.core.entity.BizException;
import com.bank.core.enums.ConstantEnum;
import com.bank.manage.dos.ExampleAdminDO;
import com.bank.manage.excel.ImportExcelResponse;
import com.bank.manage.service.ExampleBranchAdminService;
import com.bank.manage.vo.ExampleBranchVo;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ExampleBranchAdminListener extends AnalysisEventListener<ExampleBranchVo> {

    private Integer excelDataId;
    private String excelDate;
    private ImportExcelResponse response;
    private ExampleBranchAdminService exampleBranchAdminService;


    public ExampleBranchAdminListener(Integer excelDataId,String excelDate,ImportExcelResponse response,ExampleBranchAdminService exampleBranchAdminService){
        this.excelDataId = excelDataId;
        this.excelDate = excelDate;
        this.response = response;
        this.exampleBranchAdminService = exampleBranchAdminService;
    }

    @Override
    public void invoke(ExampleBranchVo data, AnalysisContext context) {
        ExampleAdminDO exampleAdminDO = new ExampleAdminDO();
        exampleAdminDO.setExcelId(excelDataId);
        exampleAdminDO.setCreateType(data.getCreateType());
        exampleAdminDO.setOrgId(data.getOrgId());
        exampleAdminDO.setOrgName(data.getOrgName());
        exampleAdminDO.setCreateType(data.getCreateType());
        String createYear = data.getCreateYear().trim().substring(0, 4);
        exampleAdminDO.setCreateYear(Integer.parseInt(createYear));
        exampleAdminDO.setStartYear(Integer.parseInt(excelDate));
        if(ConstantEnum.EXAMPLE_FAIUL.getDesc().equals(data.getFailureYear())){
            exampleAdminDO.setFailureYear(Integer.parseInt("9999"));
        }else{
            String faliureYear = data.getFailureYear().trim().substring(0, 4);
            exampleAdminDO.setFailureYear(Integer.parseInt(faliureYear));
        }
        try {
            exampleBranchAdminService.saveExampleAdmin(exampleAdminDO);
        } catch (Exception e) {
            log.error("全国标杆网点统计数据保存失败！{}",e.getMessage());
            throw new BizException("全国标杆网点统计数据Excel文件数据导入失败！");
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
