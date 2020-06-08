package com.bank.manage.excel.partorl;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/14 18:00
 */
@Data
public class PartorlExcelEntity implements Serializable {
    /**
     * 巡查记录ID
     */
    private Integer recordId;

    /**
     * 分支行名称
     */
    private String subBranchName;
    /**
     * 网点名称
     */
    private String outlets;

    /**
     * 填报日期
     */
    private LocalDate partorlDate;

    /**
     * 巡查记录内容
     */
    private List<PartorlRecordExcelEntity> listRecords;
}
