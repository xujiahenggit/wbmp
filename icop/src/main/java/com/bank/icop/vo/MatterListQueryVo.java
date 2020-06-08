package com.bank.icop.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Andy
 * @Date: 2020/6/5 9:21
 */
@Data
@ApiModel(description = "事项列表查询用")
public class MatterListQueryVo implements Serializable {

    @ApiModelProperty(value = "交易用户ID")
    private String userId;

    @ApiModelProperty(value = "查询内容类型")
    private String type;
}
