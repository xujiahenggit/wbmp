package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * cq
 */
@Data
@ApiModel
public class PictureVo {
    @ApiModelProperty(value = "图片日期")
    private String startTime;

    @ApiModelProperty(value = "图片id")
    private String id;
}
