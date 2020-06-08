package com.bank.manage.listener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.bank.core.utils.PropertyUtil;
import com.bank.manage.dos.UsherPopulationDO;
import com.bank.manage.excel.ImportExcelResponse;
import com.bank.manage.excel.UsherPopulationExcelData;
import com.bank.manage.service.UsherService;

/**
 * 引导员人数控制Excel数据导入监听类
 * ClassName: UsherPopulationExcelListener
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/04/22 15:46:06
 */
public class UsherPopulationExcelListener extends AnalysisEventListener<UsherPopulationExcelData> {

    private UsherService usherService;

    /**
     * 当前用户
     */
    private String currentUser;

    private ImportExcelResponse response;

    public UsherPopulationExcelListener(UsherService usherService, String currentUser, ImportExcelResponse response) {
        this.usherService = usherService;
        this.currentUser = currentUser;
        this.response = response;
    }

    private static final int BATCH_COUNT = 50;

    List<UsherPopulationDO> usherPopulationDOList = new ArrayList<UsherPopulationDO>();

    @Override
    public void invoke(UsherPopulationExcelData data, AnalysisContext context) {

        UsherPopulationDO usherPopulationDO = new UsherPopulationDO();
        PropertyUtil.copyProperties(data, usherPopulationDO);
        usherPopulationDO.setCreatedBy(this.currentUser);
        usherPopulationDO.setUpdatedBy(this.currentUser);
        usherPopulationDO.setCreatedTime(new Date());
        usherPopulationDO.setUpdatedTime(new Date());

        improtData(usherPopulationDO, context.readRowHolder().getRowIndex());
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        //improtData();
    }

    private void improtData(UsherPopulationDO usherPopulationDO, Integer rowIndex) {
        try {
            this.usherService.insertLimit(usherPopulationDO);
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
