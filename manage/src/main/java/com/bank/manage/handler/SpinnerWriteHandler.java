package com.bank.manage.handler;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import com.bank.core.enums.ConstantEnum;
import com.bank.manage.dos.DictionaryItemDO;
import com.bank.manage.service.DictionaryService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.util.ArrayList;
import java.util.List;


public class SpinnerWriteHandler implements SheetWriteHandler {

    private DictionaryService dictionaryService;

    public SpinnerWriteHandler(DictionaryService dictionaryService){
        this.dictionaryService = dictionaryService;
    }

    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        String type = ConstantEnum.EXAMPLE_DICT.getType();
        List<DictionaryItemDO> dictInfoByName = dictionaryService.getDictinfoByName(type);
        List<String> dictNameList = new ArrayList<>();
        for (int i = 0; i < dictInfoByName.size(); i++) {
            dictNameList.add(dictInfoByName.get(i).getDictItemTitle());
        }
        String[] arr = dictNameList.toArray(new String[dictNameList.size()]);
        Sheet sheet = writeSheetHolder.getSheet();
        CellRangeAddressList cellRangeAddressList = new CellRangeAddressList(2, 1000, 1, 1);
        DataValidationHelper helper = writeSheetHolder.getSheet().getDataValidationHelper();
        DataValidationConstraint constraint = helper.createExplicitListConstraint(arr);
        DataValidation dataValidation = helper.createValidation(constraint, cellRangeAddressList);
        sheet.addValidationData(dataValidation);

        sheet.setDefaultColumnWidth(20);
        sheet.setDefaultRowHeight((short) 540);
    }
}
