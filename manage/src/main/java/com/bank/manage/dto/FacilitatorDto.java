package com.bank.manage.dto;

import com.bank.auth.base.BaseController;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/6/24 10:47
 */
@ApiModel("引导员待办")
@Data
public class FacilitatorDto implements Serializable {

    @ApiModelProperty(value = "ID")
    private int id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "类型 TYPE_01:加班申请 TYPE_02:补卡申请 TYPE_03:月度满意度考核 TYPE_04:月度考勤")
    private String type;

    @ApiModelProperty(value = "日期")
    private String date;

}
