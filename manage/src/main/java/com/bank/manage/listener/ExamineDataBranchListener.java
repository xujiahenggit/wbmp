package com.bank.manage.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.bank.manage.dos.ExamineDataBranchDO;
import com.bank.manage.dos.ExamineDataTempBranchDO;
import com.bank.manage.excel.ImportExcelResponse;
import com.bank.manage.service.ExamineDataBranchService;
import com.bank.manage.vo.ExamineDataVo;

public class ExamineDataBranchListener extends AnalysisEventListener<ExamineDataVo> {

    private Integer excelDataId;

    private String excelDate;

    private String excelQuarter;

    private ImportExcelResponse response;

    private ExamineDataBranchService examineDataBranchService;

    public ExamineDataBranchListener(Integer excelDataId, String excelDate, String excelQuarter, ExamineDataBranchService examineDataBranchService, ImportExcelResponse response) {
        this.examineDataBranchService = examineDataBranchService;
        this.excelDataId = excelDataId;
        this.excelDate = excelDate;
        this.excelQuarter = excelQuarter;
        this.response = response;
    }

    private Map<String, Object> mapData = new HashMap<String, Object>();//最外层Map

    private String BranchOrgId = null;//分支行机构号

    private String outOrgId = null;//网点机构号

    @Override
    public void invoke(ExamineDataVo data, AnalysisContext context) {
        if (StringUtils.isNotBlank(data.getBranchOrgId())) {
            Map<String, Object> mapListData = new HashMap<String, Object>();//里层Map
            if (!data.getBranchOrgId().equals(this.BranchOrgId)) {
                /**分支行组装开始**/

                ExamineDataBranchDO examineDataBranchDO = new ExamineDataBranchDO();
                examineDataBranchDO.setExcelId(this.excelDataId);
                examineDataBranchDO.setExcelDate(this.excelDate);
                examineDataBranchDO.setExcelQuarter(this.excelQuarter);
                examineDataBranchDO.setOrgId(data.getBranchOrgId());
                examineDataBranchDO.setOrgName(data.getBranchOrgName());
                examineDataBranchDO.setExamineScore(Integer.parseInt(data.getExamineScore()));

                mapListData.put("FH_DATA", examineDataBranchDO);
                /**分支行组装结束**/
                if (!data.getOutOrgId().equals(this.outOrgId)) {
                    Map<String, Object> mapOutListData = new HashMap<String, Object>();//网点层Map
                    /**网点组装开始**/

                    ExamineDataBranchDO examineDataBranchDO1 = new ExamineDataBranchDO();
                    examineDataBranchDO1.setExcelId(this.excelDataId);
                    examineDataBranchDO1.setExcelDate(this.excelDate);
                    examineDataBranchDO1.setOrgId(data.getOutOrgId());
                    examineDataBranchDO1.setExcelQuarter(this.excelQuarter);
                    examineDataBranchDO1.setOrgName(data.getOutOrgName());
                    examineDataBranchDO1.setExamineScore(Integer.parseInt(data.getOutExamineScore()));

                    mapOutListData.put("WD_DATA", examineDataBranchDO1);

                    ExamineDataTempBranchDO examineDataTempBranchDO = new ExamineDataTempBranchDO();
                    examineDataTempBranchDO.setOneModule(data.getOneModule());
                    examineDataTempBranchDO.setTwoModule(data.getTwoModule());
                    examineDataTempBranchDO.setIndicatorName(data.getIndicatorName());
                    examineDataTempBranchDO.setDeduction(Integer.parseInt(data.getDeduction()));
                    examineDataTempBranchDO.setDeductionSpec(data.getDeductionSpec());

                    List<ExamineDataTempBranchDO> mapListIn = new ArrayList<ExamineDataTempBranchDO>();
                    mapListIn.add(examineDataTempBranchDO);

                    mapOutListData.put("WDTEMP_DATA", mapListIn);
                    this.outOrgId = data.getOutOrgId();
                    mapListData.put(data.getOutOrgId(), mapOutListData);
                    /**网点组装结束**/
                }
                this.mapData.put(data.getBranchOrgId(), mapListData);
            }
            this.BranchOrgId = data.getBranchOrgId();
        }
        else {
            Map<String, Object> branchMap = (Map<String, Object>) this.mapData.get(this.BranchOrgId);
            Map<String, Object> outOrgMap = (Map<String, Object>) branchMap.get(this.outOrgId);
            if (StringUtils.isNotBlank(data.getOutOrgId())) {
                Map<String, Object> mapOutListData = new HashMap<String, Object>();//网点层Map
                if (!data.getOutOrgId().equals(this.outOrgId)) {

                    ExamineDataBranchDO examineDataBranchDO = new ExamineDataBranchDO();
                    examineDataBranchDO.setExcelId(this.excelDataId);
                    examineDataBranchDO.setExcelDate(this.excelDate);
                    examineDataBranchDO.setExcelQuarter(this.excelQuarter);
                    examineDataBranchDO.setOrgName(data.getOutOrgName());
                    examineDataBranchDO.setOrgId(data.getOutOrgId());
                    examineDataBranchDO.setExamineScore(Integer.parseInt(data.getOutExamineScore()));
                    mapOutListData.put("WD_DATA", examineDataBranchDO);

                    ExamineDataTempBranchDO examineDataTempBranchDO = new ExamineDataTempBranchDO();
                    examineDataTempBranchDO.setOneModule(data.getOneModule());
                    examineDataTempBranchDO.setIndicatorName(data.getIndicatorName());
                    examineDataTempBranchDO.setTwoModule(data.getTwoModule());
                    examineDataTempBranchDO.setDeduction(Integer.parseInt(data.getDeduction()));
                    examineDataTempBranchDO.setDeductionSpec(data.getDeductionSpec());

                    List<ExamineDataTempBranchDO> mapListIn = new ArrayList<ExamineDataTempBranchDO>();
                    mapListIn.add(examineDataTempBranchDO);
                    mapOutListData.put("WDTEMP_DATA", mapListIn);
                    this.outOrgId = data.getOutOrgId();
                }
                branchMap.put(data.getOutOrgId(), mapOutListData);
            }
            else {
                ExamineDataTempBranchDO examineDataTempBranchDO = new ExamineDataTempBranchDO();
                examineDataTempBranchDO.setOneModule(data.getOneModule());
                examineDataTempBranchDO.setTwoModule(data.getTwoModule());
                examineDataTempBranchDO.setIndicatorName(data.getIndicatorName());
                examineDataTempBranchDO.setDeduction(Integer.parseInt(data.getDeduction()));
                examineDataTempBranchDO.setDeductionSpec(data.getDeductionSpec());
                List<ExamineDataTempBranchDO> wdtemp_data = (List<ExamineDataTempBranchDO>) outOrgMap.get("WDTEMP_DATA");
                wdtemp_data.add(examineDataTempBranchDO);
            }
        }
        //System.out.println(JSON.toJSON(mapData));
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        this.BranchOrgId = null;
        this.outOrgId = null;
        //System.out.println("excel解析结束！！！");
        //System.out.println(JSON.toJSON(mapData));
        //System.out.println(BranchOrgId);
        //System.out.println(outOrgId);
        saveBranchData(this.mapData);
    }

    public void saveBranchData(Map<String, Object> mapData) {
        this.examineDataBranchService.saveBranchData(mapData);
    }
}
