package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * 柜员信息表实体类
 *
 * @author 代码自动生成
 * @since 2020-07-03
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("wbmp_abs_teller_info")
@ApiModel(value = "WbmpAbsTellerInfo对象", description = "柜员信息表")
public class WbmpAbsTellerInfoDO implements Serializable {

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
     * 柜员状态 0-离线，1-在线
     */
  @ApiModelProperty(value = "柜员状态 0-离线，1-在线")
  @TableField("TELLER_IND")
  private String tellerInd;


}
