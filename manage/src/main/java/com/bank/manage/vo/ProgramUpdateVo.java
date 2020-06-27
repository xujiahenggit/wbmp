package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/4/26 16:04
 */
@ApiModel("节目更新用")
@Data
public class ProgramUpdateVo implements Serializable {
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
     * 开始时间
     */
    @ApiModelProperty(value = "节目开始时间 yyyyMMdd HH:mm" )
    private String startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "节目结束时间 yyyyMMdd HH:mm" )
    private String endTime;


    /**
     * 终端编号
     */
    @ApiModelProperty(value = "终端编号" )
    private String terminalNum;

    /**
     * 已选节目
     */
    @ApiModelProperty(value = "已选素材列表" )
    List<PlayMaterialVo> listPlayMaterial;
}
