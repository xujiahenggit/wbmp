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
 * 活动沙龙流水 实体类
 *
 * @author zhaozhongyuan
 * @since 2020-06-05
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("T_ACTIVITIE_SALON_LOG")
@ApiModel(value = "ActivitieSalonLog对象", description = "活动沙龙流水 ")
public class ActivitieSalonLogDO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
  @ApiModelProperty(value = "主键")
  @TableId(value = "ID", type = IdType.AUTO)
  private Integer id;
    /**
     * 活动沙龙主键
     */
  @ApiModelProperty(value = "活动沙龙主键")
  @TableField("ACTIVITIE_ID")
  private Integer activitieId;
    /**
     * 机构编号
     */
  @ApiModelProperty(value = "机构编号")
  @TableField("ORG_ID")
  private String orgId;
    /**
     * 机构名称
     */
  @ApiModelProperty(value = "机构名称")
  @TableField("ORG_NAME")
  private String orgName;
    /**
     * 播放时长
     */
  @ApiModelProperty(value = "播放时长")
  @TableField("PLAY_TIME")
  private BigDecimal playTime;
    /**
     * 启动时间
     */
  @ApiModelProperty(value = "启动时间")
  @TableField("START_TIME")
  private LocalDateTime startTime;


}
