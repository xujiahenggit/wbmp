package com.bank.esb.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: cq
 * @Date: 2020/7/2
 */
@ApiModel(description = "工单提交Vo")
@Data
public class OrderSubmissionVo {
    @ApiModelProperty(value = "工程师ID")
    private String engineerId;

    @ApiModelProperty(value = "工单编号")
    private String orderNo;

    @ApiModelProperty(value = "工单类型")
    private String orderType;

    @ApiModelProperty(value = "工单描述")
    private String orderDescribe;

    @ApiModelProperty(value = "图片路径集合")
    private List<String> pictureUrl;


}
