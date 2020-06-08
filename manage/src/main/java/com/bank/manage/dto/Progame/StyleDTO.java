package com.bank.manage.dto.Progame;

import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: Andy
 * @Date: 2020/4/24 16:24
 */
@Data
@ApiModel("样式 预览节目用")
public class StyleDTO implements Serializable {

    @ApiModelProperty(value = "样式主键" )
    private Integer id;
    /**
     * 设备类型
     */
    @ApiModelProperty(value = "设备类型" )
    private String deviceType;
    /**
     * 样式
     */
    @ApiModelProperty(value = "样式" )
    private String style;
    /**
     * 样式名称
     */
    @ApiModelProperty(value = "样式名称" )
    private String styleName;
    /**
     * 样式分类
     */
    @ApiModelProperty(value = "样式分类" )
    private String styleType;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人" )
    private String createdUser;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间" )
    private LocalDateTime createdTime;
    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人" )
    private String updateUser;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间" )
    private LocalDateTime updateTime;

    /**
     * layout
     */
    @ApiModelProperty(value = "layout" )
    private JSONArray layout;

    @ApiModelProperty(value = "样式预览路径")
    private String stylePath;
}
