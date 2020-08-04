package com.bank.manage.dao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: cq
 * @Date: 2020/5/8 22:50
 * (t_repair)
 */
@Data
@ApiModel
public class LargerScreenDto {
    @ApiModelProperty(value = "支行号")
    private String branchCode;
    @ApiModelProperty(value = "自助银行")
    private String selfBankCode;
    @ApiModelProperty(value = "终端编号")
    private String terminalCode;
    @ApiModelProperty("第几页：默认第一页")
    private Integer pageIndex = 1;

    @ApiModelProperty("分页大小：默认10行")
    private Integer pageSize = 10;
    @ApiModelProperty("排序字段")
    private String sort;

    @ApiModelProperty("排序方式")
    private String order;
}