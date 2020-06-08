package com.bank.manage.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: Andy
 * @Date: 2020/5/8 22:50
 */
@Data
@ApiModel
public class ProgramListDTO implements Serializable {
    private static final long serialVersionUID = -572104941560733167L;
    /**
     * 节目主键
     */
    @ApiModelProperty(value = "节目ID")
    private Integer programId;

    /**
     * 节目名称
     */
    @ApiModelProperty(value = "节目名称")
    private String programName;

    /**
     * 节目类型
     */
    @ApiModelProperty(value = "节目类型")
    private String programType;

    /**
     * 机构号
     */
    @ApiModelProperty(value = "机构号")
    private String orgId;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createdUser;

    /**
     * 发布时间
     */
    @ApiModelProperty(value = "发布时间")
    private LocalDateTime createdTime;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private String startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private String endTime;

    /**
     * 终端编号
     */
    @ApiModelProperty(value = "终端编号")
    private String terminalNum;

    /**
     * 机构名称
     */
    @ApiModelProperty(value = "机构名称")
    private String orgName;

    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备名称")
    private String deviceName;

    /**
     * 设备类型
     */
    @ApiModelProperty(value = "设备类型")
    private String deviceType;
}
