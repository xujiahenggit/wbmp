package com.bank.icop.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 检查附件
 * ClassName: CheckAccessoryVO
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/06/23 19:09:06
 */
@Data
@ApiModel(description = "登记检查-附件VO")
public class CheckAccessoryVO implements Serializable {

    /**
    * 
    */
    private static final long serialVersionUID = 1039677450331764133L;

    @ApiModelProperty(value = "附件名称")
    private String accessoryName;
    
    @ApiModelProperty(value = "附件路径")
    private String accessoryPath;
    
    @ApiModelProperty(value = "附件大小")
    private String accessorySize;
    
    @ApiModelProperty(value = "附件http路径")
    private String accessoryHttpPath;
}
