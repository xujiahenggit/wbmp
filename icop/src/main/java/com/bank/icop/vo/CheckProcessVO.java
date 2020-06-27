package com.bank.icop.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 检查过程
 * ClassName: CheckProcessVO
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
@ApiModel(description = "登记检查-检查过程VO")
public class CheckProcessVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7177102488636731756L;

    @ApiModelProperty(value = "检查开始时间")
    private LocalDateTime checkStartDate;

    @ApiModelProperty(value = "检查结束时间")
    private LocalDateTime checkEndDate;

    @ApiModelProperty(value = "检查笔数")
    private int checkNumber;

    @ApiModelProperty(value = "检查手段")
    private String checkMethod;

    @ApiModelProperty(value = "检查记录")
    private String checkDes;

    @ApiModelProperty(value = "其他/回头看")
    private String checkOther;
}
