package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 柜员在线时长明细表实体类
 *
 * @author 代码自动生成
 * @since 2020-07-03
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wbmp_abs_online_time")
@ApiModel(value = "WbmpAbsOnlineTime对象", description = "柜员在线时长明细表")
public class WbmpAbsOnlineTimeDO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 标识
     */
  @ApiModelProperty(value = "标识")
  @TableId("BUSISERNO")
  private String busiserno;
    /**
     * 机构号
     */
  @ApiModelProperty(value = "机构号")
  @TableField("ORG_ID")
  private String orgId;
    /**
     * 柜员号
     */
  @ApiModelProperty(value = "柜员号")
  @TableField("TELLER_ID")
  private String tellerId;
    /**
     * 接口码
     */
  @ApiModelProperty(value = "接口码")
  @TableField("INTERFACECODE")
  private String interfacecode;
    /**
     * 接口返回码
     */
  @ApiModelProperty(value = "接口返回码")
  @TableField("RTNCODE")
  private String rtncode;
    /**
     * 接口返回信息
     */
  @ApiModelProperty(value = "接口返回信息")
  @TableField("RTNMSG")
  private String rtnmsg;
    /**
     * 流水插入时间
     */
  @ApiModelProperty(value = "流水插入时间")
  @TableField("RECORDTIME")
  private String recordtime;
    /**
     * 流水发送时间
     */
  @ApiModelProperty(value = "流水发送时间")
  @TableField("STARTRECORDTIME")
  private String startrecordtime;
    /**
     * 数据日期
     */
  @ApiModelProperty(value = "数据日期")
  @TableField("DATA_DT")
  private String dataDt;


}
