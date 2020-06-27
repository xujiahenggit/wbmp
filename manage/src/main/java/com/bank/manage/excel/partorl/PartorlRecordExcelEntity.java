package com.bank.manage.excel.partorl;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 巡查记录导出excel
 * @Author: Andy
 * @Date: 2020/5/14 10:23
 */
@Data
@ExcelTarget("PartorlRecordExcelEntity")
public class PartorlRecordExcelEntity implements Serializable {

    @ExcelEntity()
    private PartorlModualExcelEntity partorlModualExcelEntity;

    @ExcelCollection(name = "巡查内容检查情况", orderNum = "2")
    private List<PartorlContentExcelEntity> partorlContentExcelEntity;
}
