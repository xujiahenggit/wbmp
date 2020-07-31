package com.bank.esb.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: cq
 * @Date: 2020/7/2
 */
@ApiModel(description = "工单处理Vo")
@Data
public class OrderDealWithVo {

    @ApiModelProperty(value = "工单号")
    private String orderNo;

    @ApiModelProperty(value = "处理人id")
    private String engineerId;

    @ApiModelProperty(value = "工单状态")
    private String orderStatus;


    @ApiModelProperty(value = "处理方式")
    private String processMode;

    @ApiModelProperty(value = "服务描述")
    private String serviceDescribe;

    @ApiModelProperty(value = "图片路径集合")
    private List<Attachment>  list;


}
