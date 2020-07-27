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
 * 自助银行信息表实体类
 *
 * @author 代码自动生成
 * @since 2020-07-10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("dat_selfsvcbank")
@ApiModel(value = "DatSelfsvcbank对象", description = "自助银行信息表")
public class DatSelfsvcbankDO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 表ID
     */
  @ApiModelProperty(value = "表ID")
  @TableId(value = "ID", type = IdType.AUTO)
  private Integer id;
    /**
     * 自助银行编号
     */
  @ApiModelProperty(value = "自助银行编号")
  @TableField("STRSSBNUM")
  private String strssbnum;
    /**
     * 自助银行名称
     */
  @ApiModelProperty(value = "自助银行名称")
  @TableField("STRSSBNAME")
  private String strssbname;
    /**
     * 自助银行类型：离行成套  1，网点成套  2，离行单机  3，网点单机  4
     */
  @ApiModelProperty(value = "自助银行类型：离行成套  1，网点成套  2，离行单机  3，网点单机  4")
  @TableField("ISSBTYPE")
  private Integer issbtype;
    /**
     * 网点级别；一级支行 1，二级支行 2，社区银行 3，离行式网点 4
     */
  @ApiModelProperty(value = "网点级别；一级支行 1，二级支行 2，社区银行 3，离行式网点 4")
  @TableField("Level")
  private Integer Level;
    /**
     * 银行编号
     */
  @ApiModelProperty(value = "银行编号")
  @TableField("STRBANKNUM")
  private String strbanknum;
    /**
     * 分行编号
     */
  @ApiModelProperty(value = "分行编号")
  @TableField("STRBRANCHNUM")
  private String strbranchnum;
    /**
     * 支行编号
     */
  @ApiModelProperty(value = "支行编号")
  @TableField("STRSUBBRANCHNUM")
  private String strsubbranchnum;
    /**
     * 状态：待复核 0, 复核不通过 1, 待审批 2, 审批通过 3, 审批不通过 4, 调试 5, 暂停 6, 启用 7, 停用 8, 撤销 9
     */
  @ApiModelProperty(value = "状态：待复核 0, 复核不通过 1, 待审批 2, 审批通过 3, 审批不通过 4, 调试 5, 暂停 6, 启用 7, 停用 8, 撤销 9")
  @TableField("ISTATUS")
  private Integer istatus;
    /**
     * 备注
     */
  @ApiModelProperty(value = "备注")
  @TableField("STRMEMO")
  private String strmemo;
    /**
     * 开始日期
     */
  @ApiModelProperty(value = "开始日期")
  @TableField("DTSTART")
  private LocalDateTime dtstart;
    /**
     * 结束日期
     */
  @ApiModelProperty(value = "结束日期")
  @TableField("DTEND")
  private LocalDateTime dtend;
    /**
     * 描述
     */
  @ApiModelProperty(value = "描述")
  @TableField("STRDESC")
  private String strdesc;
    /**
     * ATM数
     */
  @ApiModelProperty(value = "ATM数")
  @TableField("ATMCOUNT")
  private Integer atmcount;
    /**
     * CDT数
     */
  @ApiModelProperty(value = "CDT数")
  @TableField("CDTCOUNT")
  private Integer cdtcount;
    /**
     * CRS数
     */
  @ApiModelProperty(value = "CRS数")
  @TableField("CRSCOUNT")
  private Integer crscount;
    /**
     * 查询机数
     */
  @ApiModelProperty(value = "查询机数")
  @TableField("INQUIRYCOUNT")
  private Integer inquirycount;
    /**
     * 其他设备数
     */
  @ApiModelProperty(value = "其他设备数")
  @TableField("OTHERCOUNT")
  private Integer othercount;
    /**
     * 地址
     */
  @ApiModelProperty(value = "地址")
  @TableField("STRADDRESS")
  private String straddress;


}
