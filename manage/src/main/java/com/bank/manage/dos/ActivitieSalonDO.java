package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 活动沙龙 实体类
 *
 * @author zhaozhongyuan
 * @since 2020-06-05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("T_ACTIVITIE_SALON")
@ApiModel(value = "ActivitieSalon对象", description = "活动沙龙 ")
public class ActivitieSalonDO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
  @ApiModelProperty(value = "主键")
  @TableId(value = "ID", type = IdType.AUTO)
  private Integer id;
    /**
     * 活动名称
     */
  @ApiModelProperty(value = "活动名称")
  @TableField("ACTIVITIE_NAME")
  private String activitieName;
    /**
     * 活动类型 0产品营销1品牌宣传2监管宣讲3其他
     */
  @ApiModelProperty(value = "活动类型 0产品营销1品牌宣传2监管宣讲3其他")
  @TableField("ACTIVITIE_TYPE")
  private String activitieType;
    /**
     * 文件路径
     */
  @ApiModelProperty(value = "文件路径")
  @TableField("ACTIVITIE_PATH")
  private String activitiePath;


  @ApiModelProperty(value = "文件名称")
  @TableField("ACTIVITIE_FILE_NAME")
  private String activitieFileName;

    /**
     * 活动描述
     */
  @ApiModelProperty(value = "活动描述")
  @TableField("ACTIVITIE_DESC")
  private String activitieDesc;
    /**
     * 活动状态 0启用1停用
     */
  @ApiModelProperty(value = "活动状态 0启用1停用")
  @TableField("ACTIVITIE_STATUS")
  private String activitieStatus;
    /**
     * 营销时长
     */
  @ApiModelProperty(value = "营销时长",required = false)
  @TableField("ACTIVITIE_TIME")
  private BigDecimal activitieTime;
    /**
     * 营销次数
     */
  @ApiModelProperty(value = "营销次数",required = false)
  @TableField("ACTIVITIE_TOTAL")
  private Integer activitieTotal;
    /**
     * 机构号
     */
  @ApiModelProperty(value = "机构号")
  @TableField("ORG_ID")
  private String orgId;
    /**
     * 机构名称
     */
  @ApiModelProperty(value = "机构名称")
  @TableField("ORG_NAME")
  private String orgName;
    /**
     * 创建时间
     */
  @ApiModelProperty(value = "创建时间")
  @TableField("CREATED_TIME")
  private LocalDateTime createdTime;
    /**
     * 创建人
     */
  @ApiModelProperty(value = "创建人")
  @TableField("CREATED_BY")
  private String createdBy;
    /**
     * 更新人
     */
  @ApiModelProperty(value = "更新人")
  @TableField("UPDATED_BY")
  private String updatedBy;
    /**
     * 更新时间
     */
  @ApiModelProperty(value = "更新时间")
  @TableField("UPDATED_TIME")
  private LocalDateTime updatedTime;

  @ApiModelProperty(value = "解析状态 0 解析成功 1 未解析成功")
  @TableField("STATUS")
  private Integer status;
}
