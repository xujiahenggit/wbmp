package com.bank.esb.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * 分行信息表实体类
 *
 * @author 代码自动生成
 * @since 2020-07-10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("dat_branch")
@ApiModel(value = "DatBranch对象", description = "分行信息表")
public class DatBranchDO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 表ID
     */
  @ApiModelProperty(value = "表ID")
  @TableId(value = "ID", type = IdType.AUTO)
  private Integer id;
    /**
     * 分行编号
     */
  @ApiModelProperty(value = "分行编号")
  @TableField("STRBRANCHNUM")
  private String strbranchnum;
    /**
     * 分行名称
     */
  @ApiModelProperty(value = "分行名称")
  @TableField("STRBRANCHNAME")
  private String strbranchname;
    /**
     * 银行编号
     */
  @ApiModelProperty(value = "银行编号")
  @TableField("STRBANKNUM")
  private String strbanknum;
    /**
     * 组织机构号
     */
  @ApiModelProperty(value = "组织机构号")
  private String orgCode;


}
