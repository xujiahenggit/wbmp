package com.bank.icop.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 检查过程
 * ClassName: CheckProcessDTO
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/06/23 19:02:24
 */
@Data
public class CheckProcessDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2056550328884797969L;

    @ApiModelProperty(value = "检查任务项ID")
    private String taskItemId;

    @ApiModelProperty(value = "检查开始时间")
    private String checkStartDate;

    @ApiModelProperty(value = "检查结束时间")
    private String checkEndDate;

    @ApiModelProperty(value = "检查笔数")
    private int checkNumber;

    @ApiModelProperty(value = "检查手段")
    private String checkMethod;

    @ApiModelProperty(value = "检查记录")
    private String checkDes;

    @ApiModelProperty(value = "其他/回头看")
    private String checkOther;
}
