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

/**
 * 工单表实体类
 *
 * @author 代码自动生成
 * @since 2020-07-13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_work_order")
@ApiModel(value = "WorkOrder对象", description = "工单表")
public class ManageWorkOrderDO implements Serializable {

    private static final long serialVersionUID = 1L;


  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;
    /**
     * 终端编号
     */
  @ApiModelProperty(value = "终端编号")
  private String terminalCode;
    /**
     * 工单类型：1-故障工单；2-投诉工单；3-巡检

     */
  @ApiModelProperty(value = "工单类型：1-故障工单；2-投诉工单；3-巡检 ")
  private String workOrderType;
    /**
     * 优先级编号（1：一般 2：紧急）
     */
  @ApiModelProperty(value = "优先级编号（1：一般 2：紧急）")
  private String priorityCode;
    /**
     * 模块编号
     */
  @ApiModelProperty(value = "模块编号")
  private String moduleCode;
    /**
     * 要求完成时间
     */
  @ApiModelProperty(value = "要求完成时间")
  private LocalDateTime requirCompleteTime;
    /**
     * 创建人id
     */
  @ApiModelProperty(value = "创建人id")
  private String createId;
    /**
     * 工单编号
     */
  @ApiModelProperty(value = "工单编号")
  private String workOrderCode;
    /**
     * 工单状态  0 :待处理；1：待评价；2：办接；3：待分行确认；4：待总行确认；4：待厂商回复；6：总行知悉；7：分行知悉；8：退回；9：已关闭
     */
  @ApiModelProperty(value = "工单状态  0 :待处理；1：待评价；2：办接；3：待分行确认；4：待总行确认；4：待厂商回复；6：总行知悉；7：分行知悉；8：退回；9：已关闭")
  private String workOrderStatus;
    /**
     * 工单描述
     */
  @ApiModelProperty(value = "工单描述")
  private String workOrderDescribe;
    /**
     * 服务主管
     */
  @ApiModelProperty(value = "服务主管")
  private String director;
    /**
     * 服务工程师
     */
  @ApiModelProperty(value = "服务工程师")
  private String engineer;
    /**
     * 工单完成时间
     */
  @ApiModelProperty(value = "工单完成时间")
  private LocalDateTime workOrderCompleteTime;
    /**
     * 描述类型
     */
  @ApiModelProperty(value = "描述类型")
  private String describeType;
    /**
     * 评价等级（1：优 2：良 3：一般 4：差）
     */
  @ApiModelProperty(value = "评价等级（1：优 2：良 3：一般 4：差）")
  private String rating;
    /**
     * 评价备注
     */
  @ApiModelProperty(value = "评价备注")
  private String ratingNote;
    /**
     * 处理类型（1：远程处理  2：现场处理）
     */
  @ApiModelProperty(value = "处理类型（1：远程处理  2：现场处理）")
  private String dealType;
    /**
     * 处理备注
     */
  @ApiModelProperty(value = "处理备注")
  private String dealNote;
    /**
     * 创建时间
     */
  @ApiModelProperty(value = "创建时间")
  @TableField(fill = FieldFill.INSERT)
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createTime;
    /**
     * 巡检陪同人员
     */
  @ApiModelProperty(value = "巡检陪同人员")
  private String escortsPatrol;
    /**
     * 巡检开始时间
     */
  @ApiModelProperty(value = "巡检开始时间")
  private LocalDateTime escortsStartTime;
    /**
     * 巡检完成时间
     */
  @ApiModelProperty(value = "巡检完成时间")
  private LocalDateTime escortsCompleteTime;
    /**
     * 巡检处理方式（1：处理方式 1 ；2：处理方式2；3:处理方式3；4：处理方式4；5：处理方式5；6：处理方式6）
     */
  @ApiModelProperty(value = "巡检处理方式（1：处理方式 1 ；2：处理方式2；3:处理方式3；4：处理方式4；5：处理方式5；6：处理方式6）")
  private String escortsHandling;
    /**
     * 分行
     */
  @ApiModelProperty(value = "分行")
  private String branch;
    /**
     * 支行
     */
  @ApiModelProperty(value = "支行")
  private String subBranch;
    /**
     * 自助行
     */
  @ApiModelProperty(value = "自助行")
  private String buffetLine;
    /**
     * 厂商
     */
  @ApiModelProperty(value = "厂商")
  private String vendor;
    /**
     * 创建人姓名
     */
  @ApiModelProperty(value = "创建人姓名")
  private String createName;


}
