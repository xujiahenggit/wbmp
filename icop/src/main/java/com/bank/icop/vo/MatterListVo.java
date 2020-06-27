package com.bank.icop.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Andy
 * @Date: 2020/6/4 15:42
 */
@Data
@ApiModel(description = "事项列表")
public class MatterListVo implements Serializable {

    @ApiModelProperty(value = "事件名称")
    private String eventName;

    @ApiModelProperty(value = "创建时间", notes = "yyyyMMdd HH:mm:ss")
    private String createTime;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "订单状态")
    private String orderType;

}
