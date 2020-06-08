package com.bank.manage.excel.partorl;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/5/14 16:44
 */
@Data
@ExcelTarget("PartorlModualExcelEntity")
public class PartorlModualExcelEntity implements Serializable {
    /**
     * 序号
     */
    private String id;

    /**
     * 模块名称
     */
    @Excel(name = "巡查模块", orderNum = "1", needMerge = true,width = 25)
    private String partorlModual;
}
