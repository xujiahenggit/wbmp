package com.bank.icop.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 *
 * ClassName: CheckTaskSaveDTO
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/07/07 17:25:43
 */
@Data
public class CheckTaskSaveDTO implements Serializable {

    /**
    *
    */
    private static final long serialVersionUID = 5395738676165401215L;

    private CheckPonitsSaveDTO checkpoints;

    private List<CheckProblemSaveDTO> list;
}
