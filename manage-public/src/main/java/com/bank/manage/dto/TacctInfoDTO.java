package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 帐号及帐号参数
 *
 * @author
 * @date 2020-7-8
 */
@Data
@ApiModel(description = "帐号及帐号参数")
public class TacctInfoDTO implements Serializable {

    private static final long serialVersionUID = 6197505266382129003L;

    @ApiModelProperty(value = "内部编号主键自增ID")
    private Integer id;

    @ApiModelProperty(value = "账户")
    private String acctno;

    @ApiModelProperty(value = "账户名")
    private String acctna;

    @ApiModelProperty(value = "客户号（10位）")
    private String custno;

    @ApiModelProperty(value = "机构号")
    private String brchno;

    @ApiModelProperty(value = "开户日期")
    private LocalDateTime opendt;

    @ApiModelProperty(value = "重点账户标志；1是0否")
    private String flagKeyacct;

    @ApiModelProperty(value = "不对账账户标志；1是0否")
    private String flagNotaccounting;

    @ApiModelProperty(value = "二代支付标志；1是0否")
    private String flagPaymengt;

    @ApiModelProperty(value = "对账机构")
    private String branchAccounting;

    @ApiModelProperty(value = "是否纸质对账单绑定")
    private String flagBind;

    @ApiModelProperty(value = "更新柜员")
    private String tellerUpdate;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime timeUpdate;

    @ApiModelProperty(value = "账户状态")
    private String acctst;

    @ApiModelProperty(value = "对账模式：1集中对账0是自行对账")
    private String model;
}
