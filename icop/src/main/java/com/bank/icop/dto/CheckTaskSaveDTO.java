package com.bank.icop.dto;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(value = "检查过程数据项")
    private CheckPonitSaveDTO checkpoints;

    @ApiModelProperty(value = "检查问题数据项列表")
    private List<CheckProblemSaveDTO> list;
}
