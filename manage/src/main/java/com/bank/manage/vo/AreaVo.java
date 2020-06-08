package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("样式信息")
@Data
public class AreaVo implements Serializable {

    @ApiModelProperty(value = "区域高度")
    private String height;
    @ApiModelProperty(value = "区域编号")
    private String layoutId;
    @ApiModelProperty(value = "左边距")
    private String leftMargin;
    @ApiModelProperty(value = "滚动文字方向")
    private String rollType;
    @ApiModelProperty(value = "上边距")
    private String topMargin;
    @ApiModelProperty(value = "区域名称")
    private String type;
    @ApiModelProperty(value = "区域名称")
    private String name;
    @ApiModelProperty(value = "区域宽度")
    private String width;
    @ApiModelProperty(value = "字体大小")
    private String fontsize;
    @ApiModelProperty(value = "区域颜色")
    private String color;
    @ApiModelProperty(value = "日期字体大小  时间区域传值")
    private String dateFontSize;
    @ApiModelProperty(value = "时间字体大小  时间区域传值")
    private String timeFontSize;



}
