package com.bank.manage.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.bank.manage.dos.ExamineDataAdminDO;
import com.bank.manage.dos.ExamineDataTempAdminDO;
import com.bank.manage.excel.ImportExcelResponse;
import com.bank.manage.service.ExamineDataAdminService;
import com.bank.manage.vo.ExamineDataVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 审核数据-管理员入库
 */
@Slf4j
public class ExamineDataAdminListener extends AnalysisEventListener<ExamineDataVo> {

    private Integer excelDataId;
    private String excelDate;
    private String excelQuarter;
    private ImportExcelResponse response;
    private ExamineDataAdminService examineDataAdminService;

    public ExamineDataAdminListener(Integer excelDataId,String excelDate,String excelQuarter,
             ExamineDataAdminService examineDataAdminService,ImportExcelResponse response){
        this.examineDataAdminService = examineDataAdminService;
        this.excelDataId = excelDataId;
        this.excelDate = excelDate;
        this.excelQuarter = excelQuarter;
        this.response = response;
    }

    private Map<String,Object> mapData = new HashMap<String,Object>();//最外层Map

    private String BranchOrgId = null;//分支行机构号
    private String outOrgId = null;//网点机构号
    @Override
    public void invoke(ExamineDataVo data, AnalysisContext context) {
        if(StringUtils.isNotBlank(data.getBranchOrgId())){
            Map<String,Object> mapListData = new HashMap<String,Object>();//里层Map
            if(!data.getBranchOrgId().equals(BranchOrgId)){
                /**分支行组装开始**/

                ExamineDataAdminDO examineDataAdminDO =new ExamineDataAdminDO();
                examineDataAdminDO.setExcelId(this.excelDataId);
                examineDataAdminDO.setExcelDate(this.excelDate);
                examineDataAdminDO.setExcelQuarter(this.excelQuarter);
                examineDataAdminDO.setOrgId(data.getBranchOrgId());
                examineDataAdminDO.setOrgName(data.getBranchOrgName());
                examineDataAdminDO.setExamineScore(Integer.parseInt(data.getExamineScore()));

                mapListData.put("FH_DATA",examineDataAdminDO);
                /**分支行组装结束**/
                if(!data.getOutOrgId().equals(outOrgId)){
                    Map<String,Object> mapOutListData = new HashMap<String,Object>();//网点层Map
                    /**网点组装开始**/

                    ExamineDataAdminDO examineDataAdminDO1 =new ExamineDataAdminDO();
                    examineDataAdminDO1.setExcelId(this.excelDataId);
                    examineDataAdminDO1.setExcelDate(this.excelDate);
                    examineDataAdminDO1.setOrgId(data.getOutOrgId());
                    examineDataAdminDO1.setExcelQuarter(this.excelQuarter);
                    examineDataAdminDO1.setOrgName(data.getOutOrgName());
                    examineDataAdminDO1.setExamineScore(Integer.parseInt(data.getOutExamineScore()));

                    mapOutListData.put("WD_DATA",examineDataAdminDO1);

                    ExamineDataTempAdminDO examineDataTempAdminDO =new ExamineDataTempAdminDO();
                    examineDataTempAdminDO.setOneModule(data.getOneModule());
                    examineDataTempAdminDO.setTwoModule(data.getTwoModule());
                    examineDataTempAdminDO.setIndicatorName(data.getIndicatorName());
                    examineDataTempAdminDO.setDeduction(Integer.parseInt(data.getDeduction()));
                    examineDataTempAdminDO.setDeductionSpec(data.getDeductionSpec());

                    List<ExamineDataTempAdminDO> mapListIn = new ArrayList<ExamineDataTempAdminDO>();
                    mapListIn.add(examineDataTempAdminDO);

                    mapOutListData.put("WDTEMP_DATA",mapListIn);
                    outOrgId = data.getOutOrgId();
                    mapListData.put(data.getOutOrgId(),mapOutListData);
                    /**网点组装结束**/
                 }
                mapData.put(data.getBranchOrgId(),mapListData);
                }
            BranchOrgId = data.getBranchOrgId();
            }else{
                Map<String, Object> branchMap = (Map<String, Object>) mapData.get(BranchOrgId);
                Map<String, Object> outOrgMap = (Map<String, Object>) branchMap.get(outOrgId);
                if(StringUtils.isNotBlank(data.getOutOrgId())){
                    Map<String,Object> mapOutListData = new HashMap<String,Object>();//网点层Map
                    if(!data.getOutOrgId().equals(outOrgId)){

                        ExamineDataAdminDO examineDataAdminDO1 =new ExamineDataAdminDO();
                        examineDataAdminDO1.setExcelId(this.excelDataId);
                        examineDataAdminDO1.setExcelDate(this.excelDate);
                        examineDataAdminDO1.setExcelQuarter(this.excelQuarter);
                        examineDataAdminDO1.setOrgId(data.getOutOrgId());
                        examineDataAdminDO1.setOrgName(data.getOutOrgName());
                        examineDataAdminDO1.setExamineScore(Integer.parseInt(data.getOutExamineScore()));
                        mapOutListData.put("WD_DATA",examineDataAdminDO1);

                        ExamineDataTempAdminDO examineDataTempAdminDO =new ExamineDataTempAdminDO();
                        examineDataTempAdminDO.setOneModule(data.getOneModule());
                        examineDataTempAdminDO.setIndicatorName(data.getIndicatorName());
                        examineDataTempAdminDO.setTwoModule(data.getTwoModule());
                        examineDataTempAdminDO.setDeduction(Integer.parseInt(data.getDeduction()));
                        examineDataTempAdminDO.setDeductionSpec(data.getDeductionSpec());

                        List<ExamineDataTempAdminDO> mapListIn = new ArrayList<ExamineDataTempAdminDO>();
                        mapListIn.add(examineDataTempAdminDO);
                        mapOutListData.put("WDTEMP_DATA",mapListIn);
                        outOrgId = data.getOutOrgId();
                    }
                    branchMap.put(data.getOutOrgId(),mapOutListData);
                }else{
                    ExamineDataTempAdminDO examineDataTempAdminDO =new ExamineDataTempAdminDO();
                    examineDataTempAdminDO.setOneModule(data.getOneModule());
                    examineDataTempAdminDO.setTwoModule(data.getTwoModule());
                    examineDataTempAdminDO.setIndicatorName(data.getIndicatorName());
                    examineDataTempAdminDO.setDeduction(Integer.parseInt(data.getDeduction()));
                    examineDataTempAdminDO.setDeductionSpec(data.getDeductionSpec());
                    List<ExamineDataTempAdminDO> wdtemp_data = (List<ExamineDataTempAdminDO>) outOrgMap.get("WDTEMP_DATA");
                    wdtemp_data.add(examineDataTempAdminDO);
                }
            }
        //System.out.println(JSON.toJSON(mapData));
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        BranchOrgId = null;
        outOrgId = null;
        //System.out.println("excel解析结束！！！");
        //System.out.println(JSON.toJSON(mapData));
        //System.out.println(BranchOrgId);
        //System.out.println(outOrgId);
        saveAdminData(mapData);
    }


    public void saveAdminData(Map<String,Object> mapData){
        examineDataAdminService.saveAdminData(mapData);
    }
}
