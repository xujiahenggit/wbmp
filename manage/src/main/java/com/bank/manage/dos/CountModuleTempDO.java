package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 统计模块从表 实体类
 *
 * @author zhaozhongyuan
 * @since 2020-05-22
 */
@Data
@Builder
@TableName("T_COUNT_MODULE_TEMP")
@ApiModel(value = "CountModuleTemp对象", description = "统计模块从表 ")
public class CountModuleTempDO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
  @ApiModelProperty(value = "主键")
  @TableId(value = "ID", type = IdType.AUTO)
  private Integer id;
    /**
     * 主表ID
     */
  @ApiModelProperty(value = "主表ID")
  @TableField("MODULE_ID")
  private Integer moduleId;
    /**
     * 二级模块
     */
  @ApiModelProperty(value = "二级模块")
  @TableField("TWO_MODULE")
  private String twoModule;
    /**
     * 分值
     */
  @ApiModelProperty(value = "分值")
  @TableField("TWO_MODULE_SCORE")
  private Integer twoModuleScore;


}
