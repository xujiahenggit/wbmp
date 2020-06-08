package com.bank.manage.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.bank.core.utils.PropertyUtil;
import com.bank.manage.dos.UsherDO;
import com.bank.manage.dto.UsherDTO;
import com.bank.manage.excel.ImportExcelResponse;
import com.bank.manage.excel.UsherExcelData;
import com.bank.manage.service.UsherService;

/**
 * 引导员Excel数据导入监听类
 * ClassName: UsherExcelListener
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/04/22 15:45:03
 */
public class UsherExcelListener extends AnalysisEventListener<UsherExcelData> {

    private UsherService usherService;

    /**
     * 当前用户
     */
    private String currentUser;

    private ImportExcelResponse response;

    public UsherExcelListener(UsherService usherService, String currentUser, ImportExcelResponse response) {
        this.usherService = usherService;
        this.currentUser = currentUser;
        this.response = response;
    }

    private static final int BATCH_COUNT = 50;

    List<UsherDO> usherDOList = new ArrayList<UsherDO>();

    @Override
    public void invoke(UsherExcelData data, AnalysisContext context) {
        UsherDTO usherDTO = new UsherDTO();
        PropertyUtil.copyProperties(data, usherDTO);
        improtData(usherDTO, context.readRowHolder().getRowIndex());
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    private void improtData(UsherDTO usherDTO, Integer rowIndex) {
        try {
            this.usherService.insert(usherDTO, this.currentUser);
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
