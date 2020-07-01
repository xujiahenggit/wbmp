package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "设备详细信息")
public class DeviceDetailInfoVo {

    @ApiModelProperty(value = "设备状态 在线，离线，无效, 空闲，交易中，故障，暂停服务，维护中，通信中断，耗材尽，已关机，重启中")
    private String deviceRunStatus;

    @ApiModelProperty(value = "厂商")
    private String deviceVendor;

    @ApiModelProperty(value = "设备型号")
    private String deviceModel;

    @ApiModelProperty(value = "读卡器状态: 故障,正常,无效")
    private String cardReader;

//    @ApiModelProperty(value = "射频读卡器状态 OK是正常,其他都是故障 ")
//    private String rfcardReader;

    @ApiModelProperty(value = "密码键盘状态 故障,正常,无效 ")
    private String pinPad;

//    @ApiModelProperty(value = "流水打印机状态  JRN-流水打印机故障，JRNPEPT-缺流水纸，JRNPJAM-流水打印机卡纸")
//    private String  journalPrinter;

    @ApiModelProperty(value = "取款模块状态  故障,正常,无效 ，钞多，断钞 ")
    private String  cashDispenser;

    @ApiModelProperty(value = "存款模块状态 故障,正常,无效 ，钞少，钞箱满  ")
    private String  cashAcceptor;

    @ApiModelProperty(value = "凭条打印机状态  故障,正常,无效 缺纸,卡纸")
    private String  printer;

//    @ApiModelProperty(value = "对帐单打印机状态  DOC-对帐单打印机故障，DOCPEPT-缺对账单纸，DOCPJAM-对帐单打印机卡纸 ")
//    private String  statementPrinter;


//    @ApiModelProperty(value = "存折打印机状态  OK是正常，其他都是故障 ")
//    private String   passbookPrinter;
}
