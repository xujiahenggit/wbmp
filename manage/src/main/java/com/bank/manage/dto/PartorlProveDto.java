package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/5/22 15:23
 */
@ApiModel
@Data
public class PartorlProveDto implements Serializable {
    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private Integer partorlProveNum;

    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称")
    private String partorlProveFileName;

    /**
     * 文件大小 单位为M
     */
    @ApiModelProperty(value = "文件大小 单位为KB")
    private Long partorlProveFileSize;

    /**
     * 文件路径
     */
    @ApiModelProperty(value = "文件路径")
    private String partorlProveFilePath;
}
