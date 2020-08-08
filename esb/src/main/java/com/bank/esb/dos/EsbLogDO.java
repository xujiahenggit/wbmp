package com.bank.esb.dos;

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
 * 实体类
 *
 * @author 代码自动生成
 * @since 2020-07-22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_esb_log")
@ApiModel(value = "EsbLog对象", description = "EsbLog对象")
public class EsbLogDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uid", type = IdType.AUTO)
    private String uid;

    /**
     * 流水号
     */
  @ApiModelProperty(value = "流水号")
  private String id;

    /**
     * 请求报文
     */
  @ApiModelProperty(value = "请求报文")
  private String xml;


}
