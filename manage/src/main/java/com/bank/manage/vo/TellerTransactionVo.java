package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel(description = "运营柜员--交易分析数据返回模型")
public class TellerTransactionVo implements Serializable {
    private static final long serialVersionUID = 8875409266806920824L;

    @ApiModelProperty("交易名称")
    private String tranName;
    @ApiModelProperty("交易量")
    private BigDecimal tradeVolume;
    @ApiModelProperty("柜员交易总数")
    private BigDecimal count;



}
