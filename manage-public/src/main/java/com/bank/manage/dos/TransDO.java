package com.bank.manage.dos;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("z_vw_translog")
public class TransDO implements Serializable {
    /**
     * 主键ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    @ExcelProperty(value = "ID", index = 0)
    private String id;

    /**
     * 权限号码
     */
    @TableField(exist = false)
    private String powerNum;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 设备分类 0: 现金自助 1: 非现金自助 2: 快柜设备 3: 大额机
     */
    private String deviceClass;

    /**
     * 设备类型 1: 自动取款机 2: 自动存款机 3: 自动存取款机 4: 自动查询机
     * 5: 多媒体终端 6：个性化发卡机 7：回单打印机 8：智能现金柜
     */
    private String deviceType;

    /**
     * 交易来源 （业务系统） ATM: ATM BSM: 非现金 HD: 回单打印机 VTM: VTM GXHFKJ: 个性发卡机
     */
    private String transSource;

    /**
     * 交易类型 存款、取款、查询、缴费
     */
    private String transType;

    /**
     * 对应消息报文中的TransCode字段
     */
    private String transCode;

    /**
     * 主机流水
     */
    private String hostTsn;

    /**
     * 交易机构号
     */
    private String transOrgno;

    /**
     * 所属机构号
     */
    private String ownerOrgno;

    /**
     * 终端编号
     */
    @ExcelProperty(value = "终端编号", index = 1)
    private String termNum;

    /**
     * 行内终端编号
     */
    private String innerTermNum;

    /**
     * 目标账户
     */
    private String targetAccount;

    /**
     * 柜员编号
     */
    @ExcelProperty(value = "", index = 2)
    private String tellernum;

    /**
     * 银行号
     */
    @ExcelProperty(value = "银行号", index = 3)
    private String strbanknum;

    /**
     * 分行号
     */
    @ExcelProperty(value = "分行号", index = 4)
    private String strbranchnum;

    /**
     * 支行号
     */
    @ExcelProperty(value = "支行号", index = 5)
    private String strsubbranchnum;

    /**
     * 自助行号
     */
    @ExcelProperty(value = "自助行号", index = 6)
    private String strssbnum;

    /**
     * 交易名称
     */
    @ExcelProperty(value = "交易名称", index = 7)
    private String transName;

    /**
     * 金额
     */
    @ExcelProperty(value = "金额", index = 8)
    private Float amount;

    /**
     * 账号
     */
    @ExcelProperty(value = "账号", index = 9)
    private String account;

    /**
     * 交易账号类型： 11000:本行借记卡 12000:本行信用卡 16000:本行商务卡 25000: 境外卡
     */
    @ExcelProperty(value = "卡类型", index = 10)
    private String accountType;

    /**
     * 返回码
     */
    @ExcelProperty(value = "返回码", index = 11)
    private String returnCode;

    /**
     * 交易返回描述
     */
    @ExcelProperty(value = "返回描述", index = 12)
    private String returnDesc;


    /**
     * 交易状态 1：成功，返回码等于00000000 2：失败，成功和不确定之外的返回码 3：不确定，返回码等于0002
     */
    @ExcelProperty(value = "交易状态", index = 13)
    private String transStatus;

    /**
     * 交易时间
     */
    @ExcelProperty(value = "交易时间", index = 14)
    private LocalDateTime transTime;

    @TableField(exist = false)
    private LocalDateTime beginTime;

    @TableField(exist = false)
    private LocalDateTime endTime;

}
