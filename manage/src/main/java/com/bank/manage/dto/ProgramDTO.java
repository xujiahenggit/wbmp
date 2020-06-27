package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ApiModel(description = "节目信息")
public class ProgramDTO implements Serializable {


    private static final long serialVersionUID = -2231146897356802068L;
    /**
     * 节目主键
     */
    @ApiModelProperty(value = "节目主键" )
    private Integer programId;

    /**
     * 节目名称
     */
    @ApiModelProperty(value = "节目名称" )
    private String programName;

    /**
     * 节目类型
     */
    @ApiModelProperty(value = "节目类型" )
    private String programType;


    /**
     * 机构号
     */
    @ApiModelProperty(value = "机构号" )
    private String orgId;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人" )
    private String createdUser;


    /**
     * 发布时间
     */
    @ApiModelProperty(value = "节目发布时间" )
    private LocalDateTime createdTime;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "节目开始时间" )
    private String startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "节目结束时间" )
    private String endTime;


    /**
     * 终端编号
     */
    @ApiModelProperty(value = "终端编号" )
    private String terminalNum;

}
