package com.bank.manage.dto.Progame;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/4/28 10:13
 */
@Data
public class PadProgramDto implements Serializable {
    @ApiModelProperty(value = "节目主键" )
    private Integer programId;

    @ApiModelProperty(value = "机构编号" )
    private String orgId;

    @ApiModelProperty(value = "设备编号" )
    private String deviceNo;

    @ApiModelProperty(value = "状态码" )
    private String code;

    @ApiModelProperty(value = "节目名称" )
    private String programeName;

    @ApiModelProperty("素材列表")
    private List<ProgramData> data;
}
