package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 运营看板-运营红灰排行榜VO
 * ClassName: OperateRankVO
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/06/15 16:54:30
 */
@ApiModel(description = "运营红灰排行榜Top5")
@Data
public class OperateRankVO implements Serializable {

    @ApiModelProperty(value = "机构号")
    private String orgId;

    @ApiModelProperty(value = "机构名称")
    private String orgName;

    @ApiModelProperty(value = "分数")
    private float percent;

}
