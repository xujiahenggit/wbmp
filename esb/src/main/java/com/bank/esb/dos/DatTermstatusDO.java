package com.bank.esb.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
 * 终端状态表实体类
 *
 * @author 代码自动生成
 * @since 2020-07-10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("dat_termstatus")
@ApiModel(value = "DatTermstatus对象", description = "终端状态表")
public class DatTermstatusDO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 表ID
     */
  @ApiModelProperty(value = "表ID")
  @TableId(value = "ID", type = IdType.AUTO)
  private Integer id;
    /**
     * 终端编号
     */
  @ApiModelProperty(value = "终端编号")
  @TableField("STRTERMNUM")
  private String strtermnum;
    /**
     * 无效 : -1,空闲: 0,交易中 : 1,硬件故障停止服务 : 2,管理命令暂停服务 : 3,维护中 : 4,通讯中断: 5,耗材尽停止服务: 6,已关机 : 7,正在重新启动 : 8
     */
  @ApiModelProperty(value = "无效 : -1,空闲: 0,交易中 : 1,硬件故障停止服务 : 2,管理命令暂停服务 : 3,维护中 : 4,通讯中断: 5,耗材尽停止服务: 6,已关机 : 7,正在重新启动 : 8")
  @TableField("SVCSTATUS")
  private Integer svcstatus;
    /**
     * 无效 : -1,正常 : 0,警告 : 1,运维类故障 : 2,硬件故障 : 3
     */
  @ApiModelProperty(value = "无效 : -1,正常 : 0,警告 : 1,运维类故障 : 2,硬件故障 : 3")
  @TableField("IHDWSTATUS")
  private Integer ihdwstatus;
    /**
     * 服务状态开始
     */
  @ApiModelProperty(value = "服务状态开始")
  @TableField("DTSVCSTATUSBEGIN")
  private LocalDateTime dtsvcstatusbegin;
    /**
     * 无效 : -1,正常 : 0,关闭 : 1
     */
  @ApiModelProperty(value = "无效 : -1,正常 : 0,关闭 : 1")
  @TableField("IAGENTSTATUS")
  private Integer iagentstatus;
    /**
     * 读卡器状态：CARD 读卡器故障, CARDJAM 读卡器卡被卡住, CARDBIN 读卡器回收盒满
     */
  @ApiModelProperty(value = "读卡器状态：CARD 读卡器故障, CARDJAM 读卡器卡被卡住, CARDBIN 读卡器回收盒满")
  @TableField("VMCARDREADER")
  private String vmcardreader;
    /**
     * 射频读卡器状态
     */
  @ApiModelProperty(value = "射频读卡器状态")
  @TableField("VMRFCARDREADER")
  private String vmrfcardreader;
    /**
     * 密码键盘状态：PIN 密码键盘模块故障；默认为 -1
     */
  @ApiModelProperty(value = "密码键盘状态：PIN 密码键盘模块故障；默认为 -1")
  @TableField("VMPINPAD")
  private String vmpinpad;
    /**
     * 流水打印机状态：JRN 流水打印机故障，JRNPEPT 缺流水纸，JRNPJAM 流水打印机卡纸
     */
  @ApiModelProperty(value = "流水打印机状态：JRN 流水打印机故障，JRNPEPT 缺流水纸，JRNPJAM 流水打印机卡纸")
  @TableField("VMJOURNALPRINTER")
  private String vmjournalprinter;
    /**
     * 取款模块状态
     */
  @ApiModelProperty(value = "取款模块状态")
  @TableField("VMCASHDISPENSER")
  private String vmcashdispenser;
    /**
     * 存款模块状态
     */
  @ApiModelProperty(value = "存款模块状态")
  @TableField("VMCASHACCEPTOR")
  private String vmcashacceptor;
    /**
     * 凭条打印机状态：REC 凭条打印机故障，RECPEPT 缺凭条纸，RECPJAM 凭条打印机卡纸
     */
  @ApiModelProperty(value = "凭条打印机状态：REC 凭条打印机故障，RECPEPT 缺凭条纸，RECPJAM 凭条打印机卡纸")
  @TableField("VMRECEIPTPRINTER")
  private String vmreceiptprinter;
    /**
     * 对帐单打印机状态：DOC 对帐单打印机故障，DOCPEPT 缺对账单纸，DOCPJAM 对帐单打印机卡纸
     */
  @ApiModelProperty(value = "对帐单打印机状态：DOC 对帐单打印机故障，DOCPEPT 缺对账单纸，DOCPJAM 对帐单打印机卡纸")
  @TableField("VMSTATEMENTPRINTER")
  private String vmstatementprinter;
    /**
     * 存折打印机状态
     */
  @ApiModelProperty(value = "存折打印机状态")
  @TableField("VMPASSBOOKPRINTER")
  private String vmpassbookprinter;
  @TableField("VMINVOICEPRINTER")
  private String vminvoiceprinter;
  @TableField("VMINVOICEPRINTER2")
  private String vminvoiceprinter2;
    /**
     * 存款箱状态：无效 -1，正常 0，存款钞多 1，存款钞满 2
     */
  @ApiModelProperty(value = "存款箱状态：无效 -1，正常 0，存款钞多 1，存款钞满 2")
  @TableField("CASHACCEPTORINFO")
  private String cashacceptorinfo;
    /**
     * 取款箱状态：无效 -1，正常 0，取款钞少 1，取款钞尽 2
     */
  @ApiModelProperty(value = "取款箱状态：无效 -1，正常 0，取款钞少 1，取款钞尽 2")
  @TableField("CASHDISPENSERINFO")
  private String cashdispenserinfo;
    /**
     * 二维码状态：BARCODE 二维码模块故障
     */
  @ApiModelProperty(value = "二维码状态：BARCODE 二维码模块故障")
  @TableField("DCBarcode")
  private String DCBarcode;
    /**
     * 个性化读卡器状态：CARDINDI 读卡器故障, CARDINDIJAM 读卡器卡被卡住, CARDINDIBIN 读卡器回收盒满
     */
  @ApiModelProperty(value = "个性化读卡器状态：CARDINDI 读卡器故障, CARDINDIJAM 读卡器卡被卡住, CARDINDIBIN 读卡器回收盒满")
  @TableField("DCCardReaderIndi")
  private String DCCardReaderIndi;
    /**
     * 发卡器状态：CARDDIS 发卡器故障
     */
  @ApiModelProperty(value = "发卡器状态：CARDDIS 发卡器故障")
  @TableField("DCCardDispenser")
  private String DCCardDispenser;
    /**
     * 个性化发卡器状态：CARDDISINDI 个性化发卡器故障
     */
  @ApiModelProperty(value = "个性化发卡器状态：CARDDISINDI 个性化发卡器故障")
  @TableField("DCCardDispenserIndi")
  private String DCCardDispenserIndi;
    /**
     * Ukey发放器状态：UKEYDIS Ukey发放器故障
     */
  @ApiModelProperty(value = "Ukey发放器状态：UKEYDIS Ukey发放器故障")
  @TableField("DCUKeyDispenser")
  private String DCUKeyDispenser;
    /**
     * Ukey读取器状态：UKEY Ukey读取器故障
     */
  @ApiModelProperty(value = "Ukey读取器状态：UKEY Ukey读取器故障")
  @TableField("DCUKeyReader")
  private String DCUKeyReader;


}
