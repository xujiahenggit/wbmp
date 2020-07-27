package com.bank.icop.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(value = "问题主键pk")
    private String pk;

    @ApiModelProperty(value = "问题整改机构")
    private String qorgan;

    @ApiModelProperty(value = "整改责任人编号，多个用半角逗号隔开")
    private String quser;

    private String sunPointKey;

    private String childPointKey;

    @ApiModelProperty(value = "问题登记时间")
    private String qdate;

    @ApiModelProperty(value = "问题类型")
    private String qtype;

    @ApiModelProperty(value = "问题描述")
    private String qdes;

    @ApiModelProperty(value = "整改责任人名称，多个用半角逗号隔开")
    private String quserName;
}
