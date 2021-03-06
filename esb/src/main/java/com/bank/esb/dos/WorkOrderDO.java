package com.bank.esb.dos;

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
 * @since 2020-07-27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_work_order")
@ApiModel(value = "WorkOrder对象", description = "工单表")
public class WorkOrderDO implements Serializable {

  private static final long serialVersionUID = 1L;


  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;
  /**
   * 工单编号
   */
  @ApiModelProperty(value = "工单编号")
  private String workOrderCode;
  /**
   * 终端编号
   */
  @ApiModelProperty(value = "终端编号")
  private String terminalCode;
  /**
   * 设备序列号
   */
  @ApiModelProperty(value = "设备序列号")
  private String serialNum;
  /**
   * 设备类型：1 自动取款机,2 自动存款机,3 自动存取款机,4 自动查询机,5 多媒体终端,6 个性化发卡机,7 回单打印机,8 智能现金柜
   */
  @ApiModelProperty(value = "设备类型：1 自动取款机,2 自动存款机,3 自动存取款机,4 自动查询机,5 多媒体终端,6 个性化发卡机,7 回单打印机,8 智能现金柜")
  private Integer deviceType;
  /**
   * 设备分类：0 现金自助,1 非现金自助,2 快柜设备,3 大额机,4 智能现金柜
   */
  @ApiModelProperty(value = "设备分类：0 现金自助,1 非现金自助,2 快柜设备,3 大额机,4 智能现金柜")
  private String deviceClass;
  /**
   * 工单类型：01-故障工单；02-投诉工单；03-巡检
   */
  @ApiModelProperty(value = "工单类型：01-故障工单；02-投诉工单；03-巡检")
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
   * 创建人姓名
   */
  @ApiModelProperty(value = "创建人姓名")
  private String createName;
  /**
   * 回复意见
   */
  @ApiModelProperty(value = "回复意见")
  private String suggestion;
  /**
   * 工单状态 1：待分行确认；2：待总行确认；3：待厂商回复；4：待总行知悉；5：待分行知悉； 6 :待服务主管处理；7：待工程师处理 8：待评价；9：办结；10:已取消；
   */
  @ApiModelProperty(value = "工单状态 1：待分行确认；2：待总行确认；3：待厂商回复；4：待总行知悉；5：待分行知悉； 6 :待服务主管处理；7：待工程师处理 8：待评价；9：办结；10:已取消；")
  private String workOrderStatus;
  /**
   * 工单描述
   */
  @ApiModelProperty(value = "工单描述")
  private String workOrderDescribe;
  /**
   * 服务主管id（手机号）
   */
  @ApiModelProperty(value = "服务主管id（手机号）")
  private String director;
  /**
   * 服务工程师id（手机号）
   */
  @ApiModelProperty(value = "服务工程师id（手机号）")
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
   * 退回意见
   */
  @ApiModelProperty(value = "退回意见")
  private String returnOpinion;
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
   * 巡检陪同人员编号
   */
  @ApiModelProperty(value = "巡检陪同人员编号")
  private String escortsPatrol;
  /**
   * 巡检陪同人员姓名
   */
  @ApiModelProperty(value = "巡检陪同人员姓名")
  private String escortsPatrolName;
  /**
   * 巡检陪同人员手机号
   */
  @ApiModelProperty(value = "巡检陪同人员手机号")
  private String escortsPatrolPhone;
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
   * 巡检标识 1:已巡检   2：未巡检
   */
  @ApiModelProperty(value = "巡检标识 1:已巡检   2：未巡检")
  private String escortsFlag;
  /**
   * 保修截止时间
   */
  @ApiModelProperty(value = "保修截止时间")
  private String freeduedate;
  /**
   * 分行
   */
  @ApiModelProperty(value = "分行")
  private String branch;
  /**
   * 分行名字
   */
  @ApiModelProperty(value = "分行名字")
  private String branchName;
  /**
   * 支行
   */
  @ApiModelProperty(value = "支行")
  private String subBranch;
  /**
   * 支行名字
   */
  @ApiModelProperty(value = "支行名字")
  private String subBranchName;
  /**
   * 自助行
   */
  @ApiModelProperty(value = "自助行")
  private String buffetLine;
  /**
   * 自助行名字
   */
  @ApiModelProperty(value = "自助行名字")
  private String buffetLineName;
  /**
   * 厂商id
   */
  @ApiModelProperty(value = "厂商id")
  private String vendor;
  /**
   * 厂商名字
   */
  @ApiModelProperty(value = "厂商名字")
  private String vendorName;
  /**
   * 创建人手机号
   */
  @ApiModelProperty(value = "创建人手机号")
  private String createUserPhone;
  /**
   * 服务主管名称
   */
  @ApiModelProperty(value = "服务主管名称")
  private String directorName;
  /**
   * 服务工程师名称
   */
  @ApiModelProperty(value = "服务工程师名称")
  private String engineerName;
  /**
   * 现场联系人姓名
   */
  @ApiModelProperty(value = "现场联系人姓名")
  private String contactName;
  /**
   * 现场联系人号码
   */
  @ApiModelProperty(value = "现场联系人号码")
  private String contactPhone;
  /**
   * 设备型号
   */
  @ApiModelProperty(value = "设备型号")
  private String deviceModel;
  /**
   * 机构号
   */
  @ApiModelProperty(value = "机构号")
  private String orgId;
  /**
   * 创建时间
   */
  @ApiModelProperty(value = "创建时间")
  @TableField(fill = FieldFill.INSERT)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createTime;
  /**
   * 安装日期
   */
  @ApiModelProperty(value = "安装日期")
  private String installDate;
  /**
   * 安装地址
   */
  @ApiModelProperty(value = "安装地址")
  private String installAddr;
  /**
   * 机构名称
   */
  @ApiModelProperty(value = "机构名称")
  private String orgName;


}
