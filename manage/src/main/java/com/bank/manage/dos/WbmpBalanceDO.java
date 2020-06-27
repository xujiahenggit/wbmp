package com.bank.manage.dos;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * 存款统计表实体类
 *
 * @author zhaozhongyuan
 * @since 2020-06-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wbmp_balance")
@ApiModel(value = "WbmpBalance对象", description = "存款统计表")
public class WbmpBalanceDO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * id
     */
  @ApiModelProperty(value = "id")
  private String id;
    /**
     * 币种
     */
  @ApiModelProperty(value = "币种")
  private String currCd;
    /**
     * 机构号
     */
  @ApiModelProperty(value = "机构号")
  private String orgNo;
    /**
     * 储蓄存款
     */
  @ApiModelProperty(value = "储蓄存款")
  private BigDecimal privateBal;
    /**
     * 对公存款
     */
  @ApiModelProperty(value = "对公存款")
  private BigDecimal publicBal;
    /**
     * 一般性存款
     */
  @ApiModelProperty(value = "一般性存款")
  private BigDecimal generalityBal;
    /**
     * 表内贷款
     */
  @ApiModelProperty(value = "表内贷款")
  private BigDecimal bankBal;
    /**
     * 零售贷款及垫款
     */
  @ApiModelProperty(value = "零售贷款及垫款")
  private BigDecimal lsdkjdkBal;
    /**
     * 对公贷款
     */
  @ApiModelProperty(value = "对公贷款")
  private BigDecimal dgBal;
    /**
     * 对公贷款(调度口径)
     */
  @ApiModelProperty(value = "对公贷款(调度口径)")
  private BigDecimal dgDdBal;
    /**
     * 更新日期
     */
  @ApiModelProperty(value = "更新日期")
  private String updateDate;


}
