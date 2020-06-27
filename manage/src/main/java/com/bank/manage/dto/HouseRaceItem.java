package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/6/20 9:49
 */
@Data
@ApiModel("赛马制内容")
public class HouseRaceItem implements Serializable {

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "最大值")
    private String max;
}
