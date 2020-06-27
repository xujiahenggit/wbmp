package com.bank.manage.dos;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * 网点机构存款表实体类
 *
 * @author zhaozhongyuan
 * @since 2020-06-22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wbmp_org_balance")
@ApiModel(value = "WbmpOrgBalance对象", description = "网点机构存款表")
public class WbmpOrgBalanceDO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 机构号
     */
  @ApiModelProperty(value = "机构号")
  @TableId("ORG_NO")
  private String orgNo;
    /**
     * 币种
     */
  @ApiModelProperty(value = "币种")
  @TableField("CURR_CD")
  private String currCd;
    /**
     * 储蓄存款
     */
  @ApiModelProperty(value = "储蓄存款")
  @TableField("PRIVATE_BAL")
  private BigDecimal privateBal;
    /**
     * 对公存款
     */
  @ApiModelProperty(value = "对公存款")
  @TableField("PUBLIC_BAL")
  private BigDecimal publicBal;
    /**
     * 一般性存款
     */
  @ApiModelProperty(value = "一般性存款")
  @TableField("GENERALITY_BAL")
  private BigDecimal generalityBal;
    /**
     * 日期
     */
  @ApiModelProperty(value = "日期")
  @TableField("DATA_DT")
  private String dataDt;


}
