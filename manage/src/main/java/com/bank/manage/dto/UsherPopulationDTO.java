package com.bank.manage.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * ClassName: UsherPopulationDTO
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/04/26 15:58:49
 */
@Data
public class UsherPopulationDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6674924595200205321L;

    @ApiModelProperty(value = "网点引导员人数控制主键")
    private Integer usherPopulationId;

    @ApiModelProperty(value = "机构编号", required = true)
    private String orgId;

    @ApiModelProperty(value = "机构名称", required = true)
    private String orgName;

    @ApiModelProperty(value = "人数控制", required = true)
    private Integer populationLimit;

}
