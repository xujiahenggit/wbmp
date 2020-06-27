package com.bank.icop.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Andy
 * @Date: 2020/6/4 16:05
 */
@ApiModel(description = "凭证列表查询用")
@Data
public class VoucherListQueryVo implements Serializable {

    @ApiModelProperty(value = "交易用户ID")
    private String userId;

    @ApiModelProperty(value = "机构号")
    private String orgId;

    @ApiModelProperty(value = "页数")
    private String pageIndex;

    @ApiModelProperty(value = "显示行数")
    private String pageSize;

}
