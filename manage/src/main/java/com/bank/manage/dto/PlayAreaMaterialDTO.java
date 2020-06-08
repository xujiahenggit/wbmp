package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "区域素材信息")
public class PlayAreaMaterialDTO implements Serializable {

    private static final long serialVersionUID = 9157812026616270506L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键" )
    private Integer id;

    /**
     * 样式编号
     */
    @ApiModelProperty(value = "样式编号" )
    private Integer styleNum;

    /**
     * 区域编号
     */
    @ApiModelProperty(value = "区域编号" )
    private String areaNum;

    /**
     * 区域名称
     */
    @ApiModelProperty(value = "区域名称" )
    private String areaName;


    /**
     * 区域类型
     */
    @ApiModelProperty(value = "区域类型" )
    private String areaType;

    /**
     * 素材主键
     */
    @ApiModelProperty(value = "素材编号" )
    private Integer materialId;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序" )
    private String sort;

    /**
     * 播放时间
     */
    @ApiModelProperty(value = "播放时间" )
    private String playTime;

    /**
     * 节目主键
     */
    @ApiModelProperty(value = "节目主键" )
    private Integer programId;

    /**
     * 类型 00 图片轮播 01 视频轮播 10 混合播放
     */
    @ApiModelProperty(value = "节目类型" )
    private String type;

    /**
     * 终端编号
     */
    @ApiModelProperty(value = "终端编号" )
    private String terminalNum;

}
