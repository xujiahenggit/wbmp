package com.bank.icop.dos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "库存信息")
public class VoucherStockDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("交易用户ID")
    private String userId;
    @ApiModelProperty("机构号")
    private String orgId;
    @ApiModelProperty("凭证状态")
    private String voucherStatus;
    @ApiModelProperty("凭证代码")
    private String voucherNo;

}
