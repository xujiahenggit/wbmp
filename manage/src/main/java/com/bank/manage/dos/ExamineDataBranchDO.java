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
 * 考核原始数据-主表(全行) 实体类
 *
 * @author zhaozhongyuan
 * @since 2020-05-14
 */
@Data
@TableName("T_EXAMINE_DATA_BRANCH")
@ApiModel(value = "ExamineDataBranch对象", description = "考核原始数据-主表(全行) ")
public class ExamineDataBranchDO implements Serializable {

  private static final long serialVersionUID = -2742506651950476845L;
  /**
     * 主键
     */
  @ApiModelProperty(value = "主键")
  @TableId(value = "ID", type = IdType.AUTO)
  private Integer id;
    /**
     * 数据导入记录表主键
     */
  @ApiModelProperty(value = "数据导入记录表主键")
  @TableField("EXCEL_ID")
  private Integer excelId;
    /**
     * 年份
     */
  @ApiModelProperty(value = "年份")
  @TableField("EXCEL_DATE")
  private String excelDate;
    /**
     * 季度
     */
  @ApiModelProperty(value = "季度")
  @TableField("EXCEL_QUARTER")
  private String excelQuarter;
    /**
     * 考核原始数据-主表主键 自关联：机构为分行时为空，机构为支行网点时为对应分行数据主键
     */
  @ApiModelProperty(value = "考核原始数据-主表主键 自关联：机构为分行时为空，机构为支行网点时为对应分行数据主键")
  @TableField("EXAMINE_ID")
  private Integer examineId;
    /**
     * 分支行机构号
     */
  @ApiModelProperty(value = "分支行机构号")
  @TableField("ORG_ID")
  private String orgId;
    /**
     * 分支行机构名称
     */
  @ApiModelProperty(value = "分支行机构名称")
  @TableField("ORG_NAME")
  private String orgName;
    /**
     * 考核得分
     */
  @ApiModelProperty(value = "考核得分")
  @TableField("EXAMINE_SCORE")
  private Integer examineScore;


}
