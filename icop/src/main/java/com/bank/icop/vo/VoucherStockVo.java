package com.bank.icop.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "凭证库存查询返回模块信息")
public class VoucherStockVo implements Serializable {

    private static final long serialVersionUID = 2716468619651745556L;

    @ApiModelProperty(value = "凭证名称  ")
    private String voucherName;

    @ApiModelProperty(value = "凭证代码  ")
    private String voucherNo;

    @ApiModelProperty(value = "凭证数量  ")
    private String num;

    @ApiModelProperty(value = "凭证状态  ")
    private String voucherStatus;

}
