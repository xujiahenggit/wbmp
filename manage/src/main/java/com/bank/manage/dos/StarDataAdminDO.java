package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 行内星级标准化网点统计(管理员) 实体类
 *
 */
@Data
@TableName("T_STAR_DATA_ADMIN")
@ApiModel(value = "StarDataAdmin对象", description = "行内星级标准化网点统计(管理员) ")
public class StarDataAdminDO implements Serializable {

  private static final long serialVersionUID = 8468510270075530736L;
  /**
     * 主键
     */
  @ApiModelProperty(value = "主键")
  @TableId(value = "ID", type = IdType.AUTO)
  private Integer id;
    /**
     * Excel数据导入记录表主键
     */
  @ApiModelProperty(value = "Excel数据导入记录表主键")
  @TableField("EXCEL_ID")
  private Integer excelId;
    /**
     * 生效年份
     */
  @ApiModelProperty(value = "生效年份")
  @TableField("START_YEAR")
  private Integer startYear;
    /**
     * 分支行机构号
     */
  @ApiModelProperty(value = "分支行机构号")
  @TableField("BRANCH_ORG_ID")
  private String branchOrgId;
    /**
     * 分支行机构名称
     */
  @ApiModelProperty(value = "分支行机构名称")
  @TableField("BRANCH_ORG_NAME")
  private String branchOrgName;
    /**
     * 网点机构号
     */
  @ApiModelProperty(value = "网点机构号")
  @TableField("OUT_ORG_ID")
  private String outOrgId;
    /**
     * 网点机构名称
     */
  @ApiModelProperty(value = "网点机构名称")
  @TableField("OUT_ORG_NAME")
  private String outOrgName;
    /**
     * 评定星级
     */
  @ApiModelProperty(value = "评定星级")
  @TableField("ASSESS_STAR")
  private String assessStar;


}
