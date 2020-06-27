package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/6/3 9:25
 */
@Data
@ApiModel(value = "消息通知")
public class InfoMessageDto implements Serializable {

    @ApiModelProperty(value = "编号")
    private int id;

    @ApiModelProperty(value = "发布时间")
    private String Date;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "类型")
    private String type;
}
