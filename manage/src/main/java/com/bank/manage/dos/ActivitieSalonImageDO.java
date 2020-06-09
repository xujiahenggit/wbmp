package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(" 活动沙龙图片")
@TableName("T_ACTIVITIE_SALON_IMAGE")
public class ActivitieSalonImageDO implements Serializable {
    private static final long serialVersionUID = -2357288999315258740L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "活动沙龙ID")
    @TableField("ACTIVITIE_ID")
    private Integer activitieId;

    @ApiModelProperty(value = "图片路径")
    @TableField("IMAGE_PATH")
    private String imagePath;

    @ApiModelProperty(value = "排序")
    @TableField("SORT")
    private Integer sort;

}
