package com.bank.manage.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 处理附件表实体类
 *
 * @author 代码自动生成
 * @since 2020-07-13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_work_order_attachment")
@ApiModel(value = "WorkOrderAttachment对象", description = "处理附件表")
public class ManageWorkOrderAttachmentDO implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
  @ApiModelProperty(value = "主键")
  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;
    /**
     * 工单id
     */
  @ApiModelProperty(value = "工单id")
  private String repairId;
    /**
     * 附件路径
     */
  @ApiModelProperty(value = "附件路径")
  private String path;
    /**
     * 附件名称
     */
  @ApiModelProperty(value = "附件名称")
  private String name;
    /**
     * 附件大小
     */
  @ApiModelProperty(value = "附件大小")
  private String size;


}
