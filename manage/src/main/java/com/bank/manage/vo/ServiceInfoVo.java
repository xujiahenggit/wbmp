package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 服务信息
 * 陈强
 */
@Data
@ApiModel
public class ServiceInfoVo {


    @ApiModelProperty(value = "厂商id")
    private String vendorId;

    @ApiModelProperty(value = "厂商名称")
    private String vendorName;

    @ApiModelProperty(value = "处理类型")
    private String dealType;
    @ApiModelProperty(value = "工单完成时间")
    private String workOrderCompleteTime;
    @ApiModelProperty(value = "服务工程师")
    private List<EngineerListVo> engineerListVoList;
    @ApiModelProperty(value = "服务主管")
    private List<DirectorVo> directorVoList;
}
