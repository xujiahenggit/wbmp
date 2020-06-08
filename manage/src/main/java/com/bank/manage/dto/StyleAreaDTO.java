package com.bank.manage.dto;

import com.bank.manage.vo.AreaVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ApiModel(description = "样式信息")
public class StyleAreaDTO implements Serializable {

    private static final long serialVersionUID = 1349366283979175529L;
    /**
     * 主键
     */
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
    @ApiModelProperty(value = "样式JSON" )
    private List<AreaVo> style;
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
     * 样式路径
     */
    @ApiModelProperty(value = "样式路径" )
    private String stylePath;

    /**
     * 宽度
     */
    @ApiModelProperty(value = "宽度" )
    private String layoutWidth;

    /**
     * 长度
     */
    @ApiModelProperty(value = "长度" )
    private String layoutHeight;

    /**
     * 背景图片地址
     */
    @ApiModelProperty(value = "背景图片地址" )
    private String layoutBackground;

}
