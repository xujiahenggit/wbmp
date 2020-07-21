package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author: Andy
 * @Date: 2020/6/20 9:44
 */
@Data
@ApiModel("赛马制内容")
public class HouseRaceCharts implements Serializable {
    @ApiModelProperty(value = "赛马制内容")
    private List<HouseRaceItem> indicator;

    @ApiModelProperty(value = "值")
    private List<Map<String,Object>> data;
}
