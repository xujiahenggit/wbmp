package com.bank.icop.dto;

import java.io.Serializable;

import lombok.Data;

/**
 *
 * ClassName: CheckPonitsSaveDTO
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/07/07 17:34:57
 */
@Data
public class CheckPonitSaveDTO implements Serializable {

    /**
    *
    */
    private static final long serialVersionUID = -2608359063079418125L;

    private String taskPk;

    private String ePk;

    private String checkNumber;

    private String checkStartDate;

    private String checkEndDate;

    private String checkMethod;

    private String checkDes;

    private String checkOther;
}
