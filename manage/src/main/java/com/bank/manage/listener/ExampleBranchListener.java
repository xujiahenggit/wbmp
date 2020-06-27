package com.bank.manage.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.bank.core.entity.BizException;
import com.bank.core.enums.ConstantEnum;
import com.bank.core.utils.PropertyUtil;
import com.bank.manage.dos.ExampleAdminDO;
import com.bank.manage.dos.ExampleBranchDO;
import com.bank.manage.excel.ImportExcelResponse;
import com.bank.manage.service.ExampleBranchService;
import com.bank.manage.vo.ExampleBranchVo;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
@Slf4j
public class ExampleBranchListener extends AnalysisEventListener<ExampleBranchVo> {

    private Integer excelDataId;
    private String excelDate;
    private ImportExcelResponse response;
    private ExampleBranchService exampleBranchService;

    public ExampleBranchListener(Integer excelDataId,String excelDate,ImportExcelResponse response,ExampleBranchService exampleBranchService){
        this.excelDataId = excelDataId;
        this.excelDate = excelDate;
        this.response = response;
        this.exampleBranchService = exampleBranchService;
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
        ExampleBranchDO exampleBranchDO = new ExampleBranchDO();
        PropertyUtil.copyProperties(exampleAdminDO,exampleBranchDO);
        try {
            exampleBranchService.saveExampleBranch(exampleAdminDO,exampleBranchDO);
        } catch (Exception e) {
            log.error("全国标杆网点统计数据！{}",e.getMessage());
            throw new BizException("全国标杆网点统计数据保存失败！");
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
