package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 工单流水表实体类
 *
 * @author 代码自动生成
 * @since 2020-07-23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_work_water")
@ApiModel(value = "WorkWater对象", description = "工单流水表")
public class WorkWaterDO implements Serializable {

  private static final long serialVersionUID = 1L;


  /**
   * 主键
   */
  @ApiModelProperty(value = "主键")
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;
  /**
   * 序号
   */
  @ApiModelProperty(value = "序号")
  private String serialNumber;
  /**
   * 工单编号(以工单编号为准,命名错误，特此记录 work_order_code)
   */
  @ApiModelProperty(value = "工单编号(以工单编号为准,命名错误，特此记录 work_order_code)")
  private String wordOrderId;
  /**
   * 处理类型 (1退回、2分派，3处理，4办结)
   */
  @ApiModelProperty(value = "处理类型 (1退回、2分派，3处理，4办结)")
  private String dealWithType;
  /**
   * 处理时间
   */
  @ApiModelProperty(value = "处理时间")
  private Date dealWithTime;
  /**
   * 机构id
   */
  @ApiModelProperty(value = "机构id")
  private String orgId;
  /**
   * 处理人id
   */
  @ApiModelProperty(value = "处理人id")
  private String dealWithPeopleId;
  /**
   * 处理人姓名
   */
  @ApiModelProperty(value = "处理人姓名")
  private String dealWithPeopleName;
  /**
   * 处理人角色：1服务主管，2服务工程师，3分行管理员，4总行管理员，5设备厂商，6创建人
   */
  @ApiModelProperty(value = "处理人角色：1服务主管，2服务工程师，3分行管理员，4总行管理员，5设备厂商，6创建人")
  private Integer dealWithPeopleRole;
  /**
   * 处理备注
   */
  @ApiModelProperty(value = "处理备注")
  private String dealWithNote;
  /**
   * 创建时间
   */
  @ApiModelProperty(value = "创建时间")
  @TableField(fill = FieldFill.INSERT)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createTime;
  /**
   * 处理人电话
   */
  @ApiModelProperty(value = "处理人电话")
  private String phone;
  /**
   *  操作类型0 :待处理；1：待评价；2：办接；3：分行确认；4：总行确认；5：厂商回复；6：总行知悉；7：分行知悉；8：退回；9：已关闭 10：已评价
   */
  @ApiModelProperty(value = " 操作类型0 :新建；1：评价；2：办结；3：分行确认；4：总行确认；5：厂商回复；6：总行知悉；7：分行知悉；8：退回；9：关闭投诉 10不关闭投诉  11分派，12处理 ，13分行取消，14总行取消")
  private String operationType;


}
