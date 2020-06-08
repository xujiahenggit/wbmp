package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.Builder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 全国标杆网点统计表(全行) 实体类
 *
 */
@Data
@TableName("T_EXAMPLE_BRANCH")
@ApiModel(value = "ExampleBranch对象", description = "全国标杆网点统计表(全行) ")
public class ExampleBranchDO implements Serializable {

    private static final long serialVersionUID = 1L;


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
     * 创建年份
     */
  @ApiModelProperty(value = "创建年份")
  @TableField("CREATE_YEAR")
  private Integer createYear;
    /**
     * 创建类型
     */
  @ApiModelProperty(value = "创建类型")
  @TableField("CREATE_TYPE")
  private String createType;
    /**
     * 网点机构号
     */
  @ApiModelProperty(value = "网点机构号")
  @TableField("ORG_ID")
  private String orgId;
    /**
     * 网点名称
     */
  @ApiModelProperty(value = "网点名称")
  @TableField("ORG_NAME")
  private String orgName;
    /**
     * 失效年份
     */
  @ApiModelProperty(value = "失效年份")
  @TableField("FAILURE_YEAR")
  private Integer failureYear;


}
