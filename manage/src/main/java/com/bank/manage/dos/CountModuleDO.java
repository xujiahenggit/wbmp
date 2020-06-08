package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *  统计模块主表实体类
 *
 * @author zhaozhongyuan
 * @since 2020-05-22
 */
@Data
@Builder
@TableName("T_COUNT_MODULE")
@ApiModel(value = "CountModule对象", description = " 统计模块主表")
public class CountModuleDO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
  @ApiModelProperty(value = "主键")
  @TableId(value = "ID", type = IdType.AUTO)
  private Integer id;
    /**
     * 年份
     */
  @ApiModelProperty(value = "年份")
  @TableField("MODULE_YEAR")
  private Integer moduleYear;
    /**
     * 一级模块
     */
  @ApiModelProperty(value = "一级模块")
  @TableField("ONE_MODULE")
  private String oneModule;
    /**
     * 总分数
     */
  @ApiModelProperty(value = "总分数")
  @TableField("COUNT_SCORE")
  private Integer countScore;
    /**
     * 创建人
     */
  @ApiModelProperty(value = "创建人")
  @TableField("CREATED_USER")
  private String createdUser;
    /**
     * 创建时间
     */
  @ApiModelProperty(value = "创建时间")
  @TableField("CREATED_TIME")
  private LocalDateTime createdTime;
    /**
     * 修改人
     */
  @ApiModelProperty(value = "修改人")
  @TableField("UPDATE_USER")
  private String updateUser;
    /**
     * 修改时间
     */
  @ApiModelProperty(value = "修改时间")
  @TableField("UPDATE_TIME")
  private LocalDateTime updateTime;


}
