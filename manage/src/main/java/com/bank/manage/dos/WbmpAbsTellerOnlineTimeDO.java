package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 柜员在线时长信息表实体类
 *
 * @author 代码自动生成
 * @since 2020-07-03
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wbmp_abs_teller_online_time")
@ApiModel(value = "WbmpAbsTellerOnlineTime对象", description = "柜员在线时长信息表")
public class WbmpAbsTellerOnlineTimeDO implements Serializable {

    private static final long serialVersionUID = 1L;


  /**
     * 机构号
     */
  @ApiModelProperty(value = "机构号")
  @TableField("ORG_ID")
  private String orgId;
    /**
     * 柜员号
     */
  @ApiModelProperty(value = "柜员号")
  @TableId("TELLER_ID")
  private String tellerId;
    /**
     * 柜员名称
     */
  @ApiModelProperty(value = "柜员名称")
  @TableField("TELLER_NAME")
  private String tellerName;
    /**
     * 最后更新时间
     */
  @ApiModelProperty(value = "最后更新时间")
  @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime updateTime;
    /**
     * 在线时长（秒）
     */
  @ApiModelProperty(value = "在线时长（秒）")
  @TableField("ONLINE_TIME")
  private long onlineTime;


}
