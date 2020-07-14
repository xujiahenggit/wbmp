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
 * 银行信息表实体类
 *
 * @author 代码自动生成
 * @since 2020-07-10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("dat_bank")
@ApiModel(value = "DatBank对象", description = "银行信息表")
public class DatBankDO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 表ID
     */
  @ApiModelProperty(value = "表ID")
  @TableId(value = "ID", type = IdType.AUTO)
  private Integer id;
    /**
     * 银行编号
     */
  @ApiModelProperty(value = "银行编号")
  @TableField("STRBANKNUM")
  private String strbanknum;
    /**
     * 银行名称
     */
  @ApiModelProperty(value = "银行名称")
  @TableField("STRBANKNAME")
  private String strbankname;
    /**
     * 银行英文名称
     */
  @ApiModelProperty(value = "银行英文名称")
  @TableField("STRBANKNAMEEN")
  private String strbanknameen;
    /**
     * 组织机构号
     */
  @ApiModelProperty(value = "组织机构号")
  private String orgCode;


}
