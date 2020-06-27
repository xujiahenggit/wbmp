package com.bank.manage.dos;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * 网点视图计算公式参数表实体类
 *
 * @author zhaozhongyuan
 * @since 2020-06-18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wbmp_common_calc_method")
@ApiModel(value = "WbmpCommonCalcMethod对象", description = "网点视图计算公式参数表")
public class WbmpCommonCalcMethodDO implements Serializable {

    private static final long serialVersionUID = 1L;


  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;
    /**
     * s11存款百分比
     */
  @ApiModelProperty(value = "s11存款百分比")
  private BigDecimal balPer;
    /**
     * s111月日均存款百分比
     */
  @ApiModelProperty(value = "s111月日均存款百分比")
  private BigDecimal avgMouthBalPer;
    /**
     * 全行标准月日均存款值
     */
  @ApiModelProperty(value = "全行标准月日均存款值")
  private BigDecimal standAvgMouthBal;
    /**
     * s112aum百分比
     */
  @ApiModelProperty(value = "s112aum百分比")
  private BigDecimal aumPer;
    /**
     * 全行标准aum值
     */
  @ApiModelProperty(value = "全行标准aum值")
  private BigDecimal standAumNum;
    /**
     * s12客群百分比
     */
  @ApiModelProperty(value = "s12客群百分比")
  private BigDecimal cusPer;
    /**
     * s121普通客户百分比
     */
  @ApiModelProperty(value = "s121普通客户百分比")
  private BigDecimal normalCusPer;
    /**
     * 全行标准普通客户量
     */
  @ApiModelProperty(value = "全行标准普通客户量")
  private BigDecimal standNormalCusNum;
    /**
     * s122金卡客户百分比
     */
  @ApiModelProperty(value = "s122金卡客户百分比")
  private BigDecimal goldCusPer;
    /**
     * 全行标准金卡客户量
     */
  @ApiModelProperty(value = "全行标准金卡客户量")
  private BigDecimal standGoldCusNum;
    /**
     * s123白金客户百分比
     */
  @ApiModelProperty(value = "s123白金客户百分比")
  private BigDecimal platinumCusPer;
    /**
     * 全行标准白金客户量
     */
  @ApiModelProperty(value = "全行标准白金客户量")
  private BigDecimal standPlatinumCusNum;
    /**
     * s124钻石客户百分比
     */
  @ApiModelProperty(value = "s124钻石客户百分比")
  private BigDecimal diamonCusPer;
    /**
     * 全行标准钻石客户量
     */
  @ApiModelProperty(value = "全行标准钻石客户量")
  private BigDecimal standDiamonCusNum;
    /**
     * s21网点运营视图百分比
     */
  @ApiModelProperty(value = "s21网点运营视图百分比")
  private BigDecimal operatViewPer;
    /**
     * s211运营效能百分比
     */
  @ApiModelProperty(value = "s211运营效能百分比")
  private BigDecimal operatEffectPer;
    /**
     * s2111日均业务量百分比
     */
  @ApiModelProperty(value = "s2111日均业务量百分比")
  private BigDecimal dayilTrafficPer;
    /**
     * 全行标准业务量
     */
  @ApiModelProperty(value = "全行标准业务量")
  private BigDecimal standDayilTrafficNum;
    /**
     * s212赛马值百分比
     */
  @ApiModelProperty(value = "s212赛马值百分比")
  private BigDecimal saimazhiPer;
    /**
     * s2121赛马值百分比
     */
  @ApiModelProperty(value = "s2121赛马值百分比")
  private BigDecimal smzPer;
    /**
     * s21211_无纸化百分比
     */
  @ApiModelProperty(value = "s21211_无纸化百分比")
  private BigDecimal wzhPer;
    /**
     * 银企对账率百分比
     */
  @ApiModelProperty(value = "银企对账率百分比")
  private BigDecimal yqdzPer;
    /**
     * 电子对账率百分比
     */
  @ApiModelProperty(value = "电子对账率百分比")
  private BigDecimal dzdzPer;
    /**
     * 对公账户线上开户率百分比
     */
  @ApiModelProperty(value = "对公账户线上开户率百分比")
  private BigDecimal dgzhxskhPer;
    /**
     * 企业客户线上开通率百分比
     */
  @ApiModelProperty(value = "企业客户线上开通率百分比")
  private BigDecimal qykhxsktPer;
    /**
     * 现金业务分流率百分比
     */
  @ApiModelProperty(value = "现金业务分流率百分比")
  private BigDecimal xjywflPer;
    /**
     * 自助设备业务分担率百分比
     */
  @ApiModelProperty(value = "自助设备业务分担率百分比")
  private BigDecimal zzsbywfdPer;
    /**
     * 自助设备可用率百分比
     */
  @ApiModelProperty(value = "自助设备可用率百分比")
  private BigDecimal zzsbkyPer;
    /**
     * s22网点服务视图百分比
     */
  @ApiModelProperty(value = "s22网点服务视图百分比")
  private BigDecimal serviceViewPer;
    /**
     * s221网点服务水平百分比
     */
  @ApiModelProperty(value = "s221网点服务水平百分比")
  private BigDecimal serviceLevelPer;
    /**
     * s2211对外服务水平百分比
     */
  @ApiModelProperty(value = "s2211对外服务水平百分比")
  private BigDecimal extServiceLevelPer;
    /**
     * s22112客户平均等待时间百分比
     */
  @ApiModelProperty(value = "s22112客户平均等待时间百分比")
  private BigDecimal cusAvgWaitTimePer;
    /**
     * s2211全行标准等待时间
     */
  @ApiModelProperty(value = "s2211全行标准等待时间")
  private BigDecimal standAvgWaitTime;


}
