package com.bank.manage.excel.partorl;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serializable;

/**
 * 巡查内容excel 模型
 *
 * @Author: Andy
 * @Date: 2020/5/14 10:30
 */
@Data
@ExcelTarget("PartorlRecordExcelEntity")
public class PartorlContentExcelEntity implements Serializable {

    /**
     * id
     */
    @Excel(name = "序号" , orderNum = "1" , width = 25)
    private String id;

    @Excel(name = "巡查内容" , orderNum = "1" , width = 125)
    private String partorlContent;

    @Excel(name = "是否正常" , replace = { "是_0", "否_1" }, orderNum = "1" , width = 25)
    private String isNomal;

    @Excel(name = "备注" , orderNum = "1" , width = 35)
    private String remark;
}
