package com.bank.manage.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class StarTempVo implements Serializable {
    private static final long serialVersionUID = -3876431520426925093L;

    @ExcelProperty("序号")
    private String serialNumber;
    @ExcelProperty("分支行名称")
    private String branchOrgName;
    @ExcelProperty("分支行机构号")
    private String branchOrgId;
    @ExcelProperty("网点名称")
    private String outOrgName;
    @ExcelProperty("网点机构号")
    private String outOrgId;
    @ExcelProperty("评定星级")
    private String assessStar;

}
