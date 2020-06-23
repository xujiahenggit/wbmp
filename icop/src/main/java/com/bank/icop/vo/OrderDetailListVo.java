package com.bank.icop.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhaozhongyuan
 * @date 2020-06-05 17:14:33
 * @email
 */
@Data
@ApiModel(description = "是否创建订单模型")
public class OrderDetailListVo {

    @ApiModelProperty(value = "交易用户ID")
    private String userId;

    @ApiModelProperty(value = "机构id")
    private String orgId;

    @ApiModelProperty(value = "机构名称")
    private String orgName;

}
