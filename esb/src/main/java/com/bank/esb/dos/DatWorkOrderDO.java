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
 * 故障工单表实体类
 *
 * @author 代码自动生成
 * @since 2020-07-10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("dat_work_order")
@ApiModel(value = "DatWorkOrder对象", description = "故障工单表")
public class DatWorkOrderDO implements Serializable {

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
  @TableField("strTermNum")
  private String strTermNum;
    /**
     * 银行编号
     */
  @ApiModelProperty(value = "银行编号")
  @TableField("strBankNum")
  private String strBankNum;
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
     * 终端地址
     */
  @ApiModelProperty(value = "终端地址")
  @TableField("strTermAddr")
  private String strTermAddr;
    /**
     * 设备类型
     */
  @ApiModelProperty(value = "设备类型")
  @TableField("deviceType")
  private Integer deviceType;
    /**
     * 设备分类
     */
  @ApiModelProperty(value = "设备分类")
  @TableField("deviceClass")
  private Integer deviceClass;
    /**
     * 设备厂商
     */
  @ApiModelProperty(value = "设备厂商")
  @TableField("deviceManu")
  private String deviceManu;
    /**
     * 设备型号
     */
  @ApiModelProperty(value = "设备型号")
  @TableField("deviceModel")
  private String deviceModel;
    /**
     * 模块名称
     */
  @ApiModelProperty(value = "模块名称")
  @TableField("strDCName")
  private String strDCName;
    /**
     * 模块中文名称
     */
  @ApiModelProperty(value = "模块中文名称")
  @TableField("strDCNameCN")
  private String strDCNameCN;
    /**
     * 异常类型:1.硬件异常 2.服务异常
     */
  @ApiModelProperty(value = "异常类型:1.硬件异常 2.服务异常")
  @TableField("errorType")
  private String errorType;
    /**
     * 异常代码
     */
  @ApiModelProperty(value = "异常代码")
  @TableField("errorCode")
  private String errorCode;
    /**
     * 异常描述
     */
  @ApiModelProperty(value = "异常描述")
  @TableField("errorDesc")
  private String errorDesc;
    /**
     * 状态:1.故障中 2.警告中 3.处理中 4.完成
     */
  @ApiModelProperty(value = "状态:1.故障中 2.警告中 3.处理中 4.完成")
  private Integer status;
    /**
     * 工单类型：1.系统自动生成 2.手工创建
     */
  @ApiModelProperty(value = "工单类型：1.系统自动生成 2.手工创建")
  private Integer type;
    /**
     * 行内接收人工号
     */
  @ApiModelProperty(value = "行内接收人工号")
  @TableField("bankReceiveNum")
  private String bankReceiveNum;
    /**
     * 行内接收人电话号码
     */
  @ApiModelProperty(value = "行内接收人电话号码")
  @TableField("bankReceiveMobile")
  private String bankReceiveMobile;
    /**
     * 行内接收人姓名
     */
  @ApiModelProperty(value = "行内接收人姓名")
  @TableField("bankReceiveName")
  private String bankReceiveName;
    /**
     * 厂商接收人电话号码
     */
  @ApiModelProperty(value = "厂商接收人电话号码")
  @TableField("manuReceiveMobile")
  private String manuReceiveMobile;
    /**
     * 厂商接收人姓名
     */
  @ApiModelProperty(value = "厂商接收人姓名")
  @TableField("manuReceiveName")
  private String manuReceiveName;
    /**
     * 开始时间
     */
  @ApiModelProperty(value = "开始时间")
  @TableField("beginDate")
  private LocalDateTime beginDate;
    /**
     * 结束时间
     */
  @ApiModelProperty(value = "结束时间")
  @TableField("endDate")
  private LocalDateTime endDate;
    /**
     * 备注:用于手动创建工单处理内容存储
     */
  @ApiModelProperty(value = "备注:用于手动创建工单处理内容存储")
  private String remark;


}
