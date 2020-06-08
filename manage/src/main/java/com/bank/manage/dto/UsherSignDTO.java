package com.bank.manage.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 引导员签到-接口实体类
 * ClassName: UsherSignDTO
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/04/23 15:11:22
 */
@Data
@ApiModel(description = "网点引导员签到")
public class UsherSignDTO implements Serializable {

    /**
    *
    */
    private static final long serialVersionUID = 5850822424905403993L;

    @ApiModelProperty(value = "网点引导员签到主键ID")
    private Integer usherSignId;

    @ApiModelProperty(value = "引导员ID")
    private Integer usherId;

    @ApiModelProperty(value = "签到日期 格式为yyyy-MM-dd")
    private String signDate;

    @ApiModelProperty(value = "签到状态")
    private String signStatus;
}
