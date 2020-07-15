package com.bank.core.entity;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 影像平台上传请求
 * ClassName: ImagePlatformRequest
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/07/14 10:34:27
 */
@Data
@ApiModel(description = "影像平台上传请求")
public class ImagePlatformRequest {

    @ApiModelProperty(value = "类型（现场检查-xcjc；预警监测-yjjc；）等")
    private String type;

    @ApiModelProperty(value = "文件目录主键")
    private String pathId;

    @ApiModelProperty(value = "影像ID")
    private String contentId;

    @ApiModelProperty(value = "影像时间")
    private String busiStartDate;

    @ApiModelProperty(value = "影像流水号")
    private String busiSerialNo;

    @ApiModelProperty(value = "删除影像文件列表")
    private List<String> deleteFileNames;
}
