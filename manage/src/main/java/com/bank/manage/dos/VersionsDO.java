package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 应用版本维护实体类
 *
 * @author zhaozhongyuan
 * @since 2020-06-01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("T_VERSIONS")
@ApiModel(value = "Versions对象", description = "应用版本维护")
public class VersionsDO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键id")
    private Integer id;
    /**
     * 应用编号：大写字母，数字、下划线（不可开头结尾）
     */
    @ApiModelProperty(value = "应用编号：大写字母，数字、下划线（不可开头结尾）")
    @TableField("APP_NO")
    private String appNo;
    /**
     * 应用描述
     */
    @ApiModelProperty(value = "应用描述")
    @TableField("APP_DESC")
    private String appDesc;
    /**
     * 应用版本
     */
    @ApiModelProperty(value = "应用版本")
    @TableField("APP_VERSION")
    private Integer appVersion;
    /**
     * 应用名称
     */
    @ApiModelProperty(value = "应用名称")
    @TableField("APP_VERSION_NAME")
    private String appVersionName;
    /**
     * 是否强制更新
     */
    @ApiModelProperty(value = "是否强制更新,1：是；0：否")
    @TableField("FORCE_UPDATE")
    private Integer forceUpdate;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;
    /**
     * 更新路径
     */
    @ApiModelProperty(value = "更新路径")
    @TableField("APP_SAVE_PATH")
    private String appSavePath;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    @TableField("CREATE_USER")
    private String createUser;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    @TableField("UPDATE_USER")
    private String updateUser;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

}
