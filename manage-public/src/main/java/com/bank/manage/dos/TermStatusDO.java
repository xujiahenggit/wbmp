package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 终端状态表
 * </p>
 * @author
 * @since 2020-7-27
 */
@Data
@Builder
@TableName("z_termstatus")
public class TermStatusDO implements Serializable {

    /**
     * 主键ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 终端编号
     */
    private String strtermnum;

    /**
     *无效 : -1,空闲: 0,交易中 : 1,硬件故障停止服务 : 2,管理命令暂停服务
     *    : 3,维护中 : 4,通讯中断: 5,耗材尽停止服务: 6,已关机 : 7,正在重新启动 : 8
     */
    private String svcstatus;

    /**
     * 无效 : -1,正常 : 0,警告 : 1,运维类故障 : 2,硬件故障 : 3
     */
    private String ihdwstatus;

    /**
     * 服务状态开始
     */
    private String dtsvcstatusbegin;

    /**
     * 无效 : -1,正常 : 0,关闭 : 1
     */
    private String iagentstatus;

    /**
     * 读卡器状态：CARD 读卡器故障, CARDJAM 读卡器卡被卡住, CARDBIN 读卡器回收盒满
     */
    private String vmcardreader;

    /**
     * 射频读卡器状态
     */
    private String vmrfcardreader;

    /**
     * 密码键盘状态：PIN 密码键盘模块故障；默认为 -1
     */
    private String vmpinpad;

    /**
     * 流水打印机状态：JRN 流水打印机故障，JRNPEPT 缺流水纸，JRNPJAM 流水打印机卡纸
     */
    private String vmjournalprinter;

    /**
     * 取款模块状态
     */
    private String vmcashdispenser;

    /**
     * 存款模块状态
     */
    private String vmcashacceptor;

    /**
     * 凭条打印机状态：REC 凭条打印机故障，RECPEPT 缺凭条纸，RECPJAM 凭条打印机卡纸
     */
    private String vmreceiptprinter;

    /**
     * 对帐单打印机状态：DOC 对帐单打印机故障，DOCPEPT 缺对账单纸，DOCPJAM 对帐单打印机卡纸
     */
    private String vmstatementprinter;

    /**
     * 存折打印机状态
     */
    private String vmpassbookprinter;

    /**
     *
     */
    private String vminvoiceprinter;

    /**
     *
     */
    private String vminvoiceprinter2;

    /**
     * 存款箱状态：无效 -1，正常 0，存款钞多 1，存款钞满 2
     */
    private String cashacceptorinfo;

    /**
     * 取款箱状态：无效 -1，正常 0，取款钞少 1，取款钞尽 2
     */
    private String cashdispenserinfo;

    /**
     * 二维码状态：BARCODE 二维码模块故障
     */
    private String dcbarcode;

    /**
     * 个性化读卡器状态：CARDINDI 读卡器故障, CARDINDIJAM 读卡器卡被卡住, CARDINDIBIN 读卡器回收盒满
     */
    private String dccardreaderindi;

    /**
     * 发卡器状态：CARDDIS 发卡器故障
     */
    private String dccarddispenser;

    /**
     * 个性化发卡器状态：CARDDISINDI 个性化发卡器故障
     */
    private String dccarddispenserindi;

    /**
     * Ukey发放器状态：UKEYDIS Ukey发放器故障
     */
    private String dcukeydispenser;

    /**
     * Ukey读取器状态：UKEY Ukey读取器故障
     */
    private String dcukeyreader;
}
