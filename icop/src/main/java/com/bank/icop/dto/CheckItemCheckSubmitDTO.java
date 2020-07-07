package com.bank.icop.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * ClassName: CheckItemCheckSubmitDTO
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/07/07 15:42:23
 */
@Data
public class CheckItemCheckSubmitDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7602658586121659759L;

    @ApiModelProperty(value = "任务PK")
    private String taskpk;

    @ApiModelProperty(value = "反馈结果 反馈 0不同意 1同意")
    private String feedback;

    @ApiModelProperty(value = "反馈期限")
    private String feedbackdt;

    @ApiModelProperty(value = "反馈备注意见")
    private String feedbackdes;

    @ApiModelProperty(value = "登记检查qk")
    private String epk;

}
