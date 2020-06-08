package com.bank.manage.dto.ProgramUpdate;

import com.alibaba.fastjson.JSONArray;
import com.bank.manage.vo.AreaVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/4/28 16:10
 */
@Data
public class ProgramStyleDto implements Serializable {
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
     * 样式路径
     */
    @ApiModelProperty(value = "样式路径" )
    private String stylePath;

}
