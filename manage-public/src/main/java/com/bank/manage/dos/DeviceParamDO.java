package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 设备参数表
 * </p>
 * @author
 * @since 2020-7-4
 */
@Data
@Builder
@TableName("z_device")
public class DeviceParamDO implements Serializable {
    /**
     * 主键自增ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 设备序列号
     */
    private String STRDEVSN;

    /**
     * 设备型号
     */
    private String STRDEVMODEL;

    /**
     * 设备状态：待签收设备 0，库存设备 1，停用未回库设备 2，拒收设备 3，上线设备 4，已报废设备 5
     */
    private Integer ISTATUS;

    /**
     * 所属分行
     */
    private String STRBRANCHNUM;

    /**
     * 备注
     */
    private String STRMEMO;

    /**
     * 设备类型：自动取款机 1，自动存款机 2，自动存取款机 3，自动查询机 4
     */
    private Integer IDEVTYPE;

    /**
     * 开始时间
     */
    private LocalDateTime DTSTART;

    /**
     * 结束时间
     */
    private LocalDateTime DTEND;

    /**
     * 设备厂商
     */
    private String STRDEVMANU;

    /**
     * 添加时间
     */
    private LocalDateTime ADDDATE;

    /**
     * 开门方向：忽略 0，前开门 1，后开门 2
     */
    private Integer ISAFEDOORPOSITION;

    /**
     * 银行编号
     */
    private String STRBANKNUM;

    /**
     *设备分类：现金自助0、非现金自助1、快柜设备2
     */
    private Integer IDEVCLASS;

    /**
     * 报账日期
     */

    private LocalDateTime reimbursementdate;

    /**
     * 首次安装日期
     */
    private LocalDateTime firstinstalldate;

    /**
     * 资产编号
     */
    private LocalDateTime assetnum;

    /**
     * 免保期限(单位年)
     */
    private Integer freetimelimit;

    /**
     * 免保到期日期
     */

    private LocalDateTime freeduedate;

    /**
     * 侧键风格
     */
    private String STRPROFILESTYLE;
}
