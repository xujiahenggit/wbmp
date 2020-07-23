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
 * 工单流水表实体类
 *
 * @author 代码自动生成
 * @since 2020-07-13
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
     * 工单id
     */
  @ApiModelProperty(value = "工单id")
  private String wordOrderId;

    /**
     * 处理类型 (1退回、2分派 3办结）
     */
  @ApiModelProperty(value = "处理类型 (1退回、2分派 3办结")
  private String dealWithType;

    /**
     * 处理时间
     */
  @ApiModelProperty(value = "处理时间")
  private LocalDateTime dealWithTime;

    /**
     * 处理人id
     */
  @ApiModelProperty(value = "处理人id")
  private String dealWithPeopleId;

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
     * 机构id
     */
  @ApiModelProperty(value = "机构id")
  private String orgId;

    /**
     * 处理人姓名
     */
  @ApiModelProperty(value = "处理人姓名")
  private String dealWithPeopleName;

  @ApiModelProperty(value = "处理人电话")
  private String phone;

  @ApiModelProperty(value = "处理人电话")
  private String  dealWithPeopleRole;

  @ApiModelProperty(value = "处理人电话")
  private String  operationType;


}
