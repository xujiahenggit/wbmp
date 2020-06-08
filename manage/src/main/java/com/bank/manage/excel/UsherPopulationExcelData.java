package com.bank.manage.excel;

import java.io.Serializable;

import com.alibaba.excel.annotation.ExcelProperty;

import lombok.Data;

/**
 * 引导员人数控制Excel导入数据实体类
 * ClassName: UsherPopulationExcelData
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/04/22 15:44:34
 */
@Data
public class UsherPopulationExcelData implements Serializable {

    /**
    *
    */
    private static final long serialVersionUID = -8038045170415455551L;

    @ExcelProperty("机构编号")
    private String orgId;

    @ExcelProperty("机构名称")
    private String orgName;

    @ExcelProperty("引导员人数控制")
    private Integer populationLimit;

}
