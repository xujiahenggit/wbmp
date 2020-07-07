package com.bank.icop.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class WaitListQueryVo implements Serializable {
    @ApiModelProperty(value = "交易用户ID")
    private String userId;

    @ApiModelProperty(value = "机构号")
    private String orgId;
}
