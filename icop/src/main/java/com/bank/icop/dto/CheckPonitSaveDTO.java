package com.bank.icop.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * ClassName: CheckPonitsSaveDTO
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/07/07 17:34:57
 */
@Data
public class CheckPonitSaveDTO implements Serializable {

    /**
    *
    */
    private static final long serialVersionUID = -2608359063079418125L;

    @ApiModelProperty(value = "检查任务主键PK")
    private String taskPk;

    @ApiModelProperty(value = "检查任务项主键PK")
    private String ePk;

    @ApiModelProperty(value = "检查笔数")
    private String checkNumber;

    @ApiModelProperty(value = "检查开始时间")
    private String checkStartDate;

    @ApiModelProperty(value = "检查结束时间")
    private String checkEndDate;

    @ApiModelProperty(value = "检查手段")
    private String checkMethod;

    @ApiModelProperty(value = "检查记录")
    private String checkDes;

    @ApiModelProperty(value = "其他/回头看")
    private String checkOther;

    @ApiModelProperty(value = "影像ID")
    private String contentid;

    @ApiModelProperty(value = "影像时间-yyyyMMdd")
    private String busistartdate;

    @ApiModelProperty(value = "影像流水号")
    private String busiserialno;
}
