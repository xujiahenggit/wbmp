package com.bank.core.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 影像平台文件实体类
 * ClassName: ImagePlatformFile
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/07/13 18:58:40
 */
@Data
@ApiModel(description = "影像文件信息")
public class ImagePlatformFile implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6732732710142915717L;

    @ApiModelProperty(value = "文件路径")
    private String filePath;

    @ApiModelProperty(value = "文件名")
    private String fileName;

    @ApiModelProperty(value = "展示路径")
    private String httpFilePath;

    @ApiModelProperty(value = "文件类型：1-本地文件；2-影像平台文件")
    private String fileType;

    @ApiModelProperty(value = "影像文件页码数")
    private int pageNum;
}
