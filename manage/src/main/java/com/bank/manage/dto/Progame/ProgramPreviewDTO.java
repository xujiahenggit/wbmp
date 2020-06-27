package com.bank.manage.dto.Progame;

import com.bank.manage.dto.MaterialDTO;
import com.bank.manage.dto.Progame.StyleDTO;
import com.bank.manage.dto.ProgramDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/4/24 14:47
 */
@Data
@ApiModel("节目预览用")
public class ProgramPreviewDTO extends StyleDTO implements Serializable {

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
