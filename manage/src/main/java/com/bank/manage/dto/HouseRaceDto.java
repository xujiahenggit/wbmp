package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/6/20 9:41
 */
@Data
@ApiModel(value = "赛马制")
public class HouseRaceDto implements Serializable {
    @ApiModelProperty(value = "赛马制")
   private HouseRaceCharts charts;
}
