package com.bank.esb.dos;

import com.baomidou.mybatisplus.annotation.TableName;
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
