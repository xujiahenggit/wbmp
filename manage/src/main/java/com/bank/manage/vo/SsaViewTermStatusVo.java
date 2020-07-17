package com.bank.manage.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: zhangfuqiang
 * @Date: 2020/7/15 22:50
 * 终端状态表
 */
@Data
@ApiModel
public class SsaViewTermStatusVo {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "终端编号")
    private String strtermnum;

    @ApiModelProperty(value = "服务状态： 无效 : -1,空闲: 0,交易中 : 1,硬件故障停止服务 : 2,管理命令暂停服务 : 3,维护中 : 4,通讯中断: 5,耗材尽停止服务: 6,已关机 : 7,正在重新启动 : 8")
    private String svcstatus;

    @ApiModelProperty(value = "硬件状态:  无效 : -1,正常 : 0,警告 : 1,运维类故障 : 2,硬件故障 : 3")
    private String ihdwstatus;

    @ApiModelProperty(value = "服务状态开始")
    private String dtsvcstatusbegin;

    @ApiModelProperty(value = "终端监控状态：无效 -1，正常 0，关闭 1")
    private String iagentstatus;

    @ApiModelProperty(value = "读卡器状态：")
    private String vmcardreader;

    @ApiModelProperty(value = "射频读卡器状态：")
    private String vmrfcardreader;

    @ApiModelProperty(value = "密码键盘状态：")
    private String vmpinpad;

    @ApiModelProperty(value = "流水打印机状态：")
    private String vmjournalprinter;

    @ApiModelProperty(value = "取款模块状态：")
    private String  vmcashdispenser;

    @ApiModelProperty(value = "存款模块状态：")
    private String  vmcashacceptor;

    @ApiModelProperty(value = "凭条打印机状态：")
    private String   vmreceiptprinter;

    @ApiModelProperty(value = "对帐单打印机状态：")
    private String  vmstatementprinter;

    @ApiModelProperty(value = "存折打印机状态：")
    private String  vmpassbookprinter;

    @ApiModelProperty(value = "存款箱状态：无效 -1，正常 0，存款钞多 1，存款钞满 2；默认为 -1")
    private String  cashacceptorinfo;

    @ApiModelProperty(value = "取款箱状态：无效 -1，正常 0，取款钞少 1，取款钞尽 2；默认为 -1")
    private String  cashdispenserinfo;

    @ApiModelProperty(value = "发卡器状态：CARDDIS 发卡器故障")
    private String  dccarddispenser;


}
