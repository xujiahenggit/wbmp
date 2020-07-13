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
 * 设备信息表实体类
 *
 * @author 代码自动生成
 * @since 2020-07-10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("dat_device")
@ApiModel(value = "DatDevice对象", description = "设备信息表")
public class DatDeviceDO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 表ID
     */
  @ApiModelProperty(value = "表ID")
  @TableId(value = "ID", type = IdType.AUTO)
  private Integer id;
    /**
     * 设备序列号
     */
  @ApiModelProperty(value = "设备序列号")
  @TableField("STRDEVSN")
  private String strdevsn;
    /**
     * 设备型号
     */
  @ApiModelProperty(value = "设备型号")
  @TableField("STRDEVMODEL")
  private String strdevmodel;
    /**
     * 设备状态：待签收设备 0，库存设备 1，停用未回库设备 2，拒收设备 3，上线设备 4，已报废设备 5
     */
  @ApiModelProperty(value = "设备状态：待签收设备 0，库存设备 1，停用未回库设备 2，拒收设备 3，上线设备 4，已报废设备 5")
  @TableField("ISTATUS")
  private Integer istatus;
    /**
     * 所属分行
     */
  @ApiModelProperty(value = "所属分行")
  @TableField("STRBRANCHNUM")
  private String strbranchnum;
    /**
     * 备注
     */
  @ApiModelProperty(value = "备注")
  @TableField("STRMEMO")
  private String strmemo;
    /**
     * 设备类型：自动取款机 1，自动存款机 2，自动存取款机 3，自动查询机 4
     */
  @ApiModelProperty(value = "设备类型：自动取款机 1，自动存款机 2，自动存取款机 3，自动查询机 4")
  @TableField("IDEVTYPE")
  private Integer idevtype;
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
     * 设备厂商
     */
  @ApiModelProperty(value = "设备厂商")
  @TableField("STRDEVMANU")
  private String strdevmanu;
    /**
     * 添加时间
     */
  @ApiModelProperty(value = "添加时间")
  @TableField("ADDDATE")
  private LocalDateTime adddate;
    /**
     * 开门方向：忽略 0，前开门 1，后开门 2
     */
  @ApiModelProperty(value = "开门方向：忽略 0，前开门 1，后开门 2")
  @TableField("ISAFEDOORPOSITION")
  private Integer isafedoorposition;
    /**
     * 银行编号
     */
  @ApiModelProperty(value = "银行编号")
  @TableField("STRBANKNUM")
  private String strbanknum;
    /**
     * 设备分类：现金自助0、非现金自助1、快柜设备2
     */
  @ApiModelProperty(value = "设备分类：现金自助0、非现金自助1、快柜设备2")
  @TableField("IDEVCLASS")
  private Integer idevclass;
    /**
     * 报账日期
     */
  @ApiModelProperty(value = "报账日期")
  private LocalDateTime reimbursementdate;
    /**
     * 首次安装日期
     */
  @ApiModelProperty(value = "首次安装日期")
  private LocalDateTime firstinstalldate;
    /**
     * 资产编号
     */
  @ApiModelProperty(value = "资产编号")
  private String assetnum;
    /**
     * 免保期限(单位年)
     */
  @ApiModelProperty(value = "免保期限(单位年)")
  private Integer freetimelimit;
    /**
     * 免保到期日期
     */
  @ApiModelProperty(value = "免保到期日期")
  private LocalDateTime freeduedate;
    /**
     * 侧键风格
     */
  @ApiModelProperty(value = "侧键风格")
  @TableField("STRPROFILESTYLE")
  private String strprofilestyle;


}
