package com.bank.core.entity;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 影像平台接口返回对象
 * ClassName: ImagePlatformResponse
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/07/13 16:31:09
 */
@Data
@ToString
@ApiModel(description = "影像接口返回")
public class ImagePlatformResponse implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4885638992983080398L;

    @ApiModelProperty(value = "影像ID")
    private String contentId;

    @ApiModelProperty(value = "影像时间")
    private String busiStartDate;

    @ApiModelProperty(value = "影像流水号")
    private String busiSerialNo;

    @ApiModelProperty(value = "影像个数")
    private int amount;

    @ApiModelProperty(value = "影像文件数组")
    private List<ImagePlatformFile> imagePlatformFileList;
}
