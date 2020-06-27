package com.bank.manage.dos;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * 网点AUM表实体类
 *
 * @author zhaozhongyuan
 * @since 2020-06-18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wbmp_operate_index_aum")
@ApiModel(value = "WbmpOperateIndexAum对象", description = "网点AUM表")
public class WbmpOperateIndexAumDO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 加工时间
     */
  @ApiModelProperty(value = "加工时间")
  @TableField("ETL_TIME")
  private String etlTime;
    /**
     * 机构号
     */
  @ApiModelProperty(value = "机构号")
  @TableField("ORG_ID")
  private String orgId;
    /**
     * 指标编号
     */
  @ApiModelProperty(value = "指标编号")
  @TableField("INDEX_NO")
  private String indexNo;
    /**
     * 指标名称
     */
  @ApiModelProperty(value = "指标名称")
  @TableField("INDEX_NAME")
  private String indexName;
    /**
     * 本期值
     */
  @ApiModelProperty(value = "本期值")
  @TableField("INDEX_VAL")
  private BigDecimal indexVal;
    /**
     * 数据日期
     */
  @ApiModelProperty(value = "数据日期")
  @TableField("DATA_DT")
  private String dataDt;


}
