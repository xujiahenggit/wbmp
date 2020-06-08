package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *  考核原始数据-从表(全行)实体类
 *
 * @author zhaozhongyuan
 * @since 2020-05-14
 */
@Data
@TableName("T_EXAMINE_DATA_TEMP_BRANCH")
@ApiModel(value = "ExamineDataTempBranch对象", description = " 考核原始数据-从表(全行)")
public class ExamineDataTempBranchDO implements Serializable {

  private static final long serialVersionUID = 5483901532268922250L;
  /**
     * 主键
     */
  @ApiModelProperty(value = "主键")
  @TableId(value = "ID", type = IdType.AUTO)
  private Integer id;
    /**
     * 考核原始数据-主表主键
     */
  @ApiModelProperty(value = "考核原始数据-主表主键")
  @TableField("EXAMINE_ID")
  private Integer examineId;
    /**
     * 一级模块
     */
  @ApiModelProperty(value = "一级模块")
  @TableField("ONE_MODULE")
  private String oneModule;
    /**
     * 二级模块
     */
  @ApiModelProperty(value = "二级模块")
  @TableField("TWO_MODULE")
  private String twoModule;
    /**
     * 指标名称
     */
  @ApiModelProperty(value = "指标名称")
  @TableField("INDICATOR_NAME")
  private String indicatorName;
    /**
     * 扣分值
     */
  @ApiModelProperty(value = "扣分值")
  @TableField("DEDUCTION")
  private Integer deduction;
    /**
     * 扣分描述
     */
  @ApiModelProperty(value = "扣分描述")
  @TableField("DEDUCTION_SPEC")
  private String deductionSpec;


}
