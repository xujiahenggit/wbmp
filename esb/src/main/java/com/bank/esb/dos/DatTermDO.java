package com.bank.esb.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * 终端信息表实体类
 *
 * @author 代码自动生成
 * @since 2020-07-10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("dat_term")
@ApiModel(value = "DatTerm对象", description = "终端信息表")
public class DatTermDO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 表ID
     */
  @ApiModelProperty(value = "表ID")
  @TableId(value = "ID", type = IdType.AUTO)
  private Integer id;
  @TableField("STRBANKNUM")
  private String strbanknum;
    /**
     * 终端编号
     */
  @ApiModelProperty(value = "终端编号")
  @TableField("STRTERMNUM")
  private String strtermnum;
    /**
     * 设备ID
     */
  @ApiModelProperty(value = "设备ID")
  @TableField("DEVICEID")
  private Integer deviceid;
    /**
     * 分行号
     */
  @ApiModelProperty(value = "分行号")
  @TableField("STRBRANCHNUM")
  private String strbranchnum;
    /**
     * 支行号
     */
  @ApiModelProperty(value = "支行号")
  @TableField("STRSUBBRANCHNUM")
  private String strsubbranchnum;
    /**
     * 自助银行号
     */
  @ApiModelProperty(value = "自助银行号")
  @TableField("STRSSBNUM")
  private String strssbnum;
    /**
     * 网络地址
     */
  @ApiModelProperty(value = "网络地址")
  @TableField("STRNETADDR")
  private String strnetaddr;
    /**
     * 柜员号
     */
  @ApiModelProperty(value = "柜员号")
  @TableField("STRTELLERNUM")
  private String strtellernum;
    /**
     * 使用状态；待复核 0，复核不通过 1，待审批 2，审批通过 3，审批不通过 4，调试 5，暂停 6，启用 7，停用 8，撤销 9，默认为0
     */
  @ApiModelProperty(value = "使用状态；待复核 0，复核不通过 1，待审批 2，审批通过 3，审批不通过 4，调试 5，暂停 6，启用 7，停用 8，撤销 9，默认为0")
  @TableField("USINGSTATUS")
  private Integer usingstatus;
    /**
     * pin值
     */
  @ApiModelProperty(value = "pin值")
  @TableField("STRPINKEY")
  private String strpinkey;
    /**
     * mac值
     */
  @ApiModelProperty(value = "mac值")
  @TableField("STRMACKEY")
  private String strmackey;
    /**
     * ATM界面样式
     */
  @ApiModelProperty(value = "ATM界面样式")
  @TableField("STRUISTYLE")
  private String struistyle;
    /**
     * 侧键按钮样式
     */
  @ApiModelProperty(value = "侧键按钮样式")
  @TableField("STRPROFILESTYLE")
  private String strprofilestyle;
    /**
     * 备注
     */
  @ApiModelProperty(value = "备注")
  @TableField("STRMEMO")
  private String strmemo;
    /**
     * 加密方式；硬加密(国密) SM4，硬加密(DES) DES，软加密 EDS
     */
  @ApiModelProperty(value = "加密方式；硬加密(国密) SM4，硬加密(DES) DES，软加密 EDS")
  @TableField("STRPINPADTYPE")
  private String strpinpadtype;
    /**
     * 开始时间
     */
  @ApiModelProperty(value = "开始时间")
  @TableField("DTSTART")
  private LocalDate dtstart;
    /**
     * 结束时间
     */
  @ApiModelProperty(value = "结束时间")
  @TableField("DTEND")
  private LocalDate dtend;
    /**
     * 当前终端流水号
     */
  @ApiModelProperty(value = "当前终端流水号")
  @TableField("CURTERMTSN")
  private Integer curtermtsn;
    /**
     * 最后加钞时间
     */
  @ApiModelProperty(value = "最后加钞时间")
  @TableField("DTLASTREFILLCASHTIME")
  private LocalDateTime dtlastrefillcashtime;
    /**
     * 受理机构号
     */
  @ApiModelProperty(value = "受理机构号")
  @TableField("AGENNOACCEPT")
  private String agennoaccept;
    /**
     * 本机钱箱号
     */
  @ApiModelProperty(value = "本机钱箱号")
  @TableField("AGENNOCASH")
  private String agennocash;
    /**
     * 上送终端号
     */
  @ApiModelProperty(value = "上送终端号")
  @TableField("APTLID")
  private String aptlid;
    /**
     * 终端地址
     */
  @ApiModelProperty(value = "终端地址")
  @TableField("STRTERMADDR")
  private String strtermaddr;
    /**
     * 是否支持他行卡交易；否 0，是 1
     */
  @ApiModelProperty(value = "是否支持他行卡交易；否 0，是 1")
  @TableField("TRANSOTHERFG")
  private Integer transotherfg;
    /**
     * 清机钱箱号
     */
  @ApiModelProperty(value = "清机钱箱号")
  @TableField("AGENNOCLTM")
  private String agennocltm;
    /**
     * 加钞钱箱号
     */
  @ApiModelProperty(value = "加钞钱箱号")
  @TableField("AGENNOADMN")
  private String agennoadmn;
    /**
     * 交易机构号
     */
  @ApiModelProperty(value = "交易机构号")
  @TableField("STRORGNO")
  private String strorgno;
    /**
     * IC卡服务控制，1启用，0停用
     */
  @ApiModelProperty(value = "IC卡服务控制，1启用，0停用")
  @TableField("SERVICETYPE")
  private String servicetype;
    /**
     * PBOC类型：0接触，1非接
     */
  @ApiModelProperty(value = "PBOC类型：0接触，1非接")
  @TableField("PBOCTYPE")
  private String pboctype;
    /**
     * 是否是验证终端 0-否，1-是
     */
  @ApiModelProperty(value = "是否是验证终端 0-否，1-是")
  @TableField("FORVERIFICATION")
  private Integer forverification;
    /**
     * 所属机构号
     */
  @ApiModelProperty(value = "所属机构号")
  @TableField("ORGANIZATION")
  private String organization;
    /**
     * 国密主密钥下装方式:online-远程在线下装,offline-密钥分发器离线下装
     */
  @ApiModelProperty(value = "国密主密钥下装方式:online-远程在线下装,offline-密钥分发器离线下装")
  private String masterkeytype;


}
