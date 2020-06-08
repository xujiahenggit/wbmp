package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 设备信息表 
 * </p>
 * @author
 * @since 2020-04-01
 */
@Data
@Builder
@TableName("S_DEVICE")
public class DeviceDO implements Serializable {

    private static final long serialVersionUID = -5673049560230196229L;

    /**
     * 主键
     */
    @TableId(value = "DEVICE_ID", type = IdType.AUTO)
    private Integer deviceId;

    /**
     * 终端编号
     */
    private String terminalNum;

    /**
     * 机构号
     */
    private String orgId;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 设备类型
     */
    private String deviceType;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 设备系统
     */
    private String deviceSystem;

    /**
     * 设备品牌
     */
    private String deviceBrand;

    /**
     * 设备尺寸
     */
    private String deviceSize;

    /**
     * 设备型号
     */
    private String deviceModel;

    /**
     * 设备来源
     */
    private String deviceSource;

    /**
     * 供货商
     */
    private Integer upplierId;

    /**
     * 所在位置简称
     */
    private String addressAbbr;

    /**
     * 安装方式
     */
    private String installWay;

    /**
     * 网关
     */
    private String gateWay;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 设备MAC地址
     */
    private String mac;

    /**
     * 设备状态
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人ID
     */
    private String createUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人ID
     */
    private String updateUser;

    /**
     * 备注
     */
    private String remark;

    /**
     * 主题Id
     */
    private Integer itemId;

    /**
     * 主题名称
     */
    private String itemName;

    /**
     * 节目Id
     */
    private Integer programId;

    /**
     * 节目名称
     */
    private String programName;

    /**
     * 是否在线 00 在线 01 不在线
     */
    private String online;

    /**
     * 样式编号
     */
    private Integer styleNum;

}
