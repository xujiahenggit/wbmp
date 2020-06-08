package com.bank.manage.excel;

import java.io.Serializable;

import com.alibaba.excel.annotation.ExcelProperty;

import lombok.Data;

/**
 * 引导员Excel导入数据实体类
 * ClassName: UsherExcelData
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/04/22 15:44:08
 */
@Data
public class UsherExcelData implements Serializable {

    /**
    *
    */
    private static final long serialVersionUID = -1485996465142751114L;

    @ExcelProperty("引导员姓名")
    private String usherName;

    @ExcelProperty("所属公司名称")
    private String companyName;

    @ExcelProperty("所属机构编号")
    private String orgId;

    @ExcelProperty("所属机构名称")
    private String orgName;

    @ExcelProperty("电话号码")
    private String phoneNo;

    @ExcelProperty("身份证号")
    private String identityNo;

    @ExcelProperty("应出勤天数")
    private Integer workDays;

}
