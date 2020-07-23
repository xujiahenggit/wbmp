package com.bank.esb.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * cq
 */
@Data
public class ServiceInformationDto {
    @ApiModelProperty(value = "服务商")
    private String serviceProvider;

    @ApiModelProperty(value = "服务主管ID")
    private String serverId;

    @ApiModelProperty(value = "服务主管姓名")
    private String serverName;

    @ApiModelProperty(value = "服务主管手机号")
    private String serverPhone;

    @ApiModelProperty(value = "服务工程师ID")
    private String engineerId;

    @ApiModelProperty(value = "服务工程师姓名")
    private String engineerName;

    @ApiModelProperty(value = "服务工程师手机号")
    private String engineerPhone;

    @ApiModelProperty(value = "处理方式")
    private String processMode;

    @ApiModelProperty(value = "完成时间")
    private LocalDateTime finishTime;
}
