package com.bank.manage.vo.partorlRecord;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/5/13 9:12
 */
@Data
@ApiModel("巡查证明文件")
public class PartorlProveVo implements Serializable {
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
     * 文件大小 单位为KB
     */
    @ApiModelProperty(value = "文件大小 单位为KB")
    private long partorlProveFileSize;

    /**
     * 文件路径
     */
    @ApiModelProperty(value = "文件路径")
    private String partorlProveFilePath;
}
