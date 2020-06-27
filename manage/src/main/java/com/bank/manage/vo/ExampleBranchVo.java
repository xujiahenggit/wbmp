package com.bank.manage.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

import java.io.Serializable;

@Data
public class ExampleBranchVo implements Serializable {
    private static final long serialVersionUID = 1548174718293284707L;
    @ExcelProperty("序号")
    private String serialNumber;
    @ExcelProperty("创建类型")
    private String createType;
    @ExcelProperty("创建年份")
    private String createYear;
    @ExcelProperty("网点名称")
    private String orgName;
    @ExcelProperty("网点机构号")
    @ColumnWidth(30)
    private String orgId;
    @ExcelProperty("失效日期('年份'或'长期有效')")
    private String failureYear;
}
