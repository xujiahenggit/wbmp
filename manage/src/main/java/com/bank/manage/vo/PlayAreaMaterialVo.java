package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ApiModel(description = "节目发布列表查询返回信息")
public class PlayAreaMaterialVo implements Serializable {
    private static final long serialVersionUID = 3656868650827570124L;

    @ApiModelProperty(value = "机构名称" )
    private String orgName;

    @ApiModelProperty(value = "设备名称" )
    private String deviceName;

    @ApiModelProperty(value = "设备类型" )
    private String deviceType;

    @ApiModelProperty(value = "节目名称" )
    private String programName;

    @ApiModelProperty(value = "开始时间" )
    private String startTime;

    @ApiModelProperty(value = "结束时间" )
    private String endTime;

    @ApiModelProperty(value = "发布时间" )
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "设备编号" )
    private String terminalNum;

    @ApiModelProperty(value = "节目主键" )
    private Integer programId;

}
