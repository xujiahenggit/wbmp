package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/6/16 11:13
 */
@Data
@ApiModel(value = "客群增长 查询用")
public class CustomerAvgVo implements Serializable {

    @ApiModelProperty(value = "机构编号")
    private String orgId;

    @ApiModelProperty(value = "客群类别  CUST_001:普通客户数 CUST_002:金卡客户数 CUST_003:白金客户数 CUST_004:钻石客户数")
    private String customerTypeCode;

    @ApiModelProperty(value = "周期类型 00:日 01:月 02:年")
    private String cycleCode;
}
