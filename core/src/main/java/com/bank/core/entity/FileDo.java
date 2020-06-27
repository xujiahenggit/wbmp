package com.bank.core.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Andy
 * @Date: 2020/5/13 9:34
 */
@ApiModel(value = "文件模型")
@Data
public class FileDo implements Serializable {
    @ApiModelProperty(value = "上传文件名称")
    private String fileLocalName;

    @ApiModelProperty(value = "文件存储名称")
    private String fileServerName;

    @ApiModelProperty(value = "文件存储路径")
    private String fileSavePath;

    @ApiModelProperty(value = "文件访问路径")
    private String accssPath;

    @ApiModelProperty(value = "文件大小 单位M")
    private Long fileSize;
}
