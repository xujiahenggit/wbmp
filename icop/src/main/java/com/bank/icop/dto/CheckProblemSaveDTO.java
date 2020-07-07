package com.bank.icop.dto;

import java.io.Serializable;

import lombok.Data;

/**
 *
 * ClassName: CheckProblemSaveDTO
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/07/07 17:37:06
 */
@Data
public class CheckProblemSaveDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1427978660053792437L;

    private String pk;

    private String qorgan;

    private String quser;

    private String sunPointKey;

    private String childPointKey;

    private String qdate;

    private String qtype;

    private String qdes;

    private String quserName;
}
