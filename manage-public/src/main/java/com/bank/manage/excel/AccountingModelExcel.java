package com.bank.manage.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 对账模式管理 excel实体
 */
@Data
public class AccountingModelExcel implements Serializable {

    @ExcelProperty("账户")
    private String acctno;

    @ExcelProperty("是否纸质对账单绑定:1是0否")
    private String flagBind;

    @ExcelProperty("对账模式:1集中对账0自行对账")
    private String model;
}
