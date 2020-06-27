package com.bank.manage.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 素材信息
 *
 * @author
 * @date 2020-4-9
 */
@Data
@ApiModel(description = "素材信息")
public class MaterialDTO implements Serializable {

    private static final long serialVersionUID = 8222002022858225839L;

    @ApiModelProperty(value = "素材主键" )
    private Integer materialId;


    @ApiModelProperty(value = "素材名称" )
    private String materialName;


    @ApiModelProperty(value = "素材分类 00 图片 01 视频 02 PDF 03 文字" )
    private String materialType;


    @ApiModelProperty(value = "素材大小" )
    private String materialSize;

    @ApiModelProperty(value = "素材版式 00横版01竖版" )
    private String materialFormat;

    @ApiModelProperty(value = "素材说明" )
    private String materialSpec;

    @ApiModelProperty(value = "素材路径" )
    private String materialPath;

    @ApiModelProperty(value = "组织机构ID" )
    private String orgId;

    @ApiModelProperty(value = "标签" )
    private String label;

    @ApiModelProperty(value = "创建时间" )
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "创建人" )
    private String createdUser;


    @ApiModelProperty(value = "修改人" )
    private String updateUser;

    @ApiModelProperty(value = "修改时间" )
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "是否强制播放 00是 01否" )
    private String forcePlay;

    @ApiModelProperty(value = "失效时间" )
    private String expirTime;

    @ApiModelProperty(value = "设备类型" )
    private String deviceType;

    @ApiModelProperty(value = "文本" )
    private String text;

    @ApiModelProperty(value = "机构名称" )
    private String orgName;
}
