package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 自助设备基本信息表实体类
 *
 * @author zhaozhongyuan
 * @since 2020-06-24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wbmp_atmp_basic_info")
@ApiModel(value = "WbmpAtmpBasicInfo对象", description = "自助设备基本信息表")
public class WbmpAtmpBasicInfoDO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 机构ID
     */
  @ApiModelProperty(value = "机构ID")
  private String orgId;
    /**
     * 设备ID
     */
  @ApiModelProperty(value = "设备ID")
  private String deviceId;
    /**
     * 自助渠道类型  1:智慧厅堂 2:智慧柜台 3:ATM 4:CRS快柜 5:壹站通 6:回单打印机 7:智能现金柜 
     */
  @ApiModelProperty(value = "自助渠道类型  1:智慧厅堂 2:智慧柜台 3:ATM 4:CRS快柜 5:壹站通 6:回单打印机 7:智能现金柜 ")
  private String deviceType;
    /**
     * 终端编号
     */
  @ApiModelProperty(value = "终端编号")
  private String termNo;
    /**
     * 设备状态 0-在线、1-离线、2-故障、3-维护
     */
  @ApiModelProperty(value = "设备状态")
  private String deviceRunStatus;
    /**
     * 厂商
     */
  @ApiModelProperty(value = "厂商")
  private String deviceVendor;
    /**
     * 设备型号
     */
  @ApiModelProperty(value = "设备型号")
  private String deviceModel;
    /**
     * 读卡器状态 CARD 读卡器故障, CARDJAM 读卡器卡被卡住, CARDBIN 读卡器回收盒满  
     */
  @ApiModelProperty(value = "读卡器状态 CARD 读卡器故障, CARDJAM 读卡器卡被卡住, CARDBIN 读卡器回收盒满  ")
  private String cardReader;
    /**
     * 射频读卡器状态   OK是正常，其他都是故障
     */
  @ApiModelProperty(value = "射频读卡器状态   OK是正常，其他都是故障")
  private String rfcardReader;
    /**
     * 密码键盘状态 PIN 密码键盘模块故障；默认为 -1
     */
  @ApiModelProperty(value = "密码键盘状态 PIN 密码键盘模块故障；默认为 -1")
  private String pinPad;
    /**
     * 流水打印机状态  JRN 流水打印机故障，JRNPEPT 缺流水纸，JRNPJAM 流水打印机卡纸
     */
  @ApiModelProperty(value = "流水打印机状态  JRN 流水打印机故障，JRNPEPT 缺流水纸，JRNPJAM 流水打印机卡纸")
  private String journalPrinter;
    /**
     * 取款模块状态 CDM :"出钞模块故障", DCASSERR : "取款钞箱模块故障",CASSEPT : "取款钞箱断钞",CASSMORE : 钞多"
     */
  @ApiModelProperty(value = "取款模块状态 CDM :出钞模块故障, DCASSERR : 取款钞箱模块故障,CASSEPT : 取款钞箱断钞,CASSMORE : 钞多")
  private String cashDispenser;
    /**
     * 存款模块状态 CIM :"存款模块故障",ICASSERR : "存款钞箱模块故障",DEP : "存款模块故障",CASSFULL : "存款钞箱满",CASSLESS : "钞少"
     */
  @ApiModelProperty(value = "存款模块状态 CIM :存款模块故障,ICASSERR : 存款钞箱模块故障,DEP : 存款模块故障,CASSFULL : 存款钞箱满,CASSLESS : 钞少")
  private String cashAcceptor;
    /**
     * 凭条打印机状态  REC 凭条打印机故障，RECPEPT 缺凭条纸，RECPJAM 凭条打印机卡纸
     */
  @ApiModelProperty(value = "凭条打印机状态  REC 凭条打印机故障，RECPEPT 缺凭条纸，RECPJAM 凭条打印机卡纸")
  private String receiptPrinter;
    /**
     * 对帐单打印机状态   DOC 对帐单打印机故障，DOCPEPT 缺对账单纸，DOCPJAM 对帐单打印机卡纸
     */
  @ApiModelProperty(value = "对帐单打印机状态   DOC 对帐单打印机故障，DOCPEPT 缺对账单纸，DOCPJAM 对帐单打印机卡纸")
  private String statementPrinter;
    /**
     * 存折打印机状态  OK是正常，其他都是故障
     */
  @ApiModelProperty(value = "存折打印机状态  OK是正常，其他都是故障")
  private String passbookPrinter;
    /**
     * 数据日期
     */
  @ApiModelProperty(value = "数据日期")
  private String dataDt;


}
