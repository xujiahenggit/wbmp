package com.bank.manage.listener;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.bank.manage.dos.TacctInfoDO;
import com.bank.manage.excel.AccountingModelExcel;
import com.bank.manage.excel.ImportExcelResponse;
import com.bank.manage.service.TacctInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang.StringUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class AccountingModelExcelListener extends AnalysisEventListener<AccountingModelExcel> {

    private ImportExcelResponse response;
    private TacctInfoService tacctInfoService;

    public AccountingModelExcelListener(TacctInfoService tacctInfoService, ImportExcelResponse response){
        this.tacctInfoService = tacctInfoService;
        this.response = response;
    }

    @Override
    public void invoke(AccountingModelExcel data, AnalysisContext context) {
        QueryWrapper<TacctInfoDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ACCTNO",data.getAcctno());
        TacctInfoDO tacctInfoDO = tacctInfoService.getOne(queryWrapper);

        Integer rowIndex = context.readRowHolder().getRowIndex();
        if(ObjectUtil.isNull(tacctInfoDO)){
            //为空的情况下 进行记录
            Map<String, Object> errorMap = new HashMap<>();
            errorMap.put("rowIndex", rowIndex);
            errorMap.put("errorMsg", "账户号不存在！请检查第 "+rowIndex+" 条数据");
            this.response.getErrorRows().add(errorMap);
            return;
        }
        if(StringUtils.isBlank(data.getFlagBind()) || StringUtils.isBlank(data.getModel()) ){
            //为空的情况下 进行记录
            Map<String, Object> errorMap = new HashMap<>();
            errorMap.put("rowIndex", rowIndex);
            errorMap.put("errorMsg", "纸质对账单或对账模式值为空！请检查第 "+rowIndex+" 条数据");
            this.response.getErrorRows().add(errorMap);
            return;
        }
        if(data.getFlagBind().equals(tacctInfoDO.getFlagBind()) && data.getModel().equals(tacctInfoDO.getModel())){
            //数据没改动 直接返回
            return;
        }
        //不为空的情况下 调用修改
        TacctInfoDO tacctInfo = TacctInfoDO.builder()
                .acctno(data.getAcctno())
                .flagBind(data.getFlagBind())
                .model(data.getModel())
                //.tellerUpdate()
                .timeUpdate(LocalDateTime.now()).build();
        importData(tacctInfo, rowIndex);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
    }

    private void importData(TacctInfoDO tacctInfoDO, Integer rowIndex) {
        try {
            UpdateWrapper<TacctInfoDO> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("ACCTNO",tacctInfoDO.getAcctno());
            this.tacctInfoService.update(tacctInfoDO,updateWrapper);
        }
        catch (Exception e) {
            this.response.setStatus(false);
            Map<String, Object> errorMap = new HashMap<>();
            errorMap.put("rowIndex", rowIndex);
            errorMap.put("errorMsg", e.getMessage());
            this.response.getErrorRows().add(errorMap);
        }
    }
}
