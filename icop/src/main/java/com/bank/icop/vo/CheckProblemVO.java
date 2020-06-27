package com.bank.icop.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 检查问题
 * ClassName: CheckProblemVO
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/06/23 19:06:59
 */
@Data
@ApiModel(description = "登记检查-检查问题VO")
public class CheckProblemVO implements Serializable {

    /**
    *
    */
    private static final long serialVersionUID = -8361160875644196001L;

    @ApiModelProperty(value = "问题Id")
    private String problemId;

    @ApiModelProperty(value = "问题登记时间")
    private LocalDateTime problemDate;

    @ApiModelProperty(value = "问题类型")
    private String problemType;

    @ApiModelProperty(value = "问题整改机构")
    private String problemRectifyOrg;

    @ApiModelProperty(value = "整改责任人列表")
    private List<String> rectifyDutyUserList;

    @ApiModelProperty(value = "问题描述")
    private String problemDes;
}
