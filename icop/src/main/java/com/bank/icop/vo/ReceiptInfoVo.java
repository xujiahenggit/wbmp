package com.bank.icop.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 *
 * ClassName: ReceiptInfoVo
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/06/06 16:24:41
 */
@Data
@Builder
@ApiModel(description = "收货信息")
public class ReceiptInfoVo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5699703104045448205L;

    /**
     * 机构ID
     */
    @ApiModelProperty(value = "机构ID", position = 0)
    private String orgId;

    /**
     * 机构名称
     */
    @ApiModelProperty(value = "机构名称", position = 1)
    private String orgName;

    /**
     * 收货人
     */
    @ApiModelProperty(value = "收货人", position = 2)
    private String receiptUser;

    /**
     * 收货地址
     */
    @ApiModelProperty(value = "收货地址", position = 3)
    private String receiptAddress;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话", position = 4)
    private String phone;

    /**
     * 发票类型
     */
    @ApiModelProperty(value = "发票类型", position = 5)
    private String invoiceType;

    /**
     * 发票抬头
     */
    @ApiModelProperty(value = "发票抬头", position = 6)
    private String invoiceTitle;

    /**
     * 税号
     */
    @ApiModelProperty(value = "税号", position = 7)
    private String dutyParagraph;

}
