package com.bank.icop.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 检查项
 * ClassName: CheckItemVO
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/06/23 19:07:19
 */
@Data
@ApiModel(description = "登记检查-检查子项VO")
public class CheckItemVO implements Serializable {

    /**
    *
    */
    private static final long serialVersionUID = -2297590590825558869L;

    @ApiModelProperty(value = "检查子项")
    private String childPointName;

    @ApiModelProperty(value = "检查依据")
    private String abasis;

    @ApiModelProperty(value = "检查方法")
    private String checkMethod;

    @ApiModelProperty(value = "检查Key")
    private String sunPonitKey;

    @ApiModelProperty(value = "检查要点")
    private String sunPonitValue;

}
